package com.drop.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.drop.dao.IUserDao;
import com.drop.dao.domain.User;

@Repository("userDao")
public class UserDaoImpl extends GenericDaoImpl<User> implements IUserDao {

	public UserDaoImpl() {
		super(User.class);
	}
	
	@Override
	public User authenticateUser(String email, String password) {
		Session session = getCurrentSession();
		User user = null;
		Query query = session.getNamedQuery("User.getUserByEmailAndPassword");
		query.setString("email", email);
		query.setString("pwd", password);
		user = (User) query.uniqueResult();
		return user;
	}
	
	@Override
	public User getUserByEmail(String email) {
		Session session = getCurrentSession();
		User user = null;
		Query query = session.getNamedQuery("User.getUserByEmail");
		query.setString("email", email);
		user = (User) query.uniqueResult();
		return user;
	}
}
