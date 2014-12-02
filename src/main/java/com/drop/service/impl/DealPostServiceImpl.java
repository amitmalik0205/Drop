package com.drop.service.impl;

import java.util.Date;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.drop.controller.form.DealPostForm;
import com.drop.dao.IDealCategoryDao;
import com.drop.dao.IDealPostDao;
import com.drop.dao.IUserDao;
import com.drop.dao.domain.DealPost;
import com.drop.service.IDealPostService;
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
		entity.setOnlineDeal(form.getOnlineDeal());
		entity.setLocalDeal(form.getLocalDeal());
		entity.setCouponsRequired(form.getCouponsRequired());
		entity.setMembershipRequired(form.getMembershipRequired());
		entity.setIpAddress(form.getIpAddress());
		entity.setActive(true);
		entity.setDealCategory(categoryDao.loadEntity(form.getCategory()));		
		entity.setUser(userDao.loadEntity(form.getUserId()));
		
		String dateFormat = msgConfig.getProperty("date.format");
		Date starts = DropUtil.convertStringToDate(form.getStarts(), dateFormat);
		Date expires = DropUtil.convertStringToDate(form.getExpires(), dateFormat);
		
		entity.setStarts(starts);
		entity.setExpires(expires);
		
		dealPostDao.create(entity);
	}

}
