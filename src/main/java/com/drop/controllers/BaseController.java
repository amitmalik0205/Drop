package com.drop.controllers;

import org.springframework.ui.ModelMap;

import com.drop.controller.form.ForgotPasswordForm;
import com.drop.controller.form.LoginForm;
import com.drop.controller.form.RegistrationForm;
import com.drop.controller.form.SearchDealForm;

public class BaseController {

	protected void initializeCommonModel(ModelMap map) {
		SearchDealForm dealForm = new SearchDealForm();
		map.addAttribute("searchDealForm", dealForm);
	}
	
	protected void prepareModelForHomePage(ModelMap map) {		
		RegistrationForm registerationForm = new RegistrationForm();
		LoginForm loginForm = new LoginForm();
		ForgotPasswordForm forgotPasswordForm = new ForgotPasswordForm();
		
		map.addAttribute("registerationForm", registerationForm);
		map.addAttribute("loginForm", loginForm);
		map.addAttribute("forgotPasswordForm", forgotPasswordForm);		
	}
}
