package com.drop.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.drop.controller.form.DealWantedForm;
import com.drop.controller.form.ForgotPasswordForm;
import com.drop.controller.form.LoginForm;
import com.drop.controller.form.RegistrationForm;
import com.drop.service.IDealCategoryService;
import com.drop.util.DropUtil;

@Controller
public class HomeController {
	
	private static final Logger logger = Logger.getLogger(HomeController.class);
	
	@Autowired
	private IDealCategoryService categoryService;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String getLoginPage(ModelMap map) {
		try {
			RegistrationForm registerationForm = new RegistrationForm();
			LoginForm loginForm = new LoginForm();
			ForgotPasswordForm forgotPasswordForm = new ForgotPasswordForm();
			DealWantedForm dealWantedForm = new DealWantedForm();
			
			dealWantedForm.setDealCategories(categoryService.getAllDealCategories());
			
			map.addAttribute("registerationForm", registerationForm);
			map.addAttribute("loginForm", loginForm);
			map.addAttribute("forgotPasswordForm", forgotPasswordForm);
			map.addAttribute("dealWantedForm", dealWantedForm);
			
		} catch (Exception e) {
			logger.fatal(DropUtil.getExceptionDescriptionString(e));
			e.printStackTrace();
		}
		
		return "home1";
	}
}