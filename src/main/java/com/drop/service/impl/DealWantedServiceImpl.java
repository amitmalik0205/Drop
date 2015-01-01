package com.drop.service.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.drop.controller.form.DealWantedForm;
import com.drop.controller.form.ReasonToDeleteForm;
import com.drop.dao.IDealCategoryDao;
import com.drop.dao.IDealWantedDao;
import com.drop.dao.IUserDao;
import com.drop.dao.domain.DealWanted;
import com.drop.service.IDealWantedService;
import com.drop.service.ISolrSearchService;

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
	public Long saveDealWanted(DealWantedForm form) {
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

		solrSearchService.add(entity);

		return entity.getId();
	}

	@Override
	@Transactional
	public List<DealWanted> getAllDealWantedForUser(Long userId) {
		List<DealWanted> list = dealWantedDao.getAllDealWantedForUser(userId);
		return list;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveOrUpdate(DealWantedForm form) {

		DealWanted savedDealWanted = dealWantedDao.getEntity(form
				.getDealWantedId());

		if (savedDealWanted != null) {
			savedDealWanted.setTitle(form.getTitle());
			savedDealWanted.setDescription(form.getDescription());
			savedDealWanted.setMaxPrice(form.getMaxPrice());
			savedDealWanted.setTipAmount(form.getTipAmount());
			savedDealWanted.setAcceptCoupons(form.getAcceptCoupons());
			savedDealWanted.setWouldBuyOnline(form.getWouldBuyOnline());
			savedDealWanted.setWouldBuyLocally(form.getWouldBuyLocally());
			savedDealWanted.setWantNew(form.getWantNew());
			savedDealWanted.setWantUsed(form.getWantUsed());
			savedDealWanted.setRefurbishedOK(form.getRefurbishedOK());
			savedDealWanted.setIpAddress(form.getIpAddress());
			savedDealWanted.setActive(true);
			savedDealWanted.setDealCategory(categoryDao.loadEntity(form
					.getCategory()));
			savedDealWanted.setUpdatedOn(new Date(System.currentTimeMillis()));

			dealWantedDao.saveOrUpdate(savedDealWanted);
		}
	}

	@Override
	@Transactional
	public DealWanted getDealWantedById(Long id) {
		return dealWantedDao.getEntity(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteDealWanted(ReasonToDeleteForm form) {

		DealWanted savedDealWanted = dealWantedDao.getEntity(form.getDealId());

		if (savedDealWanted != null) {
			savedDealWanted.setActive(false);
			savedDealWanted.setReasonForDeleting(form.getReason());

			dealWantedDao.saveOrUpdate(savedDealWanted);
			solrSearchService.delete(savedDealWanted.getId(), false);
		}
	}

	@Override
	@Transactional
	public List<DealWanted> getAllDealWanted() {
		return dealWantedDao.getFirstNEntities(0, 4);
	}
}
