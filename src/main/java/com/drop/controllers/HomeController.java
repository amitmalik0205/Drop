package com.drop.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.drop.controller.form.ForgotPasswordForm;
import com.drop.controller.form.LoginForm;
import com.drop.controller.form.RegistrationForm;
import com.drop.controller.form.SearchDealForm;
import com.drop.dao.domain.DealPost;
import com.drop.dao.domain.DealWanted;
import com.drop.service.IDealCategoryService;
import com.drop.service.IDealPostService;
import com.drop.service.IDealWantedService;
import com.drop.util.DropUtil;

@Controller
public class HomeController {
	
	private static final Logger logger = Logger.getLogger(HomeController.class);
	
	@Autowired
	private IDealCategoryService categoryService;
	
	@Autowired
	private IDealWantedService dealWantedService;
	
	@Autowired
	private IDealPostService dealPostService;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String getHomePage(ModelMap map) {
		try {
			
			prepareModelForHomePage(map);
			
			List<DealWanted> featuredDealWantedList = dealWantedService.getAllDealWanted();
			map.addAttribute("featuredDealWantedList", featuredDealWantedList);
			
			List<DealPost> featuredDealPostList = dealPostService.getAllDealPost();
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
}
