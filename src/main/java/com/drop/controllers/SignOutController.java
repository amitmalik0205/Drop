package com.drop.controllers;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.drop.dao.domain.User;
import com.drop.util.WebUtil;

@Controller
public class SignOutController {

	private static final Logger logger = Logger
			.getLogger(SignOutController.class);

	@RequestMapping(value = "/signOut", method = RequestMethod.GET)
	public String signOut(HttpSession session) {
		
		User user = WebUtil.getSessionUser(session);
		
		if(user != null) {
			logger.info("Signing out user :"
					+ ((User) session.getAttribute("user")).getEmail());
			session.invalidate();
		}		
		
		return "redirect:/home.htm";
	}
}
