package com.drop.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.drop.controller.form.DealMatchForm;
import com.drop.controller.form.SearchDealForm;
import com.drop.controller.form.UserRatingForm;
import com.drop.dao.domain.DealMatch;
import com.drop.dao.domain.DealPost;
import com.drop.dao.domain.DealWanted;
import com.drop.dao.domain.User;
import com.drop.enums.DEAL_MATCH_STATUS;
import com.drop.exception.DropException;
import com.drop.service.IDealCategoryService;
import com.drop.service.IDealMatchService;
import com.drop.service.IDealPostService;
import com.drop.service.IDealWantedService;
import com.drop.service.ISolrSearchService;
import com.drop.service.IUserRatingService;
import com.drop.util.DropUtil;
import com.drop.util.WebUtil;

@Controller
public class MatchingDealsController {
	
	private static final Logger logger = Logger.getLogger(MatchingDealsController.class);	
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private IDealWantedService dealWantedService;
	
	@Autowired
	private IDealPostService dealPostService;
	
	@Autowired
	private ISolrSearchService solrSearchService;
	
	@Autowired
	private IDealCategoryService categoryService;
	
	@Autowired
	private IDealMatchService dealMatchService;
	
	@Autowired
	private IUserRatingService userRatingService;
	
/*	private void initializeFormModels(ModelMap map) {

		DealWantedForm dealWantedForm = new DealWantedForm();
		DealPostForm dealPostForm = new DealPostForm();

		List<DealCategory> categories = categoryService.getAllDealCategories();
		dealWantedForm.setDealCategories(categories);
		dealPostForm.setDealCategories(categories);

		map.addAttribute("dealWantedForm", dealWantedForm);
		map.addAttribute("dealPostForm", dealPostForm);
	}*/
	
	
	@RequestMapping(value = "/getMatchingDeals", method = RequestMethod.GET)
	public ModelAndView getMatchingDeals(@RequestParam("dropWantedId") Long dropWantedId, ModelMap map) {
		
		if(!WebUtil.userAuthorization(session)) {
			return new ModelAndView("redirect:/home.htm");
		}
		
		ModelAndView modelAndView = new ModelAndView("matchingDeals");

		try {
			DealWanted dealWanted = dealWantedService.getDealWantedById(dropWantedId);
			List<DealPost> matchingDealPostList = solrSearchService.search(dealWanted,0);
			map.addAttribute("dealWantedToMatch", dealWanted);
			map.addAttribute("matchingDealPostList", matchingDealPostList);			

			SearchDealForm dealForm = new SearchDealForm();		
			map.addAttribute("searchDealForm", dealForm);
			
		} catch (Exception e) {
			logger.fatal(DropUtil.getExceptionDescriptionString(e));
			e.printStackTrace();
			throw new DropException();
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/acceptDeal", method = RequestMethod.POST)
	public @ResponseBody
	String acceptDeal(@ModelAttribute DealMatchForm form) {
		try {
			dealMatchService.saveDealMatch(form);
		} catch (Exception e) {
			logger.fatal(DropUtil.getExceptionDescriptionString(e));
			e.printStackTrace();
			return "ERROR";
		}
		return "SUCCESS";
	}
	
	
	@RequestMapping(value = "/showReasonToRejectDialog", method = RequestMethod.GET)
	public ModelAndView showReasonToDeleteDialog(@RequestParam Long dealPostId,
			@RequestParam Long dealWantedId, ModelMap map, HttpSession session) {

		if (!WebUtil.userAuthorization(session)) {
			return new ModelAndView("redirect:/home.htm");
		}
		
		ModelAndView modelAndView = new ModelAndView("rejectDealMatchDialog");

		try {			
			DealMatchForm dealMatchForm = new DealMatchForm();
			dealMatchForm.setDealWantedId(dealWantedId);
			dealMatchForm.setDealPostId(dealPostId);
			modelAndView.addObject("reasonToRejectDealForm", dealMatchForm);				
			
		} catch (Exception e) {
			logger.fatal(DropUtil.getExceptionDescriptionString(e));
			e.printStackTrace();
			throw new DropException();
		}
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/rejectDeal", method = RequestMethod.POST)
	public @ResponseBody
	String rejectDealMatch(DealMatchForm form, ModelMap map) {

		if (!WebUtil.userAuthorization(session)) {
			return "redirect:/home.htm";
		}

		try {
			User sessionUser = WebUtil.getSessionUser(session);

			long dealWantedId = form.getDealWantedId();
			long dealPostId = form.getDealPostId();
			
			//If deal wanted belong to user then allow the delete
			DealMatch savedDealMatch = dealMatchService.getDealMatchWithUserByDealWanted(dealWantedId, dealPostId);
			
			if(savedDealMatch != null) {
				User savedUserForDealMatch = savedDealMatch.getDealWanted().getUser();
				
				if(sessionUser.getUserId() == savedUserForDealMatch.getUserId()) {
					savedDealMatch.setStatus(DEAL_MATCH_STATUS.REJECTED);
					savedDealMatch.setRejectedReason(form.getReasonToReject());
					dealMatchService.saveOrUpdate(savedDealMatch);
				}
			}					

		} catch (Exception e) {
			logger.fatal(DropUtil.getExceptionDescriptionString(e));
			e.printStackTrace();
			return "ERROR";
		}
		return "SUCCESS";
	}
	
	
	@RequestMapping(value = "/viewDealDetails", method = RequestMethod.GET)
	public ModelAndView viewDealDetails(@RequestParam Long dealPostId, @RequestParam Long dealWantedId, ModelMap map) {
		
		if(!WebUtil.userAuthorization(session)) {
			return new ModelAndView("redirect:/home.htm");
		}
		
		ModelAndView modelAndView = new ModelAndView("dealDetails");
		
		try {			
			//Check if deal is accepted by the user
			User sessionUser = WebUtil.getSessionUser(session);
			DealMatch savedDealMatch = dealMatchService.getDealMatchWithUserByDealWanted(dealWantedId, dealPostId);
			
			if (savedDealMatch != null
					&& savedDealMatch.getDealWanted().getUser().getUserId() == sessionUser
							.getUserId()
					&& savedDealMatch.getStatus() == DEAL_MATCH_STATUS.ACCEPTED) {
				
				DealPost dealPost = dealPostService.getDealPostWithUser(dealPostId);
				map.addAttribute("dealPostDetail", dealPost);
				map.addAttribute("dealWantedToMatch", savedDealMatch.getDealWanted());
			} 
			
			SearchDealForm dealForm = new SearchDealForm();		
			map.addAttribute("searchDealForm", dealForm);
			
		} catch (Exception e) {
			logger.fatal(DropUtil.getExceptionDescriptionString(e));
			e.printStackTrace();
			throw new DropException();
		}		
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/showUserRatingDialog", method = RequestMethod.GET)
	public ModelAndView showUserRatingDialog(@RequestParam Long dealMatchId,
			ModelMap map, HttpSession session) {

		if (!WebUtil.userAuthorization(session)) {
			return new ModelAndView("redirect:/home.htm");
		}

		ModelAndView modelAndView = new ModelAndView("userReviewDialog");

		try {
			UserRatingForm form = new UserRatingForm();
			form.setDealMatchId(dealMatchId);
			modelAndView.addObject("userRatingForm", form);

		} catch (Exception e) {
			logger.fatal(DropUtil.getExceptionDescriptionString(e));
			e.printStackTrace();
			throw new DropException();
		}
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/saveUserRating", method = RequestMethod.POST)
	public String saveUserRating(@ModelAttribute UserRatingForm userRatingForm, ModelMap map) {

		long dealWantedId = 0;
		
		if (!WebUtil.userAuthorization(session)) {
			return "redirect:/home.htm";
		}

		try {
			User sessionUser = WebUtil.getSessionUser(session);
			
			//If deal match belong to user then allow the review
			DealMatch savedDealMatch = dealMatchService.getDealMatchWithDealWanted(userRatingForm.getDealMatchId());
			
			if(savedDealMatch != null) {
				dealWantedId = 	savedDealMatch.getDealWanted().getId();			
				User savedUserForDealMatch = savedDealMatch.getDealWanted().getUser();
				
				if(sessionUser.getUserId() == savedUserForDealMatch.getUserId()) {
					savedDealMatch.setStatus(DEAL_MATCH_STATUS.GOTIT);					
					userRatingService.saveUserRating(userRatingForm, savedDealMatch);
				}
			}					

		} catch (Exception e) {
			logger.fatal(DropUtil.getExceptionDescriptionString(e));
			e.printStackTrace();
			return "error";
		}
		return "redirect:/getMatchingDeals.htm?dropWantedId="+dealWantedId;
	}
}
