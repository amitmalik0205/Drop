package com.drop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.drop.controller.form.ForgotPasswordForm;
import com.drop.controller.form.LoginForm;
import com.drop.controller.form.RegistrationForm;

@Controller
public class HomeController {

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String getLoginPage(ModelMap map) {
		RegistrationForm registerationForm = new RegistrationForm();
		LoginForm loginForm = new LoginForm();
		ForgotPasswordForm forgotPasswordForm = new ForgotPasswordForm();
		
		map.addAttribute("registerationForm", registerationForm);
		map.addAttribute("loginForm", loginForm);
		map.addAttribute("forgotPasswordForm", forgotPasswordForm);
		
		return "home1";
	}
}
