package com.drop.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.drop.controller.form.DealMatchForm;
import com.drop.dao.IDealMatchDao;
import com.drop.dao.IDealPostDao;
import com.drop.dao.IDealWantedDao;
import com.drop.dao.domain.DealMatch;
import com.drop.dao.domain.DealPost;
import com.drop.dao.domain.DealWanted;
import com.drop.enums.DEAL_MATCH_STATUS;
import com.drop.service.IDealMatchService;

@Service
public class DealMatchServiceImpl implements IDealMatchService {

	@Autowired
	private IDealMatchDao dealMatchDao;
	
	@Autowired
	private IDealWantedDao dealWantedDao;
	
	@Autowired
	private IDealPostDao dealPostDao;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveDealMatch(DealMatchForm form) {
		
		DealPost savedDealPost = dealPostDao.loadEntity(form.getDealPostId());
		DealWanted savedDealWanted = dealWantedDao.loadEntity(form.getDealWantedId());
		
		DealMatch dealMatch = new DealMatch();
		dealMatch.setAcceptedDate(new Date());
		dealMatch.setStatus(DEAL_MATCH_STATUS.ACCEPTED);
		dealMatch.setDealPost(savedDealPost);
		dealMatch.setDealWanted(savedDealWanted);
		dealMatch.setMatchCreated(new Date());
		
		dealMatchDao.saveOrUpdate(dealMatch);
	}
	
	@Override
	@Transactional
	public DealMatch getDealMatchWithUserByDealWanted(long dealWantedId,
			long dealPostId) {
		return dealMatchDao.getDealMatchWithUserByDealWanted(dealWantedId, dealPostId);
	}
	
	@Override
	@Transactional
	public DealMatch getDealMatchByDealWantedAndDealPost(long dealWantedId,
			long dealPostId) {
		return dealMatchDao.getDealMatchByDealWantedAndDealPost(dealWantedId, dealPostId);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveOrUpdate(DealMatch dealMatch) {
		dealMatchDao.saveOrUpdate(dealMatch);
	}
}
