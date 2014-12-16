package com.drop.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.drop.controller.form.DealPostForm;
import com.drop.controller.form.DealWantedForm;
import com.drop.dao.domain.DealCategory;
import com.drop.dao.domain.DealPost;
import com.drop.dao.domain.DealWanted;
import com.drop.dao.domain.User;
import com.drop.exception.DropException;
import com.drop.service.IDealCategoryService;
import com.drop.service.IDealPostService;
import com.drop.service.IDealWantedService;
import com.drop.service.ISolrSearchService;
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
	
	
	private void initializeFormModels(ModelMap map) {

		DealWantedForm dealWantedForm = new DealWantedForm();
		DealPostForm dealPostForm = new DealPostForm();

		List<DealCategory> categories = categoryService.getAllDealCategories();
		dealWantedForm.setDealCategories(categories);
		dealPostForm.setDealCategories(categories);

		map.addAttribute("dealWantedForm", dealWantedForm);
		map.addAttribute("dealPostForm", dealPostForm);
	}
	
	
	@RequestMapping(value = "/getMatchingDeals", method = RequestMethod.GET)
	public ModelAndView getMatchingDeals(@RequestParam("dropWantedId") Long dropWantedId, ModelMap map) {
		
		if(!WebUtil.userAuthorization(session)) {
			return new ModelAndView("redirect:/home.htm");
		}
		
		ModelAndView modelAndView = new ModelAndView("matchingDeals");

		try {
			DealWanted dealWanted = dealWantedService.getDealWantedById(dropWantedId);
			List<DealPost> matchingDealPostList = solrSearchService.search(dealWanted);
			map.addAttribute("dealWantedToMatch", dealWanted);
			map.addAttribute("matchingDealPostList", matchingDealPostList);
			initializeFormModels(map);

		} catch (Exception e) {
			logger.fatal(DropUtil.getExceptionDescriptionString(e));
			e.printStackTrace();
			throw new DropException();
		}
		return modelAndView;
	}
}
