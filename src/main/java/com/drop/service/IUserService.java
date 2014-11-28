package com.drop.service;

import com.drop.controller.form.LoginForm;
import com.drop.controller.form.RegisterationForm;
import com.drop.dao.domain.User;

public interface IUserService {

	public void saveUser(RegisterationForm form);
	
	public User authenticateUser(LoginForm form);
	
	public User getUserByEmail(String email);
	
	public void sendForgotPasswordEmail(User user);
}
