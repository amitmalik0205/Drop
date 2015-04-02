package com.drop.service.impl;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.drop.controller.form.AccountSettingsForm;
import com.drop.controller.form.AddressBookForm;
import com.drop.controller.form.LoginForm;
import com.drop.controller.form.RegistrationForm;
import com.drop.dao.IUserDao;
import com.drop.dao.domain.MailingAddress;
import com.drop.dao.domain.User;
import com.drop.dto.ForgetPasswordDTO;
import com.drop.rest.request.dto.LoginDTO;
import com.drop.service.IUserService;
import com.drop.util.EmailUtil;
import com.drop.util.WebUtil;

@Service("userService")
public class UserServiceImpl implements IUserService {

	@Autowired
	@Qualifier("velocityConfig")
	private Properties velocityConfig;
	
	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private HttpSession session;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveUser(RegistrationForm form) {
		User user = new User();
		user.setFirstName(form.getFirstName());
		user.setLastName(form.getLastName());
		user.setEmail(form.getEmail());
		user.setPassword(form.getPassword());
		userDao.create(user);
	}
	
	@Override
	@Transactional
	public User authenticateUser(LoginForm form) {
		return userDao.authenticateUser(form.getEmail(), form.getPassword());
	}
	
	@Override
	@Transactional
	public User getUserByEmail(String email) {
		return userDao.getUserByEmail(email);
	}
	
	@Override
	public void sendForgotPasswordEmail(User user) {
		ForgetPasswordDTO dto = new ForgetPasswordDTO();
		dto.setFirstName(user.getFirstName());
		dto.setPassword(user.getPassword());

		String subject = velocityConfig
				.getProperty("resend.password.email.subject");
		String recipeintEmail = user.getEmail();
		String emailType = velocityConfig.getProperty("resend.password");

		EmailUtil.sendEmail(subject, recipeintEmail, emailType, dto, null);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveOrUpdateUser(AccountSettingsForm form) {
		User user = WebUtil.getSessionUser(session);
		
		user.setFirstName(form.getFirstName());
		user.setLastName(form.getLastName());
		user.setPhoneNumber(form.getPhone());
		user.setSkypeName(form.getSkypeName());
		
		userDao.saveOrUpdate(user);	
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveOrUpdateUser(AddressBookForm form) {
		User user = WebUtil.getSessionUser(session);
		Set<MailingAddress> addressList = user.getAddresses();
		
		MailingAddress address = new MailingAddress();
		address.setAddressLine1(form.getAddressLine1());
		address.setAddressLine2(form.getAddressLine2());
		address.setState(form.getState());
		address.setCity(form.getCity());
		address.setZip(form.getZip());
		
		addressList.add(address);
		
		userDao.saveOrUpdate(user);
	}
		
	/*
	 * Method will first check if there is user with the email id 
	 * If found then no need to save the user details otherwise 
	 * save details
	 */
	@Override
	public void login(LoginDTO loginDto) {
				
		User savedUser = userDao.getUserByEmail(loginDto.getEmail());
		
		if(savedUser == null) {
			
			User  user = new User();
			
			user.setFirstName(loginDto.getFirstName());
			user.setLastName(loginDto.getLastName());
			user.setPhoneNumber(loginDto.getPhoneNumber());
			user.setEmail(loginDto.getEmail());
			
			Set<MailingAddress> addressList = new HashSet<MailingAddress>();
			
			MailingAddress address = new MailingAddress();
			address.setAddressLine1(loginDto.getAddressLine1());
			address.setAddressLine2(loginDto.getAddressLine2());
			address.setState(loginDto.getState());
			address.setCity(loginDto.getCity());
			address.setZip(loginDto.getZip());
			
			addressList.add(address);
			
			user.setAddresses(addressList);
			
			userDao.create(user);
						
		} 	
	}
	
	
	@Override
	public void updateUserProfile(LoginDTO loginDto) {

		User user = userDao.getUserByEmail(loginDto.getEmail());

		if (user != null) {
			
			user.setFirstName(loginDto.getFirstName());
			user.setLastName(loginDto.getLastName());
			user.setPhoneNumber(loginDto.getPhoneNumber());
			user.setEmail(loginDto.getEmail());

			Set<MailingAddress> addressList = user.getAddresses();
			
			if(addressList.size() > 0) {
				
				for(MailingAddress address : addressList) {
					address.setAddressLine1(loginDto.getAddressLine1());
					address.setAddressLine1(loginDto.getAddressLine2());
					address.setCity(loginDto.getCity());
					address.setState(loginDto.getState());
					address.setZip(loginDto.getZip());
					break;
				}
			}
			
			userDao.saveOrUpdate(user);
		}	
	}
}
