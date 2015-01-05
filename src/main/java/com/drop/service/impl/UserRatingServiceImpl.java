package com.drop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.drop.controller.form.UserRatingForm;
import com.drop.dao.IDealMatchDao;
import com.drop.dao.IUserRatingDao;
import com.drop.dao.domain.DealMatch;
import com.drop.dao.domain.UserRating;
import com.drop.service.IUserRatingService;

@Service
public class UserRatingServiceImpl implements IUserRatingService {
	
	@Autowired
	private IUserRatingDao userRatingDao;
	
	@Autowired
	private IDealMatchDao dealMatchDao;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveUserRating(UserRatingForm userRatingForm, DealMatch dealMatch) {
		
		dealMatchDao.saveOrUpdate(dealMatch);
		
		UserRating userRating = new UserRating();
		userRating.setRating(userRatingForm.getRating());
		userRating.setDescription(userRatingForm.getDescription());
		userRating.setDealMatch(dealMatch);
		userRating.setRateeId(dealMatch.getDealWanted().getUser());
		
		DealMatch savedDealMatch = dealMatchDao.getDealMatchWithDealPost(dealMatch.getId());
		
		userRating.setRaterId(savedDealMatch.getDealPost().getUser());
		
		userRatingDao.saveOrUpdate(userRating);
	}
	
}
