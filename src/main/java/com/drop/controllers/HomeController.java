package com.drop.controllers;

import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.drop.controller.form.DropRatingForm;
import com.drop.controller.form.ForgotPasswordForm;
import com.drop.controller.form.LoginForm;
import com.drop.controller.form.RegistrationForm;
import com.drop.controller.form.SearchDealForm;
import com.drop.dao.domain.DealCategory;
import com.drop.dao.domain.DealMatch;
import com.drop.dao.domain.DealPost;
import com.drop.dao.domain.User;
import com.drop.dto.DealPostDTO;
import com.drop.dto.DealWantedDTO;
import com.drop.enums.DEAL_MATCH_STATUS;
import com.drop.enums.SORT_TYPE;
import com.drop.exception.DropException;
import com.drop.service.IDealCategoryService;
import com.drop.service.IDealPostService;
import com.drop.service.IDealWantedService;
import com.drop.service.IDropRatingService;
import com.drop.service.ISolrSearchService;
import com.drop.service.IUserRatingService;
import com.drop.util.DropUtil;
import com.drop.util.WebUtil;

@Controller
public class HomeController {

	private static final Logger logger = Logger.getLogger(HomeController.class);

	@Autowired
	private IDealCategoryService categoryService;

	@Autowired
	private IDealWantedService dealWantedService;

	@Autowired
	private IDealPostService dealPostService;

	@Autowired
	private ISolrSearchService solrSearchService;

	@Autowired
	@Qualifier("msgConfig")
	private Properties msgConfig;
		
	@Autowired
	private IUserRatingService userRatingService;
	
	@Autowired
	private IDropRatingService dropRatingService;
	

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String getHomePage(ModelMap map) {
		try {

			prepareModelForHomePage(map);

			List<DealWantedDTO> featuredDealWantedList = solrSearchService
					.getDropsWantedForHome();
			map.addAttribute("featuredDealWantedList", featuredDealWantedList);

			List<DealPostDTO> featuredDealPostList = solrSearchService
					.getDropsForHomePage();
			map.addAttribute("featuredDealPostList", featuredDealPostList);

		} catch (Exception e) {
			logger.fatal(DropUtil.getExceptionDescriptionString(e));
			e.printStackTrace();
		}

		return "home1";
	}

	@RequestMapping(value = "/aboutus", method = RequestMethod.GET)
	public String aboutUs(ModelMap map) {
		try {

			prepareModelForHomePage(map);

		} catch (Exception e) {
			logger.fatal(DropUtil.getExceptionDescriptionString(e));
			e.printStackTrace();
		}
		return "aboutus";
	}

	private void prepareModelForHomePage(ModelMap map) {
		RegistrationForm registerationForm = new RegistrationForm();
		LoginForm loginForm = new LoginForm();
		ForgotPasswordForm forgotPasswordForm = new ForgotPasswordForm();
		SearchDealForm dealForm = new SearchDealForm();

		map.addAttribute("registerationForm", registerationForm);
		map.addAttribute("loginForm", loginForm);
		map.addAttribute("forgotPasswordForm", forgotPasswordForm);
		map.addAttribute("searchDealForm", dealForm);
	}

	@RequestMapping(value = "/searchMore", method = RequestMethod.GET)
	public ModelAndView searchMore(@RequestParam String searchType,
			ModelMap map, HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView("searchResults");

		HttpSession session = request.getSession();
		try {
			SearchDealForm searchDealForm = new SearchDealForm();
			List<DealPostDTO> dealPostListForSearch = null;
			List<DealWantedDTO> dealWantedListForSearch = null;
			searchDealForm.setSearchType(searchType);
			if (searchType.equals("Drops")) {
				dealPostListForSearch = solrSearchService.searchDrops("", 0,
						SORT_TYPE.PRICE, "All");
				map.addAttribute("dealPostListForSearch", dealPostListForSearch);
				searchDealForm.setItemsOnpage(dealPostListForSearch.size());
			} else {
				dealWantedListForSearch = solrSearchService.searchWanted("", 0,
						SORT_TYPE.PRICE, "All");
				map.addAttribute("dealWantedListForSearch",
						dealWantedListForSearch);
				searchDealForm.setItemsOnpage(dealWantedListForSearch.size());
			}

			List<DealCategory> categories = categoryService
					.getAllDealCategories();
			map.addAttribute("dealCategories", categories);

			searchDealForm.setCurrentPage(0);
			searchDealForm.setMaxResultOnPage(new Integer(msgConfig
					.getProperty("search.results.per.page")));
			searchDealForm.setSortType(SORT_TYPE.PRICE);
			searchDealForm.setSelectedCategory("All");
			session.setAttribute("sessionSearchDealForm", searchDealForm);

			prepareModelForHomePage(map);

		} catch (Exception e) {
			logger.fatal(DropUtil.getExceptionDescriptionString(e));
			e.printStackTrace();
			throw new DropException();
		}
		return modelAndView;
	}

	
	@RequestMapping(value = "/viewDropDetails", method = RequestMethod.GET)
	public ModelAndView viewDropDetails(@RequestParam Long dealPostId, ModelMap map) {
		
		ModelAndView modelAndView = new ModelAndView("homePageDropDetails");
		
		try {			
			
			DealPost dealPost = dealPostService.getDealPostWithUserAndRating(dealPostId);
			map.addAttribute("dealPostDetail", dealPost);
						
		} catch (Exception e) {
			logger.fatal(DropUtil.getExceptionDescriptionString(e));
			e.printStackTrace();
			throw new DropException();
		}		
		return modelAndView;
	}
	
	
/*	@RequestMapping(value = "/showDropRatingDialog", method = RequestMethod.GET)
	public ModelAndView showDropRatingDialog(@RequestParam long dealPostId,
			@RequestParam long dealWantedId, ModelMap map) {

		ModelAndView modelAndView = new ModelAndView("dropRatingDialog");

		try {
			DropRatingForm form = new DropRatingForm();
			form.setDealPostId(dealPostId);
			form.setDealWantedId(dealWantedId);
			modelAndView.addObject("dropRatingForm", form);

		} catch (Exception e) {
			logger.fatal(DropUtil.getExceptionDescriptionString(e));
			e.printStackTrace();
			throw new DropException();
		}
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/saveDropRating", method = RequestMethod.POST)
	public String saveDropRating(@ModelAttribute DropRatingForm dropRatingForm,
			ModelMap map) {
		
		try {

			dropRatingService.saveDropRating(dropRatingForm);

		} catch (Exception e) {
			logger.fatal(DropUtil.getExceptionDescriptionString(e));
			e.printStackTrace();
			return "error";
		}
		return "redirect:/viewDealDetails.htm?dealPostId="
				+ dropRatingForm.getDealPostId() + "&dealWantedId="
				+ dropRatingForm.getDealWantedId();
	}*/
}
