package com.drop.dao.impl;

import org.springframework.stereotype.Repository;

import com.drop.dao.IUserRatingDao;
import com.drop.dao.domain.UserRating;

@Repository
public class UserRatingDaoImpl extends GenericDaoImpl<UserRating> implements
		IUserRatingDao {

	public UserRatingDaoImpl() {
		super(UserRating.class);
	}

}
