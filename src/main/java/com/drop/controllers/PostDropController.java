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

import com.drop.controller.form.DealPostForm;
import com.drop.dao.domain.User;
import com.drop.enums.POST_DEAL_TYPE;
import com.drop.service.IDealPostService;
import com.drop.util.DropUtil;
import com.drop.util.WebUtil;

@Controller
public class PostDropController {

	private static final Logger logger = Logger.getLogger(PostDropController.class);
	
	@Autowired
	private IDealPostService dealPostService;
	
	@Autowired
	@Qualifier("validationConfig")
	private Properties validationConfig;
	
	@Autowired
	private HttpSession session;

	@RequestMapping(value = "/postdrop", method = RequestMethod.POST)
	public @ResponseBody
	String registerUser(@Valid DealPostForm form, BindingResult result,
			ModelMap map, HttpServletRequest request) {
		
		if(!WebUtil.userAuthorization(session)) {
			return "redirect:/home.htm";
		}
		
		try {			
			String dealType = form.getDealType();
			
			if(dealType != null) {
				
				if(dealType.equals(POST_DEAL_TYPE.LOCAL_DEAL.getDealType())) {
					
					String addressLine1 = form.getAddressLine1();
					String addressLine2 = form.getAddressLine2();
					String state = form.getState();
					String city = form.getCity();
					String zip = form.getZip();
					
					if(!(addressLine1 != null && addressLine1.length() > 0)) {
						result.rejectValue("addressLine1", validationConfig.getProperty("NotEmpty.dealPostForm.addressLine1"));
					} 
					if (!(addressLine2 != null && addressLine2.length() > 0)) {
						result.rejectValue("addressLine2", validationConfig.getProperty("NotEmpty.dealPostForm.addressLine2"));
					}
					if (!(state != null && state.length() > 0)) {
						result.rejectValue("state", validationConfig.getProperty("NotEmpty.dealPostForm.state"));
					}
					if (!(city != null && city.length() > 0)) {
						result.rejectValue("city", validationConfig.getProperty("NotEmpty.dealPostForm.city"));
					}
					if (!(zip != null && zip.length() > 0)) {
						result.rejectValue("zip", validationConfig.getProperty("NotEmpty.dealPostForm.zip"));
					}
					
				} else if(dealType.equals(POST_DEAL_TYPE.ONLINE_DEAL.getDealType())) {
					String url = form.getUrl();
					if (!(url != null && url.length() > 0)) {
						result.rejectValue("url", validationConfig.getProperty("NotEmpty.dealPostForm.url"));
					}
				}
			}
			
			if (result.hasErrors()) {
				return DropUtil.getErrorString(result);
			} else {
				
				form.setIpAddress(DropUtil.getIPAddress(request));
				HttpSession session = request.getSession(false);
				User user = (User)session.getAttribute("user");
				form.setUserId(user.getUserId());
				dealPostService.saveDealPost(form);
			}
			
		} catch (Exception e) {
			logger.fatal(DropUtil.getExceptionDescriptionString(e));
			e.printStackTrace();
			return "ERROR";
		}
		return "SUCCESS";
	}
}
