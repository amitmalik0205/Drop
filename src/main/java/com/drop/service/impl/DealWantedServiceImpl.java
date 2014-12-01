package com.drop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.drop.controller.form.DealWantedForm;
import com.drop.dao.IDealCategoryDao;
import com.drop.dao.IDealWantedDao;
import com.drop.dao.domain.DealWanted;
import com.drop.service.IDealWantedService;

@Service
public class DealWantedServiceImpl implements IDealWantedService {

	@Autowired
	private IDealWantedDao dealWantedDao;
	
	@Autowired
	private IDealCategoryDao categoryDao;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveDealWanted(DealWantedForm form) {
		DealWanted entity = new DealWanted();
		
		entity.setTitle(form.getTitle());
		entity.setDescription(form.getDescription());
		entity.setMaxPrice(form.getMaxPrice());
		entity.setTipAmount(form.getTipAmount());
		entity.setAcceptCoupons(form.getAcceptCoupons());
		entity.setWouldBuyOnline(form.getWouldBuyOnline());
		entity.setWouldBuyLocally(form.getWouldBuyLocally());
		entity.setWantNew(form.getWantNew());
		entity.setWantUsed(form.getWantUsed());
		entity.setRefurbishedOK(form.getRefurbishedOK());
		entity.setDealCategory(categoryDao.loadEntity(form.getCategory()));
		
		dealWantedDao.create(entity);
	}

}
