package com.drop.dao;

import com.drop.dao.domain.User;

public interface IUserDao extends IGenericDao<User> {

	/**
	 * Method to check email and password combination
	 * 
	 * @param email
	 * @param password
	 * @return User if email and password do match otherwise NULL
	 */
	public User authenticateUser(String email, String password);
	
	/**
	 * Method to get user by email
	 * @param email
	 * @return User if email do match otherwise NULL
	 */
	public User getUserByEmail(String email);
}
