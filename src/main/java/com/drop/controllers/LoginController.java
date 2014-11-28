package com.drop.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.drop.controller.form.ForgotPasswordForm;
import com.drop.controller.form.LoginForm;
import com.drop.dao.domain.User;
import com.drop.enums.RESPONSE_STATUS;
import com.drop.json.response.JsonResponse;
import com.drop.service.IUserService;

@Controller
public class LoginController {
	
	private static final Logger logger = Logger.getLogger(LoginController.class);

	@Autowired
	private IUserService userService;
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public @ResponseBody Employee getDummyEmployee() {
		logger.info("Start getDummyEmployee");
		Employee emp = new Employee();
		emp.setId(9999);
		emp.setName("Dummy");
		emp.setCreatedDate(new Date());
		return emp;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody
	String registerUser(@Valid LoginForm form, BindingResult result, ModelMap map, HttpServletRequest request) {
		
		JsonResponse jsonReponse = new JsonResponse();
		jsonReponse.setStatus(RESPONSE_STATUS.SUCCESS.getStatus());
		System.out.println("Inside controller");
		
		if (result.hasErrors()) {
	
			 Map<String, String> errors=new HashMap<String, String>();
	         List<FieldError> fieldErrors = result.getFieldErrors();
	         
	         for (FieldError fieldError : fieldErrors) {
		         errors.put(fieldError.getField(), fieldError.getDefaultMessage()); 
	         }
	         
	         jsonReponse.setErrorMap(errors);
	         jsonReponse.setStatus(RESPONSE_STATUS.ERROR.getStatus());
	         System.out.println("Inside error");
	         return "Some error occured in login";
		} else {
			try {
				User savedUser = userService.authenticateUser(form);
				if(savedUser == null) {
					return "authentication failed";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return "user is authenticated";
	}
	
	@RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
	public @ResponseBody
	String registerUser(@Valid ForgotPasswordForm form, BindingResult result, ModelMap map, HttpServletRequest request) {
		
		JsonResponse jsonReponse = new JsonResponse();
		jsonReponse.setStatus(RESPONSE_STATUS.SUCCESS.getStatus());
		System.out.println("Inside controller");
		
		if (result.hasErrors()) {
	
			 Map<String, String> errors=new HashMap<String, String>();
	         List<FieldError> fieldErrors = result.getFieldErrors();
	         
	         for (FieldError fieldError : fieldErrors) {
		         errors.put(fieldError.getField(), fieldError.getDefaultMessage()); 
	         }
	         
	         jsonReponse.setErrorMap(errors);
	         jsonReponse.setStatus(RESPONSE_STATUS.ERROR.getStatus());
	         System.out.println("Inside error");
	         return "Some error occured in forgot password";
		} else {
			try {
				User savedUser = userService.getUserByEmail(form.getEmail());
				if(savedUser == null) {
					return "No user found";
				} else {
					
					userService.sendForgotPasswordEmail(savedUser);
				}
			} catch (Exception e) {
				e.printStackTrace();
				return "error while sending email";
			}
			
		}
		
		return "user password has been sent to your email id";
	}
}
