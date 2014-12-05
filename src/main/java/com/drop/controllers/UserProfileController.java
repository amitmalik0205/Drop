package com.drop.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.drop.controller.form.AccountSettingsForm;
import com.drop.controller.form.AddressBookForm;
import com.drop.controller.form.DealPostForm;
import com.drop.controller.form.DealWantedForm;
import com.drop.dao.domain.DealCategory;
import com.drop.dao.domain.DealWanted;
import com.drop.dao.domain.User;
import com.drop.exception.DropException;
import com.drop.service.IDealCategoryService;
import com.drop.service.IDealWantedService;
import com.drop.service.IUserService;
import com.drop.util.DropUtil;
import com.drop.util.WebUtil;

@Controller
public class UserProfileController {
	
	private static final Logger logger = Logger.getLogger(UserProfileController.class);
	
	@Autowired
	private IUserService userService;	
	
	@Autowired
	private IDealWantedService dealWantedService;
	
	@Autowired
	private IDealCategoryService categoryService;
	
	private void initializeFormModels(ModelMap map) {
		
		DealWantedForm dealWantedForm = new DealWantedForm();
		DealPostForm dealPostForm = new DealPostForm();
		
		List<DealCategory> categories = categoryService.getAllDealCategories();
		dealWantedForm.setDealCategories(categories);
		dealPostForm.setDealCategories(categories);
		
		map.addAttribute("dealWantedForm", dealWantedForm);
		map.addAttribute("dealPostForm", dealPostForm);
	}

	@RequestMapping(value = "/showAccountSettings", method = RequestMethod.GET)
	public ModelAndView showAccountSettingsPage(ModelMap map, HttpSession session) {
		
		ModelAndView modelAndView = new ModelAndView("accountSettings");
		
		try {
			
			User user = WebUtil.getSessionUser(session);
			
			AccountSettingsForm form = new AccountSettingsForm();
			form.setEmail(user.getEmail());
			form.setFirstName(user.getFirstName());
			form.setLastName(user.getLastName());
			form.setPhone(user.getPhoneNumber());
			form.setSkypeName(user.getSkypeName());
			
			modelAndView.addObject("accountSettingsForm", form);
			initializeFormModels(map);

		} catch (Exception e) {
			logger.fatal(DropUtil.getExceptionDescriptionString(e));
			e.printStackTrace();
			throw new DropException();
		}
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/updateAccountSettings", method = RequestMethod.POST)
	public String updateAccountSettings(
			@Valid @ModelAttribute("accountSettingsForm") AccountSettingsForm form,
			BindingResult result, ModelMap map, HttpServletRequest request) {
		
		try {
			if (result.hasErrors()) {
				return "accountSettings";
			}
			
			userService.saveOrUpdateUser(form);
			
		} catch (Exception e) {
			logger.fatal(DropUtil.getExceptionDescriptionString(e));			
			e.printStackTrace();
			throw new DropException();
		}
		return "redirect:/showAccountSettings.htm";
	}
	
	@RequestMapping(value = "/showAddressBook", method = RequestMethod.GET)
	public ModelAndView showAddressBook(ModelMap map, HttpSession session) {
		
		ModelAndView modelAndView = new ModelAndView("accountAddress");
		
		try {
			User user = WebUtil.getSessionUser(session);
			
			AddressBookForm form = new AddressBookForm();
			form.setAddressList(user.getAddresses());
			
			modelAndView.addObject("addressForm", form);
			initializeFormModels(map);

		} catch (Exception e) {
			logger.fatal(DropUtil.getExceptionDescriptionString(e));
			e.printStackTrace();
			throw new DropException();
		}
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/addAddress", method = RequestMethod.POST)
	public @ResponseBody
	String addAddress(
			@Valid @ModelAttribute("addressForm") AddressBookForm form,
			BindingResult result, ModelMap map, HttpServletRequest request) {

		if (result.hasErrors()) {
			return DropUtil.getErrorString(result);
		} else {
			try {

				userService.saveOrUpdateUser(form);

			} catch (Exception e) {
				logger.fatal(DropUtil.getExceptionDescriptionString(e));
				e.printStackTrace();
				return "ERROR";
			}
		}
		return "SUCCESS";
	}
	
	
	@RequestMapping(value = "/deleteAddress", method = RequestMethod.GET)
	public String deleteAddress(ModelMap map, HttpSession session) {
		return "myStatistics";
	}
	
	
	@RequestMapping(value = "/myStatistics", method = RequestMethod.GET)
	public String showStatisticsPage(ModelMap map, HttpSession session) {
		initializeFormModels(map);
		return "myStatistics";
	}

	
	@RequestMapping(value = "/showMyDropWanted", method = RequestMethod.GET)
	public ModelAndView showMyDropWanted(ModelMap map, HttpSession session) {

		ModelAndView modelAndView = new ModelAndView("myDropWanted");

		try {
			User user = WebUtil.getSessionUser(session);
			List<DealWanted> dealWantedList = dealWantedService
					.getAllDealWantedForUser(user.getUserId());
			modelAndView.addObject("dealWantedList", dealWantedList);
			initializeFormModels(map);

		} catch (Exception e) {
			logger.fatal(DropUtil.getExceptionDescriptionString(e));
			e.printStackTrace();
			throw new DropException();
		}
		return modelAndView;
	}
}
