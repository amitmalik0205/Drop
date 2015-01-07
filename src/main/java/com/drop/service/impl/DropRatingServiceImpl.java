package com.drop.service.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.drop.controller.form.DropRatingForm;
import com.drop.dao.IDropRatingDao;
import com.drop.dao.domain.DealPost;
import com.drop.dao.domain.DealWanted;
import com.drop.dao.domain.DropRating;
import com.drop.service.IDealPostService;
import com.drop.service.IDealWantedService;
import com.drop.service.IDropRatingService;
import com.drop.util.DropUtil;
import com.drop.util.WebUtil;

@Service
public class DropRatingServiceImpl implements IDropRatingService {
	
	@Autowired
	private IDealPostService dealPostService;
	
	@Autowired
	private IDealWantedService dealWantedService;
	
	@Autowired
	private IDropRatingDao dropRatingDao;
	
	@Autowired
	private HttpSession session;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveDropRating(DropRatingForm dropRatingForm) {

		DealPost dealPost = dealPostService.getDealPostWithUser(dropRatingForm.getDealPostId());
		//DealWanted dealWanted = dealWantedService.getDealWantedById(dropRatingForm.getDealWantedId());
		
		int savedRatingCount = dealPost.getTotalRatings();
		int newRatingCount = savedRatingCount + 1;
		
		double savedAvgRating  = dealPost.getAverageRating();		
		double newAvgRating = ((savedRatingCount*savedAvgRating)+dropRatingForm.getRating()) / newRatingCount;
		
		dealPost.setTotalRatings(newRatingCount);
		dealPost.setAverageRating(newAvgRating);
		
		DropRating dropRating = new DropRating();
		dropRating.setRating(dropRatingForm.getRating());
		dropRating.setDescription(dropRatingForm.getDescription());
		dropRating.setDate(new Date());
		dropRating.setRatee(WebUtil.getSessionUser(session));
		dropRating.setRater(dealPost.getUser());
		dropRating.setDealPost(dealPost);
		
		dropRatingDao.saveOrUpdate(dropRating);
	}

}
