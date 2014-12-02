package com.drop.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.drop.controller.form.DealPostForm;
import com.drop.dao.domain.User;
import com.drop.service.IDealPostService;
import com.drop.util.DropUtil;

@Controller
public class PostDropController {

	private static final Logger logger = Logger.getLogger(PostDropController.class);
	
	@Autowired
	private IDealPostService dealPostService;

	@RequestMapping(value = "/postdrop", method = RequestMethod.POST)
	public @ResponseBody
	String registerUser(@Valid DealPostForm form, BindingResult result,
			ModelMap map, HttpServletRequest request) {

		if (result.hasErrors()) {
			return DropUtil.getErrorString(result);
		} else {
			try {
				form.setIpAddress(DropUtil.getIPAddress(request));
				HttpSession session = request.getSession(false);
				User user = (User)session.getAttribute("user");
				form.setUserId(user.getUserId());
				dealPostService.saveDealPost(form);
			} catch (Exception e) {
				logger.fatal(DropUtil.getExceptionDescriptionString(e));
				e.printStackTrace();
				return "ERROR";
			}
		}
		return "SUCCESS";
	}
}
