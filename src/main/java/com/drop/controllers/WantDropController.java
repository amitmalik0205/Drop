package com.drop.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.drop.controller.form.DealWantedForm;
import com.drop.controller.form.ReasonToDeleteForm;
import com.drop.dao.domain.DealCategory;
import com.drop.dao.domain.DealWanted;
import com.drop.dao.domain.User;
import com.drop.exception.DropException;
import com.drop.service.IDealCategoryService;
import com.drop.service.IDealWantedService;
import com.drop.util.DropUtil;
import com.drop.util.WebUtil;

@Controller
public class WantDropController {

	private static final Logger logger = Logger
			.getLogger(WantDropController.class);

	@Autowired
	private IDealWantedService dealWantedService;

	@Autowired
	private IDealCategoryService categoryService;

	@Autowired
	private HttpSession session;

	private void initializeFormModels(ModelMap map) {

		DealWantedForm dealWantedForm = new DealWantedForm();
		DealPostForm dealPostForm = new DealPostForm();

		List<DealCategory> categories = categoryService.getAllDealCategories();
		dealWantedForm.setDealCategories(categories);
		dealPostForm.setDealCategories(categories);

		map.addAttribute("dealWantedForm", dealWantedForm);
		map.addAttribute("dealPostForm", dealPostForm);
	}

	
	@RequestMapping(value = "/showDealWantedPage", method = RequestMethod.GET)
	public ModelAndView showDealWantedPage(ModelMap map) {
		
		if (!WebUtil.userAuthorization(session)) {
			return new ModelAndView("redirect:/home.htm");
		}

		ModelAndView modelAndView = new ModelAndView("dealWantedPage");

		try {

			DealWantedForm dealWantedForm = new DealWantedForm();
			
			List<DealCategory> categories = categoryService.getAllDealCategories();
			dealWantedForm.setDealCategories(categories);
			
			map.addAttribute("dealWantedForm", dealWantedForm);

		} catch (Exception e) {
			logger.fatal(DropUtil.getExceptionDescriptionString(e));
			e.printStackTrace();
			throw new DropException();
		}
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/wantdrop", method = RequestMethod.POST)
	public String saveDropWanted(@Valid DealWantedForm form, BindingResult result,
			ModelMap map, HttpServletRequest request) {

		Long dealWantedId;
		
		if (!WebUtil.userAuthorization(session)) {
			return "redirect:/home.htm";
		}

		if (result.hasErrors()) {			
			form.setDealCategories(categoryService.getAllDealCategories());
			return "dealWantedPage";
		} else {
			try {
				form.setIpAddress(DropUtil.getIPAddress(request));
				HttpSession session = request.getSession(false);
				User user = (User) session.getAttribute("user");
				form.setUserId(user.getUserId());
				dealWantedId = dealWantedService.saveDealWanted(form);
			} catch (Exception e) {
				logger.fatal(DropUtil.getExceptionDescriptionString(e));
				e.printStackTrace();
				return "error";
			}
		}
		return "redirect:/getMatchingDeals.htm?dropWantedId="+dealWantedId;
	}
	
	
/*	@RequestMapping(value = "/wantdrop", method = RequestMethod.POST)
	public @ResponseBody
	String saveDropWanted(@Valid DealWantedForm form, BindingResult result,
			ModelMap map, HttpServletRequest request) {

		if (!WebUtil.userAuthorization(session)) {
			return "redirect:/home.htm";
		}

		if (result.hasErrors()) {
			return DropUtil.getErrorString(result);
		} else {
			try {
				form.setIpAddress(DropUtil.getIPAddress(request));
				HttpSession session = request.getSession(false);
				User user = (User) session.getAttribute("user");
				form.setUserId(user.getUserId());
				dealWantedService.saveDealWanted(form);
			} catch (Exception e) {
				logger.fatal(DropUtil.getExceptionDescriptionString(e));
				e.printStackTrace();
				return "ERROR";
			}
		}
		return "SUCCESS";
	}*/

	@RequestMapping(value = "/showMyDropWanted", method = RequestMethod.GET)
	public ModelAndView showMyDropWanted(ModelMap map, HttpSession session) {

		if (!WebUtil.userAuthorization(session)) {
			return new ModelAndView("redirect:/home.htm");
		}

		ModelAndView modelAndView = new ModelAndView("myDropWanted");

		try {
			User user = WebUtil.getSessionUser(session);
			List<DealWanted> dealWantedList = dealWantedService
					.getAllDealWantedForUser(user.getUserId());
			modelAndView.addObject("dealWantedList", dealWantedList);
			initializeFormModels(map);

		} catch (Exception e) {
			logger.fatal(DropUtil.getExceptionDescriptionString(e));
			e.printStackTrace();
			throw new DropException();
		}
		return modelAndView;
	}

	@RequestMapping(value = "/showEditDropWanted", method = RequestMethod.GET)
	public ModelAndView showEditDropWantedForm(
			@RequestParam("dropWantedId") Long dropWantedId, ModelMap map,
			HttpSession session) {

		if (!WebUtil.userAuthorization(session)) {
			return new ModelAndView("redirect:/home.htm");
		}

		ModelAndView modelAndView = new ModelAndView("editDealWantedPage");

		try {
			User user = WebUtil.getSessionUser(session);
			DealWanted userDealWanted = null;

			List<DealWanted> dealWantedList = dealWantedService
					.getAllDealWantedForUser(user.getUserId());
			for (DealWanted dealWanted : dealWantedList) {
				if (dealWanted.getId() == dropWantedId) {
					userDealWanted = dealWanted;
					break;
				}
			}

			// If deal belongs to user then allow edit
			if (userDealWanted != null) {
				DealWantedForm form = new DealWantedForm();
				form.setTitle(userDealWanted.getTitle());
				form.setDescription(userDealWanted.getDescription());
				form.setCategory(userDealWanted.getDealCategory().getId());
				form.setTipAmount(userDealWanted.getTipAmount());
				form.setMaxPrice(userDealWanted.getMaxPrice());
				form.setAcceptCoupons(userDealWanted.getAcceptCoupons());
				form.setWouldBuyLocally(userDealWanted.getWouldBuyLocally());
				form.setWouldBuyOnline(userDealWanted.getWouldBuyOnline());
				form.setWantNew(userDealWanted.getWantNew());
				form.setWantUsed(userDealWanted.getWantUsed());
				form.setRefurbishedOK(userDealWanted.getRefurbishedOK());
				form.setDealCategories(categoryService.getAllDealCategories());
				form.setDealWantedId(userDealWanted.getId());

				modelAndView.addObject("editDealWantedForm", form);
			}

		} catch (Exception e) {
			logger.fatal(DropUtil.getExceptionDescriptionString(e));
			e.printStackTrace();
			throw new DropException();
		}
		return modelAndView;
	}

	@RequestMapping(value = "/updateWantdrop", method = RequestMethod.POST)
	public String updateDropWanted(@Valid @ModelAttribute("editDealWantedForm") DealWantedForm form, BindingResult result,
			ModelMap map, HttpServletRequest request, HttpSession session) {

		if (!WebUtil.userAuthorization(session)) {
			return "redirect:/home.htm";
		}

		try {

			if (result.hasErrors()) {
				form.setDealCategories(categoryService.getAllDealCategories());
				return "editDealWantedPage";
			}

			form.setIpAddress(DropUtil.getIPAddress(request));
			User user = WebUtil.getSessionUser(session);
			form.setUserId(user.getUserId());
			dealWantedService.saveOrUpdate(form);

		} catch (Exception e) {
			logger.fatal(DropUtil.getExceptionDescriptionString(e));
			e.printStackTrace();
			return "error";
		}
		return "redirect:/showMyDropWanted.htm";
	}

	@RequestMapping(value = "/showReasonToDeleteDialog", method = RequestMethod.GET)
	public ModelAndView showDialog(@RequestParam Long dealId, ModelMap map,
			HttpSession session) {

		ModelAndView modelAndView = new ModelAndView("deleteDropWantedDialog");

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

	@RequestMapping(value = "/deleteDropWanted", method = RequestMethod.POST)
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
			DealWanted userDealWanted = null;

			List<DealWanted> dealWantedList = dealWantedService
					.getAllDealWantedForUser(user.getUserId());
			for (DealWanted dealWanted : dealWantedList) {
				if (dealWanted.getId() == form.getDealId()) {
					userDealWanted = dealWanted;
					break;
				}
			}

			if (userDealWanted != null) {
				dealWantedService.deleteDealWanted(form);
			}

		} catch (Exception e) {
			logger.fatal(DropUtil.getExceptionDescriptionString(e));
			e.printStackTrace();
			return "ERROR";
		}
		return "SUCCESS";
	}
}
