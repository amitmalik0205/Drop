package com.drop.controllers;

import java.util.Set;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.drop.controller.form.AccountSettingsForm;
import com.drop.controller.form.AddressBookForm;
import com.drop.dao.domain.MailingAddress;
import com.drop.dao.domain.User;
import com.drop.exception.DropException;
import com.drop.service.IDealCategoryService;
import com.drop.service.IDealWantedService;
import com.drop.service.IMailingAddressService;
import com.drop.service.IUserService;
import com.drop.util.DropUtil;
import com.drop.util.WebUtil;

@Controller
public class UserProfileController extends BaseController {
	
	private static final Logger logger = Logger.getLogger(UserProfileController.class);
	
	@Autowired
	private IUserService userService;	
	
	@Autowired
	private IDealWantedService dealWantedService;
	
	@Autowired
	private IDealCategoryService categoryService;
	
	@Autowired
	private IMailingAddressService mailingAddressService;
	
	@Autowired
	private HttpSession session;


	@RequestMapping(value = "/showAccountSettings", method = RequestMethod.GET)
	public ModelAndView showAccountSettingsPage(ModelMap map, HttpSession session) {
		
		if(!WebUtil.userAuthorization(session)) {
			return new ModelAndView("redirect:/home.htm");
		}
		
		ModelAndView modelAndView = new ModelAndView("accountSettings");
		
		try {
			
			User user = WebUtil.getSessionUser(session);
			
			AccountSettingsForm form = new AccountSettingsForm();
			form.setFirstName(user.getFirstName());
			form.setLastName(user.getLastName());
			form.setPhone(user.getPhoneNumber());
			form.setSkypeName(user.getSkypeName());
			
			modelAndView.addObject("accountSettingsForm", form);
			
			initializeCommonModel(map);

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
		
		if(!WebUtil.userAuthorization(session)) {
			return "redirect:/home.htm";
		}
		
		try {
			if (result.hasErrors()) {
				initializeCommonModel(map);
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
		
		if(!WebUtil.userAuthorization(session)) {
			return new ModelAndView("redirect:/home.htm");
		}
		
		ModelAndView modelAndView = new ModelAndView("accountAddress");
		
		try {
			User user = WebUtil.getSessionUser(session);
			
			AddressBookForm form = new AddressBookForm();
			form.setAddressList(user.getAddresses());
			
			modelAndView.addObject("addressForm", form);
			
			initializeCommonModel(map);

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
		
		if(!WebUtil.userAuthorization(session)) {
			return "redirect:/home.htm";
		}

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
		
	@RequestMapping(value = "/showEditAddress", method = RequestMethod.GET)
	public ModelAndView showEditAddressForm(@RequestParam("addressId") Long addressId,  ModelMap map, HttpSession session) {
		
		if(!WebUtil.userAuthorization(session)) {
			return new ModelAndView("redirect:/home.htm");
		}
		
		ModelAndView modelAndView = new ModelAndView("editAddress");
		
		try {
			User user = WebUtil.getSessionUser(session);
			MailingAddress userAddress = null;
			
			Set<MailingAddress> addresses = user.getAddresses();
			for(MailingAddress address : addresses) {
				if(address.getId() == addressId) {
					userAddress = address;
					break;
				}
			}
			
			// If address belongs to user then allow edit
			if(userAddress != null) {
				AddressBookForm form = new AddressBookForm();
				form.setAddressLine1(userAddress.getAddressLine1());
				form.setAddressLine2(userAddress.getAddressLine2());
				form.setState(userAddress.getCity());
				form.setCity(userAddress.getCity());
				form.setZip(userAddress.getZip());
				form.setAddressId(userAddress.getId());
				
				modelAndView.addObject("editAddressForm", form);
			}						

		} catch (Exception e) {
			logger.fatal(DropUtil.getExceptionDescriptionString(e));
			e.printStackTrace();
			throw new DropException();
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/updateAddress", method = RequestMethod.POST)
	public @ResponseBody
	String updateAddress(@Valid AddressBookForm form, BindingResult result,
			ModelMap map, HttpSession session) {
		
		if(!WebUtil.userAuthorization(session)) {
			return "redirect:/home.htm";
		}
		
		try {
			
			if (result.hasErrors()) {
				return DropUtil.getErrorString(result);
			}
			
			User user = WebUtil.getSessionUser(session);
			MailingAddress userAddress = null;
			
			Set<MailingAddress> addresses = user.getAddresses();
			for(MailingAddress address : addresses) {
				if(address.getId() == form.getAddressId()) {
					userAddress = address;
					break;
				}
			}
			
			// If address belongs to user then allow update
			if(userAddress != null) {				
				userAddress.setAddressLine1(form.getAddressLine1());
				userAddress.setAddressLine2(form.getAddressLine2());
				userAddress.setState(form.getState());
				userAddress.setCity(form.getCity());
				userAddress.setZip(form.getZip());
				
				mailingAddressService.saveOrUpdate(userAddress);
			}								

		} catch (Exception e) {
			logger.fatal(DropUtil.getExceptionDescriptionString(e));
			e.printStackTrace();
			return "ERROR";
		}
		
		return "SUCCESS";
	}
	
	
	@RequestMapping(value = "/showDeleteConfirmDialog", method = RequestMethod.GET)
	public String showDialog(@RequestParam Long addressId, ModelMap map, HttpSession session) {
		
		if(!WebUtil.userAuthorization(session)) {
			return "redirect:/home.htm";
		}
		
		map.addAttribute("addressIdToDelete", addressId);
		
		return "deleteConfirmationDialog";
	}
	
	
	@RequestMapping(value = "/deleteAddress", method = RequestMethod.GET)
	public String deleteAddress(@RequestParam Long addressId, ModelMap map, HttpSession session) {
		
		if(!WebUtil.userAuthorization(session)) {
			return "redirect:/home.htm";
		}
		
		try {	
			User user = WebUtil.getSessionUser(session);
			MailingAddress userAddress = null;
			
			Set<MailingAddress> addresses = user.getAddresses();
			for(MailingAddress address : addresses) {
				if(address.getId() == addressId) {
					userAddress = address;
					break;
				}
			}
			
			// If address belongs to user then allow delete
			if(userAddress != null) {				
				addresses.remove(userAddress);	
				mailingAddressService.delete(userAddress);
			}								

		} catch (Exception e) {
			logger.fatal(DropUtil.getExceptionDescriptionString(e));
			e.printStackTrace();
			throw new DropException();
		}
		
		return "redirect:/showAddressBook.htm";
	}
	
	
	@RequestMapping(value = "/myStatistics", method = RequestMethod.GET)
	public String showStatisticsPage(ModelMap map, HttpSession session) {
		
		if(!WebUtil.userAuthorization(session)) {
			return "redirect:/home.htm";
		}
		
		initializeCommonModel(map);
		
		return "myStatistics";
	}
	
}