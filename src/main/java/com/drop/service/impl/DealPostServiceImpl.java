package com.drop.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.drop.controller.form.DealPostForm;
import com.drop.controller.form.ReasonToDeleteForm;
import com.drop.dao.IDealCategoryDao;
import com.drop.dao.IDealPostDao;
import com.drop.dao.IUserDao;
import com.drop.dao.domain.DealPost;
import com.drop.dao.domain.DealWanted;
import com.drop.dao.domain.Location;
import com.drop.dao.domain.MailingAddress;
import com.drop.enums.POST_DEAL_TYPE;
import com.drop.service.IDealPostService;
import com.drop.service.ISolrSearchService;
import com.drop.util.DropUtil;

@Service
public class DealPostServiceImpl implements IDealPostService {
	
	@Autowired
	private IDealPostDao dealPostDao;
	
	@Autowired
	private IDealCategoryDao categoryDao;
	
	@Autowired
	private IUserDao userDao;
	
	@Autowired
	@Qualifier("msgConfig")
	private Properties msgConfig;

	@Autowired
	private ISolrSearchService solrSearchService;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveDealPost(DealPostForm form) {
		DealPost entity = new DealPost();
		
		entity.setTitle(form.getTitle());
		entity.setDescription(form.getDescription());
		entity.setSalePrice(form.getSalePrice());
		entity.setRetailPrice(form.getRetailPrice());
		entity.setDiscountPercent(form.getDiscountPercent());
		entity.setSpecialInstructions(form.getSpecialInstructions());
		entity.setCouponsRequired(form.getCouponsRequired());
		entity.setMembershipRequired(form.getMembershipRequired());
		entity.setIpAddress(form.getIpAddress());
		entity.setActive(true);
		entity.setDealCategory(categoryDao.loadEntity(form.getCategory()));		
		entity.setUser(userDao.loadEntity(form.getUserId()));
		entity.setCreatedOn(new Date());
		
		String dateFormat = msgConfig.getProperty("date.format");
		Date starts = DropUtil.convertStringToDate(form.getStarts(), dateFormat);
		Date expires = DropUtil.convertStringToDate(form.getExpires(), dateFormat);
		
		entity.setStarts(starts);
		entity.setExpires(expires);
		
		Location location = new Location();
		String dealType = form.getDealType();
		
		if(dealType != null) {
			if(dealType.equals(POST_DEAL_TYPE.LOCAL_DEAL.getDealType())) { 				
				entity.setLocalDeal(true);
				MailingAddress address = new MailingAddress();
				address.setAddressLine1(form.getAddressLine1());
				address.setAddressLine2(form.getAddressLine2());
				address.setState(form.getState());
				address.setCity(form.getCity());
				address.setZip(form.getZip());
				
				location.setMailingAddress(address);
				
			} else if(dealType.equals(POST_DEAL_TYPE.ONLINE_DEAL.getDealType())) {				
				entity.setOnlineDeal(true);
				location.setUrl(form.getUrl());
			}
		}	
			
		entity.setLocation(location);
		
		dealPostDao.create(entity);
		
		solrSearchService.add(entity);
	}

	@Override
	@Transactional
	public List<DealPost> getAllActiveDealPostForUser(Long userId) {
		return dealPostDao.getAllActiveDealPostForUser(userId);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteDealPost(ReasonToDeleteForm form) {
		
		DealPost savedDealPost = dealPostDao.getEntity(form.getDealId());
		
		if(savedDealPost != null) {
			savedDealPost.setActive(false);
			savedDealPost.setReasonForDeleting(form.getReason());
			
			dealPostDao.saveOrUpdate(savedDealPost);
		}
	}
	
	@Override
	@Transactional
	public DealPost getDealPostbyId(long dealPostId) {
		return dealPostDao.getEntity(dealPostId);
	}
}
