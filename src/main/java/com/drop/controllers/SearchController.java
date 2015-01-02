package com.drop.controllers;

import java.util.List;
import java.util.Properties;

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

import com.drop.controller.form.SearchDealForm;
import com.drop.dao.domain.DealCategory;
import com.drop.dto.DealPostDTO;
import com.drop.dto.DealWantedDTO;
import com.drop.enums.SORT_TYPE;
import com.drop.exception.DropException;
import com.drop.service.IDealCategoryService;
import com.drop.service.ISolrSearchService;
import com.drop.util.DropUtil;

@Controller
public class SearchController {
	
	private static final Logger logger = Logger.getLogger(SearchController.class);
	
	@Autowired
	private IDealCategoryService categoryService;
	
	@Autowired
	private ISolrSearchService solrSearchService;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	@Qualifier("msgConfig")
	private Properties msgConfig;


	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView getMatchingDeals(
			@ModelAttribute("searchDealForm") SearchDealForm searchDealForm,
			ModelMap map) {

		ModelAndView modelAndView = new ModelAndView("searchResults");

		try {			
			List<DealPostDTO> dealPostListForSearch = null;
			List<DealWantedDTO> dealWantedListForSearch = null;
			
			String searchType = searchDealForm.getSearchType();
			String searchStr = searchDealForm.getSearchString();				
			
			if(searchType.equals("Drops")) {
				dealPostListForSearch = solrSearchService.searchDrops(searchStr, 0, SORT_TYPE.PRICE, "All");
				map.addAttribute("dealPostListForSearch",dealPostListForSearch);
				searchDealForm.setItemsOnpage(dealPostListForSearch.size());
			} else {
				dealWantedListForSearch = solrSearchService.searchWanted(searchStr, 0, SORT_TYPE.PRICE, "All");
				map.addAttribute("dealWantedListForSearch",dealWantedListForSearch);
				searchDealForm.setItemsOnpage(dealWantedListForSearch.size());
			}
			
			List<DealCategory> categories = categoryService.getAllDealCategories();
			map.addAttribute("dealCategories", categories);
			
			searchDealForm.setCurrentPage(0);
			searchDealForm.setMaxResultOnPage(new Integer(msgConfig.getProperty("search.results.per.page")));
			searchDealForm.setSortType(SORT_TYPE.PRICE);
			searchDealForm.setSelectedCategory("All");
			session.setAttribute("sessionSearchDealForm", searchDealForm);			

		} catch (Exception e) {
			logger.fatal(DropUtil.getExceptionDescriptionString(e));
			e.printStackTrace();
			throw new DropException();
		}
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/searchByCategory", method = RequestMethod.GET)
	public ModelAndView searchByCategory(@RequestParam("categoryName") String categoryName,
			ModelMap map) {

		ModelAndView modelAndView = new ModelAndView("searchResults");

		try {
			
			SearchDealForm sessionDealForm = (SearchDealForm)session.getAttribute("sessionSearchDealForm");
			
			List<DealPostDTO> dealPostListForSearch = null;
			List<DealWantedDTO> dealWantedListForSearch = null;
			
			String searchType = sessionDealForm.getSearchType();
			String searchStr = sessionDealForm.getSearchString();
			SORT_TYPE sortType =  sessionDealForm.getSortType();
			int currentPageNo = sessionDealForm.getCurrentPage();
			
			sessionDealForm.setSelectedCategory(categoryName);
			
			if(searchType.equals("Drops")) {
				dealPostListForSearch = solrSearchService.searchDrops(searchStr, currentPageNo, sortType, categoryName);
				map.addAttribute("dealPostListForSearch",dealPostListForSearch);
				sessionDealForm.setItemsOnpage(dealPostListForSearch.size());
			} else {
				dealWantedListForSearch = solrSearchService.searchWanted(searchStr, currentPageNo, sortType, categoryName);
				map.addAttribute("dealWantedListForSearch",dealWantedListForSearch);
				sessionDealForm.setItemsOnpage(dealWantedListForSearch.size());
			}		
			
			List<DealCategory> categories = categoryService
					.getAllDealCategories();
			map.addAttribute("dealCategories", categories);			
			map.addAttribute("searchDealForm", sessionDealForm);
						

		} catch (Exception e) {
			logger.fatal(DropUtil.getExceptionDescriptionString(e));
			e.printStackTrace();
			throw new DropException();
		}
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/sortSearch", method = RequestMethod.GET)
	public ModelAndView sortSearch(@RequestParam("sortBy") String sortBy, ModelMap map) {

		ModelAndView modelAndView = new ModelAndView("searchResults");

		try {
			
			SearchDealForm sessionDealForm = (SearchDealForm)session.getAttribute("sessionSearchDealForm");
			
			List<DealPostDTO> dealPostListForSearch = null;
			List<DealWantedDTO> dealWantedListForSearch = null;
			
			String searchType = sessionDealForm.getSearchType();
			String searchStr = sessionDealForm.getSearchString();
			String categoryName = sessionDealForm.getSelectedCategory();
			int currentPageNo = sessionDealForm.getCurrentPage();
			
			SORT_TYPE sortType = null;
			
			if(sortBy.equals(SORT_TYPE.PRICE.getSortType())) {
				sortType = SORT_TYPE.PRICE;
			} else if(sortBy.equals(SORT_TYPE.TITLE.getSortType())) {
				sortType = SORT_TYPE.TITLE;
			} else {
				sortType = SORT_TYPE.DATE;
			}
					
			sessionDealForm.setSortType(sortType);
			
			if(searchType.equals("Drops")) {
				dealPostListForSearch = solrSearchService.searchDrops(searchStr, currentPageNo, sortType, categoryName);
				map.addAttribute("dealPostListForSearch",dealPostListForSearch);
				sessionDealForm.setItemsOnpage(dealPostListForSearch.size());
			} else {
				dealWantedListForSearch = solrSearchService.searchWanted(searchStr, currentPageNo, sortType, categoryName);
				map.addAttribute("dealWantedListForSearch",dealWantedListForSearch);
				sessionDealForm.setItemsOnpage(dealWantedListForSearch.size());
			}		
			
			List<DealCategory> categories = categoryService
					.getAllDealCategories();
			map.addAttribute("dealCategories", categories);			
			map.addAttribute("searchDealForm", sessionDealForm);
						
		} catch (Exception e) {
			logger.fatal(DropUtil.getExceptionDescriptionString(e));
			e.printStackTrace();
			throw new DropException();
		}
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/searchByPage", method = RequestMethod.GET)
	public ModelAndView searchByCategory(@RequestParam("pageNo") int pageNo, ModelMap map) {

		ModelAndView modelAndView = new ModelAndView("searchResults");

		try {
			
			SearchDealForm sessionDealForm = (SearchDealForm)session.getAttribute("sessionSearchDealForm");
			
			List<DealPostDTO> dealPostListForSearch = null;
			List<DealWantedDTO> dealWantedListForSearch = null;
			
			String searchType = sessionDealForm.getSearchType();
			String searchStr = sessionDealForm.getSearchString();
			SORT_TYPE sortType =  sessionDealForm.getSortType();
			String categoryName = sessionDealForm.getSelectedCategory();
			
			sessionDealForm.setCurrentPage(pageNo);
			
			if(searchType.equals("Drops")) {
				dealPostListForSearch = solrSearchService.searchDrops(searchStr, pageNo, sortType, categoryName);
				map.addAttribute("dealPostListForSearch",dealPostListForSearch);
				sessionDealForm.setItemsOnpage(dealPostListForSearch.size());
			} else {
				dealWantedListForSearch = solrSearchService.searchWanted(searchStr, pageNo, sortType, categoryName);
				map.addAttribute("dealWantedListForSearch",dealWantedListForSearch);
				sessionDealForm.setItemsOnpage(dealWantedListForSearch.size());
			}		
			
			List<DealCategory> categories = categoryService
					.getAllDealCategories();
			map.addAttribute("dealCategories", categories);			
			map.addAttribute("searchDealForm", sessionDealForm);
						

		} catch (Exception e) {
			logger.fatal(DropUtil.getExceptionDescriptionString(e));
			e.printStackTrace();
			throw new DropException();
		}
		return modelAndView;
	}
}
