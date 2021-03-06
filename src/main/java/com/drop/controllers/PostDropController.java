package com.drop.controllers;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.drop.controller.form.DealPostForm;
import com.drop.controller.form.ReasonToDeleteForm;
import com.drop.dao.domain.DealCategory;
import com.drop.dao.domain.DealPost;
import com.drop.dao.domain.MailingAddress;
import com.drop.dao.domain.User;
import com.drop.enums.POST_DEAL_TYPE;
import com.drop.exception.DropException;
import com.drop.service.IDealCategoryService;
import com.drop.service.IDealPostService;
import com.drop.util.DropUtil;
import com.drop.util.WebUtil;

@Controller
public class PostDropController extends BaseController {

	private static final Logger logger = Logger
			.getLogger(PostDropController.class);

	@Autowired
	private IDealPostService dealPostService;

	@Autowired
	@Qualifier("validationConfig")
	private Properties validationConfig;

	@Autowired
	private HttpSession session;

	@Autowired
	private IDealCategoryService categoryService;

	@Autowired
	@Qualifier("msgConfig")
	private Properties msgConfig;

	@RequestMapping(value = "/showDealPostPage", method = RequestMethod.GET)
	public ModelAndView showDealPostPage(ModelMap map) {

		if (!WebUtil.userAuthorization(session)) {
			return new ModelAndView("redirect:/home.htm");
		}

		ModelAndView modelAndView = new ModelAndView("dealPostPage");

		try {

			DealPostForm dealPostForm = new DealPostForm();

			List<DealCategory> categories = categoryService
					.getAllDealCategories();
			dealPostForm.setDealCategories(categories);

			map.addAttribute("dealPostForm", dealPostForm);

			initializeCommonModel(map);

		} catch (Exception e) {
			logger.fatal(DropUtil.getExceptionDescriptionString(e));
			e.printStackTrace();
			throw new DropException();
		}
		return modelAndView;
	}

	@RequestMapping(value = "/postdrop", method = RequestMethod.POST)
	public String postDrop(@Valid DealPostForm form, BindingResult result,
			ModelMap map, HttpServletRequest request) {

		if (!WebUtil.userAuthorization(session)) {
			return "redirect:/home.htm";
		}

		try {

			
			String dealType = form.getDealType();

			if (dealType != null) {

				if (dealType.equals(POST_DEAL_TYPE.LOCAL_DEAL.getDealType())) {

					String addressLine1 = form.getAddressLine1();
					String addressLine2 = form.getAddressLine2();
					String state = form.getState();
					String city = form.getCity();
					String zip = form.getZip();

					if (!(addressLine1 != null && addressLine1.length() > 0)) {
						result.rejectValue("addressLine1",
								"NotEmpty.dealPostForm.addressLine1");
					}
					if (!(addressLine2 != null && addressLine2.length() > 0)) {
						result.rejectValue("addressLine2",
								"NotEmpty.dealPostForm.addressLine2");
					}
					if (!(state != null && state.length() > 0)) {
						result.rejectValue("state",
								"NotEmpty.dealPostForm.state");
					}
					if (!(city != null && city.length() > 0)) {
						result.rejectValue("city", "NotEmpty.dealPostForm.city");
					}
					if (!(zip != null && zip.length() > 0)) {
						result.rejectValue("zip", "NotEmpty.dealPostForm.zip");
					}

				} else if (dealType.equals(POST_DEAL_TYPE.ONLINE_DEAL
						.getDealType())) {
					String url = form.getUrl();
					if (!(url != null && url.length() > 0)) {
						result.rejectValue("url", "NotEmpty.dealPostForm.url");
					}
				}
			}
						
			if (result.hasErrors()) {
				form.setDealCategories(categoryService.getAllDealCategories());
				initializeCommonModel(map);
				return "dealPostPage";
			} 

			String dateFormat = msgConfig.getProperty("date.format");
			
			Date starts = DropUtil.convertStringToDate(form.getStarts(),
					dateFormat);
			Date expires = DropUtil.convertStringToDate(form.getExpires(),
					dateFormat);

			if (starts.after(expires)) {
				result.rejectValue("starts", "dropstart.after.dropexpiry");
			}
			
			if (starts.compareTo(expires) == 0) {
				result.rejectValue("starts", "dropstart.same.dropexpiry");
			}

			if (form.getSalePrice().compareTo(form.getRetailPrice()) == 1) {
				result.rejectValue("salePrice", "salePrice.more.retailprice");
			}
			
			if (result.hasErrors()) {
				form.setDealCategories(categoryService.getAllDealCategories());
				initializeCommonModel(map);
				return "dealPostPage";
			} else {
				
				form.setIpAddress(DropUtil.getIPAddress(request));
				HttpSession session = request.getSession(false);
				User user = (User) session.getAttribute("user");
				form.setUserId(user.getUserId());
				dealPostService.saveDealPost(form);
			}

		} catch (Exception e) {
			logger.fatal(DropUtil.getExceptionDescriptionString(e));
			e.printStackTrace();
			return "error";
		}
		return "redirect:/showMyDropPost.htm";
	}

	/*
	 * @RequestMapping(value = "/postdrop", method = RequestMethod.POST) public
	 * 
	 * @ResponseBody String postDrop(@Valid DealPostForm form, BindingResult
	 * result, ModelMap map, HttpServletRequest request) {
	 * 
	 * if(!WebUtil.userAuthorization(session)) { return "redirect:/home.htm"; }
	 * 
	 * try { String dealType = form.getDealType();
	 * 
	 * if(dealType != null) {
	 * 
	 * if(dealType.equals(POST_DEAL_TYPE.LOCAL_DEAL.getDealType())) {
	 * 
	 * String addressLine1 = form.getAddressLine1(); String addressLine2 =
	 * form.getAddressLine2(); String state = form.getState(); String city =
	 * form.getCity(); String zip = form.getZip();
	 * 
	 * if(!(addressLine1 != null && addressLine1.length() > 0)) {
	 * result.rejectValue("addressLine1",
	 * validationConfig.getProperty("NotEmpty.dealPostForm.addressLine1")); } if
	 * (!(addressLine2 != null && addressLine2.length() > 0)) {
	 * result.rejectValue("addressLine2",
	 * validationConfig.getProperty("NotEmpty.dealPostForm.addressLine2")); } if
	 * (!(state != null && state.length() > 0)) { result.rejectValue("state",
	 * validationConfig.getProperty("NotEmpty.dealPostForm.state")); } if
	 * (!(city != null && city.length() > 0)) { result.rejectValue("city",
	 * validationConfig.getProperty("NotEmpty.dealPostForm.city")); } if (!(zip
	 * != null && zip.length() > 0)) { result.rejectValue("zip",
	 * validationConfig.getProperty("NotEmpty.dealPostForm.zip")); }
	 * 
	 * } else if(dealType.equals(POST_DEAL_TYPE.ONLINE_DEAL.getDealType())) {
	 * String url = form.getUrl(); if (!(url != null && url.length() > 0)) {
	 * result.rejectValue("url",
	 * validationConfig.getProperty("NotEmpty.dealPostForm.url")); } } }
	 * 
	 * if (result.hasErrors()) { return DropUtil.getErrorString(result); } else
	 * {
	 * 
	 * form.setIpAddress(DropUtil.getIPAddress(request)); HttpSession session =
	 * request.getSession(false); User user =
	 * (User)session.getAttribute("user"); form.setUserId(user.getUserId());
	 * dealPostService.saveDealPost(form); }
	 * 
	 * } catch (Exception e) {
	 * logger.fatal(DropUtil.getExceptionDescriptionString(e));
	 * e.printStackTrace(); return "ERROR"; } return "SUCCESS"; }
	 */

	@RequestMapping(value = "/showMyDropPost", method = RequestMethod.GET)
	public ModelAndView showMyDropWanted(ModelMap map, HttpSession session) {

		if (!WebUtil.userAuthorization(session)) {
			return new ModelAndView("redirect:/home.htm");
		}

		ModelAndView modelAndView = new ModelAndView("myDropPost");

		try {
			User user = WebUtil.getSessionUser(session);
			List<DealPost> dealPostList = dealPostService
					.getAllActiveDealPostForUser(user.getUserId());
			modelAndView.addObject("dealPostList", dealPostList);
			initializeCommonModel(map);

		} catch (Exception e) {
			logger.fatal(DropUtil.getExceptionDescriptionString(e));
			e.printStackTrace();
			throw new DropException();
		}
		return modelAndView;
	}

	@RequestMapping(value = "/showDeleteDropPost", method = RequestMethod.GET)
	public ModelAndView showDialog(@RequestParam Long dealId, ModelMap map,
			HttpSession session) {

		if (!WebUtil.userAuthorization(session)) {
			return new ModelAndView("redirect:/home.htm");
		}

		ModelAndView modelAndView = new ModelAndView("deleteDropPostDialog");

		try {
			ReasonToDeleteForm reasonToDeleteForm = new ReasonToDeleteForm();
			reasonToDeleteForm.setDealId(dealId);
			modelAndView.addObject("reasonToDeleteForm", reasonToDeleteForm);

		} catch (Exception e) {
			logger.fatal(DropUtil.getExceptionDescriptionString(e));
			e.printStackTrace();
			throw new DropException();
		}
		return modelAndView;
	}

	@RequestMapping(value = "/deleteDropPost", method = RequestMethod.POST)
	public @ResponseBody
	String deleteDropWanted(@Valid ReasonToDeleteForm form,
			BindingResult result, ModelMap map, HttpServletRequest request,
			HttpSession session) {

		if (!WebUtil.userAuthorization(session)) {
			return "redirect:/home.htm";
		}

		try {

			if (result.hasErrors()) {
				return DropUtil.getErrorString(result);
			}

			User user = WebUtil.getSessionUser(session);
			DealPost userDealPost = null;

			List<DealPost> dealPostList = dealPostService
					.getAllActiveDealPostForUser(user.getUserId());
			for (DealPost dealPost : dealPostList) {
				if (dealPost.getId() == form.getDealId()) {
					userDealPost = dealPost;
					break;
				}
			}

			if (userDealPost != null) {
				dealPostService.deleteDealPost(form);
			}

		} catch (Exception e) {
			logger.fatal(DropUtil.getExceptionDescriptionString(e));
			e.printStackTrace();
			return "ERROR";
		}
		return "SUCCESS";
	}

	@RequestMapping(value = "/showEditDropPost", method = RequestMethod.GET)
	public ModelAndView showEditDropWantedForm(
			@RequestParam("dropPostId") long dropPostId, ModelMap map,
			HttpSession session) {

		if (!WebUtil.userAuthorization(session)) {
			return new ModelAndView("redirect:/home.htm");
		}

		ModelAndView modelAndView = new ModelAndView("editDealPostPage");

		try {
			User user = WebUtil.getSessionUser(session);
			DealPost userDealPost = null;

			List<DealPost> dealPostList = dealPostService
					.getAllActiveDealPostForUser(user.getUserId());
			for (DealPost dealPost : dealPostList) {
				if (dealPost.getId() == dropPostId) {
					userDealPost = dealPost;
					break;
				}
			}

			// If deal belongs to user then allow edit
			if (userDealPost != null) {
				DealPostForm form = new DealPostForm();
				form.setTitle(userDealPost.getTitle());
				form.setDescription(userDealPost.getDescription());
				form.setCategory(userDealPost.getDealCategory().getId());
				form.setSalePrice(userDealPost.getSalePrice());
				form.setRetailPrice(userDealPost.getRetailPrice());
				form.setDiscountPercent(userDealPost.getDiscountPercent());
				form.setExpires(DropUtil.convertDateToString(
						userDealPost.getExpires(),
						msgConfig.getProperty("calendar.date.format")));
				form.setStarts(DropUtil.convertDateToString(
						userDealPost.getStarts(),
						msgConfig.getProperty("calendar.date.format")));
				form.setSpecialInstructions(userDealPost
						.getSpecialInstructions());
				form.setCouponsRequired(userDealPost.getCouponsRequired());
				form.setMembershipRequired(userDealPost.getMembershipRequired());
				form.setIpAddress(userDealPost.getIpAddress());
				form.setUserId(userDealPost.getUser().getUserId());
				form.setDealCategories(categoryService.getAllDealCategories());
				form.setDealPostId(userDealPost.getId());
				if (null != userDealPost.getLocation()) {
					MailingAddress address = userDealPost.getLocation()
							.getMailingAddress();
					if (address != null) {
						form.setAddressLine1(address.getAddressLine1());
						form.setAddressLine2(address.getAddressLine2());
						form.setState(address.getState());
						form.setCity(address.getCity());
						form.setZip(address.getZip());
						form.setDealType("localDeal");
					} else {
						form.setUrl(userDealPost.getLocation().getUrl());
						form.setDealType("onlineDeal");
					}
				}
				modelAndView.addObject("editDealPostForm", form);

				initializeCommonModel(map);
			}

		} catch (Exception e) {
			logger.fatal(DropUtil.getExceptionDescriptionString(e));
			e.printStackTrace();
			throw new DropException();
		}
		return modelAndView;
	}

	@RequestMapping(value = "/updatePostDrop", method = RequestMethod.POST)
	public String updateDropPost(
			@Valid @ModelAttribute("editDealPostForm") DealPostForm form,
			BindingResult result, ModelMap map, HttpServletRequest request,
			HttpSession session) {

		if (!WebUtil.userAuthorization(session)) {
			return "redirect:/home.htm";
		}

		try {

			String dealType = form.getDealType();

			if (dealType != null) {

				if (dealType.equals(POST_DEAL_TYPE.LOCAL_DEAL.getDealType())) {

					String addressLine1 = form.getAddressLine1();
					String addressLine2 = form.getAddressLine2();
					String state = form.getState();
					String city = form.getCity();
					String zip = form.getZip();

					if (!(addressLine1 != null && addressLine1.length() > 0)) {
						result.rejectValue("addressLine1",
								"NotEmpty.dealPostForm.addressLine1");
					}
					if (!(addressLine2 != null && addressLine2.length() > 0)) {
						result.rejectValue("addressLine2",
								"NotEmpty.dealPostForm.addressLine2");
					}
					if (!(state != null && state.length() > 0)) {
						result.rejectValue("state",
								"NotEmpty.dealPostForm.state");
					}
					if (!(city != null && city.length() > 0)) {
						result.rejectValue("city", "NotEmpty.dealPostForm.city");
					}
					if (!(zip != null && zip.length() > 0)) {
						result.rejectValue("zip", "NotEmpty.dealPostForm.zip");
					}

				} else if (dealType.equals(POST_DEAL_TYPE.ONLINE_DEAL
						.getDealType())) {
					String url = form.getUrl();
					if (!(url != null && url.length() > 0)) {
						result.rejectValue("url", "NotEmpty.dealPostForm.url");
					}
				}
			}

			if (result.hasErrors()) {
				form.setDealCategories(categoryService.getAllDealCategories());
				initializeCommonModel(map);
				return "editDealPostPage";
			} 

			String dateFormat = msgConfig.getProperty("date.format");
			
			Date starts = DropUtil.convertStringToDate(form.getStarts(),
					dateFormat);
			Date expires = DropUtil.convertStringToDate(form.getExpires(),
					dateFormat);

			if (starts.after(expires)) {
				result.rejectValue("starts", "dropstart.after.dropexpiry");
			}
			
			if (starts.compareTo(expires) == 0) {
				result.rejectValue("starts", "dropstart.same.dropexpiry");
			}

			if (form.getSalePrice().compareTo(form.getRetailPrice()) == 1) {
				result.rejectValue("salePrice", "salePrice.more.retailprice");
			}
			
			if (result.hasErrors()) {
				initializeCommonModel(map);
				form.setDealCategories(categoryService.getAllDealCategories());
				return "editDealPostPage";
			}

			form.setIpAddress(DropUtil.getIPAddress(request));
			User user = WebUtil.getSessionUser(session);

			form.setUserId(user.getUserId());
			dealPostService.saveOrUpdate(form);

		} catch (Exception e) {
			logger.fatal(DropUtil.getExceptionDescriptionString(e));
			e.printStackTrace();
			return "error";
		}
		return "redirect:/showMyDropPost.htm";
	}
}
