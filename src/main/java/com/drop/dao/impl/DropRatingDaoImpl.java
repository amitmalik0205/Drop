package com.drop.dao.impl;

import org.springframework.stereotype.Repository;

import com.drop.dao.IDropRatingDao;
import com.drop.dao.domain.DropRating;

@Repository
public class DropRatingDaoImpl extends GenericDaoImpl<DropRating> implements
		IDropRatingDao {
	
	public DropRatingDaoImpl() {
		super(DropRating.class);
	}

}
