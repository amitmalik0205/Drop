package com.drop.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.drop.controller.form.RegisterationForm;
import com.drop.enums.RESPONSE_STATUS;
import com.drop.json.response.JsonResponse;
import com.drop.service.IUserService;

@Controller
public class RegisterationController {
	
	@Autowired
	private IUserService userService;
	

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public @ResponseBody
	String registerUser(@Valid RegisterationForm form, BindingResult result, ModelMap map, HttpServletRequest request) {
		
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
	         return "Some error occured";
		} else {
			try {
				userService.saveUser(form);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return "its working";
	}
}
