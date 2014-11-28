package com.drop.service.impl;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.drop.controller.form.LoginForm;
import com.drop.controller.form.RegisterationForm;
import com.drop.dao.IUserDao;
import com.drop.dao.domain.User;
import com.drop.dto.ForgetPasswordDTO;
import com.drop.service.IUserService;
import com.drop.util.EmailUtil;

@Service("userService")
public class UserServiceImpl implements IUserService {

	@Autowired
	@Qualifier("velocityConfig")
	private Properties velocityConfig;
	
	@Autowired
	private IUserDao userDao;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveUser(RegisterationForm form) {
		User user = new User();
		user.setFirstName(form.getFirstName());
		user.setLastName(form.getLastName());
		user.setEmail(form.getEmail());
		user.setPassword(form.getPassword());
		user.setLocationDescription(form.getLocation());
		user.setPhoneNumber(form.getPhone());
		user.setSkypeName(form.getSkypeName());
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
}
