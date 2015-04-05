package com.drop.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.drop.controller.form.DealWantedForm;
import com.drop.controller.form.ReasonToDeleteForm;
import com.drop.dao.IDealCategoryDao;
import com.drop.dao.IDealWantedDao;
import com.drop.dao.IUserDao;
import com.drop.dao.domain.DealCategory;
import com.drop.dao.domain.DealWanted;
import com.drop.dao.domain.User;
import com.drop.rest.request.dto.PostWantDropDTO;
import com.drop.rest.response.dto.GetMyWantDropsDTO;
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
			solrSearchService.edit(savedDealWanted);
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
	
	@Override
	@Transactional
	public DealWanted loadDealWantedById(Long dealWantedId) {
		return dealWantedDao.loadEntity(dealWantedId);
	}
	
	
	
	@Override
	public Long saveDealWanted(PostWantDropDTO postWantDropDTO) {
		
		DealWanted entity = new DealWanted();

		entity.setTitle(postWantDropDTO.getTitle());
		entity.setDescription(postWantDropDTO.getDescription());
		entity.setMaxPrice(postWantDropDTO.getMaxPrice());
		entity.setTipAmount(postWantDropDTO.getTipAmount());
		entity.setAcceptCoupons(postWantDropDTO.getAcceptCoupons());
		entity.setWouldBuyOnline(postWantDropDTO.getWouldBuyOnline());
		entity.setWouldBuyLocally(postWantDropDTO.getWouldBuyLocally());
		entity.setWantNew(postWantDropDTO.getWantNew());
		entity.setWantUsed(postWantDropDTO.getWantUsed());
		entity.setRefurbishedOK(postWantDropDTO.getRefurbishedOK());
		
		entity.setActive(true);
		entity.setDealCategory(categoryDao.loadEntity(postWantDropDTO.getCategory()));
		entity.setUser(userDao.getUserByEmail(postWantDropDTO.getEmail()));
		entity.setCreatedOn(new Date(System.currentTimeMillis()));

		dealWantedDao.create(entity);

		solrSearchService.add(entity);

		return entity.getId();			
	}
	
	
	@Override
	public List<GetMyWantDropsDTO> getAllDealWantedForUser(String  email) {
		
		List<GetMyWantDropsDTO> dtoList = new ArrayList<GetMyWantDropsDTO>();
		
		User user = userDao.getUserByEmail(email);
		
		if(user != null) {
			
			List<DealWanted> list = dealWantedDao.getAllDealWantedForUser(user.getUserId());
			
			for(DealWanted dealWanted : list) {
				
				GetMyWantDropsDTO dto = new GetMyWantDropsDTO();
				
				dto.setWantDropId(dealWanted.getId());
				dto.setTitle(dealWanted.getTitle());
				
				DealCategory category = dealWanted.getDealCategory();				
				dto.setCategoryId(category.getId());
				dto.setCategoryName(category.getName());
				
				dto.setMaxPrice(dealWanted.getMaxPrice());
				dto.setTipAmount(dealWanted.getTipAmount());
				dto.setDescription(dealWanted.getDescription());
				
				dto.setAcceptCoupons(dealWanted.getAcceptCoupons());
				dto.setWantNew(dealWanted.getWantNew());
				dto.setWantUsed(dealWanted.getWantUsed());
				
				dto.setWouldBuyOnline(dealWanted.getWouldBuyOnline());
				dto.setWouldBuyLocally(dealWanted.getWouldBuyLocally());
				dto.setRefurbishedOK(dealWanted.getRefurbishedOK());
				
				dtoList.add(dto);
			}
		}
		
		return dtoList;		
	}
}
