package com.drop.service;

import com.drop.controller.form.UserRatingForm;
import com.drop.dao.domain.DealMatch;

public interface IUserRatingService {
	
	public void saveUserRating(UserRatingForm userRatingForm, DealMatch dealMatch);
}
