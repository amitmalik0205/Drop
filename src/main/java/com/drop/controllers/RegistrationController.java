package com.drop.controllers;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
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

import com.drop.controller.form.RegistrationForm;
import com.drop.dao.domain.User;
import com.drop.service.IUserService;
import com.drop.util.DropUtil;

@Controller
public class RegistrationController {
	
	private static final Logger logger = Logger.getLogger(RegistrationController.class);
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	@Qualifier("msgConfig")
	private Properties msgConfig;
	

/*	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public @ResponseBody
	String registerUser(@Valid RegisterationForm form, BindingResult result, ModelMap map, HttpServletRequest request) {
		
		JsonResponse jsonReponse = new JsonResponse();
		jsonReponse.setStatus(RESPONSE_STATUS.SUCCESS.getStatus());
		System.out.println("Inside controller");
		
		if (result.hasErrors()) {
			 StringBuilder builder = new StringBuilder("");
			 Map<String, String> errors=new HashMap<String, String>();
	         List<FieldError> fieldErrors = result.getFieldErrors();
	         
	         for (FieldError fieldError : fieldErrors) {
		         builder.append(fieldError.getDefaultMessage());
		         builder.append(System.getProperty("line.separator"));
	         }
	         
	         jsonReponse.setErrorMap(errors);
	         jsonReponse.setStatus(RESPONSE_STATUS.ERROR.getStatus());
	         System.out.println("Inside error");
	         return "Some error occured";
		} else {
			try {
				userService.saveUser(form);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return "its working";
	}*/
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public @ResponseBody
	String registerUser(@Valid RegistrationForm form, BindingResult result,
			ModelMap map, HttpServletRequest request) {

		if (result.hasErrors()) {
			return DropUtil.getErrorString(result);
		} else {
			try {
				User saveUser = userService.getUserByEmail(form.getEmail());
				if (saveUser != null) {
					return msgConfig.getProperty("user.exists");
				}
				userService.saveUser(form);
			} catch (Exception e) {
				logger.fatal(DropUtil.getExceptionDescriptionString(e));
				e.printStackTrace();
				return "ERROR";
			}
		}
		return "SUCCESS";
	}
}
