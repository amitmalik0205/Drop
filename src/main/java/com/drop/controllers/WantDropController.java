package com.drop.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.drop.controller.form.DealWantedForm;
import com.drop.service.IDealWantedService;
import com.drop.util.DropUtil;

@Controller
public class WantDropController {
	
	private static final Logger logger = Logger.getLogger(WantDropController.class);
	
	@Autowired
	private IDealWantedService dealWantedService;

	@RequestMapping(value = "/wantdrop", method = RequestMethod.POST)
	public @ResponseBody
	String registerUser(@Valid DealWantedForm form, BindingResult result,
			ModelMap map, HttpServletRequest request) {

		if (result.hasErrors()) {
			return DropUtil.getErrorString(result);
		} else {
			try {
				dealWantedService.saveDealWanted(form);
			} catch (Exception e) {
				logger.fatal(DropUtil.getExceptionDescriptionString(e));
				e.printStackTrace();
				return "ERROR";
			}
		}
		return "SUCCESS";
	}
}
