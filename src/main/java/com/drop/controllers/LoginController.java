package com.drop.controllers;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.drop.controller.form.ForgotPasswordForm;
import com.drop.controller.form.LoginForm;
import com.drop.dao.domain.User;
import com.drop.service.IDealWantedService;
import com.drop.service.IUserService;
import com.drop.util.DropUtil;

@Controller
public class LoginController {
	
	private static final Logger logger = Logger.getLogger(LoginController.class);

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IDealWantedService dealWantedService;
	
	@Autowired
	@Qualifier("msgConfig")
	private Properties msgConfig;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody
	String registerUser(@Valid LoginForm form, BindingResult result,
			ModelMap map, HttpServletRequest request) {

		if (result.hasErrors()) {
			return DropUtil.getErrorString(result);
		} else {
			try {
				User savedUser = userService.authenticateUser(form);
				if (savedUser == null) {
					return msgConfig.getProperty("authenticatin.failed");
				}
				HttpSession session = request.getSession();
				session.setAttribute("user", savedUser);
			} catch (Exception e) {
				e.printStackTrace();
				logger.fatal(DropUtil.getExceptionDescriptionString(e));
				return "ERROR";
			}
		}		
		return "SUCCESS";
	}
	
	@RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
	public @ResponseBody
	String registerUser(@Valid ForgotPasswordForm form, BindingResult result, ModelMap map, HttpServletRequest request) {
		
		if (result.hasErrors()) {
			return DropUtil.getErrorString(result);
		} else {
			try {
				User savedUser = userService.getUserByEmail(form.getEmail());
				if(savedUser == null) {
					return msgConfig.getProperty("user.not.exists");
				} else {
					userService.sendForgotPasswordEmail(savedUser);
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.fatal(DropUtil.getExceptionDescriptionString(e));
				return "ERROR";
			}			
		}
		return "SUCCESS";
	}
}
