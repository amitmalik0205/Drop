package com.drop.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.drop.controller.form.DealWantedForm;
import com.drop.dao.IDealCategoryDao;
import com.drop.dao.IDealWantedDao;
import com.drop.dao.IUserDao;
import com.drop.dao.domain.DealWanted;
import com.drop.service.IDealWantedService;
import com.drop.service.ISolrSearchService;
import com.drop.util.DropUtil;

@Service
public class DealWantedServiceImpl implements IDealWantedService {

	@Autowired
	private IDealWantedDao dealWantedDao;
	
	@Autowired
	private IDealCategoryDao categoryDao;
	
	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private ISolrSearchService solrSearchService;
	
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
		entity.setIpAddress(form.getIpAddress());
		entity.setActive(true);
		entity.setDealCategory(categoryDao.loadEntity(form.getCategory()));		
		entity.setUser(userDao.loadEntity(form.getUserId()));
		entity.setCreatedOn(new Date(System.currentTimeMillis()));
		
		dealWantedDao.create(entity);
		
		solrSearchService.search(entity);
	}

	@Override
	@Transactional
	public List<DealWanted> getAllDealWantedForUser(Long userId) {
		List<DealWanted> list = dealWantedDao.getAllDealWantedForUser(userId);
		return list;
	}
}
