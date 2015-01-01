package com.drop.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpSession;

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
import com.drop.dao.domain.Location;
import com.drop.dao.domain.MailingAddress;
import com.drop.dao.domain.User;
import com.drop.enums.POST_DEAL_TYPE;
import com.drop.service.IDealPostService;
import com.drop.service.IMailingAddressService;
import com.drop.service.ISolrSearchService;
import com.drop.util.DropUtil;
import com.drop.util.WebUtil;

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

	@Autowired
	private HttpSession session;

	@Autowired
	private IMailingAddressService mailingAddressService;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveDealPost(DealPostForm form) {
		DealPost entity = new DealPost();

		entity.setTitle(form.getTitle());
		entity.setDescription(form.getDescription());
		entity.setSalePrice(form.getSalePrice());
		entity.setRetailPrice(form.getRetailPrice());
		entity.setDiscountPercent(DropUtil.calculateDiscount(
				form.getSalePrice(), form.getRetailPrice()));
		entity.setSpecialInstructions(form.getSpecialInstructions());
		entity.setCouponsRequired(form.getCouponsRequired());
		entity.setMembershipRequired(form.getMembershipRequired());
		entity.setIpAddress(form.getIpAddress());
		entity.setActive(true);
		entity.setDealCategory(categoryDao.loadEntity(form.getCategory()));
		entity.setUser(userDao.loadEntity(form.getUserId()));
		entity.setCreatedOn(new Date());

		String dateFormat = msgConfig.getProperty("date.format");
		Date starts = DropUtil
				.convertStringToDate(form.getStarts(), dateFormat);
		Date expires = DropUtil.convertStringToDate(form.getExpires(),
				dateFormat);

		entity.setStarts(starts);
		entity.setExpires(expires);

		Location location = new Location();
		String dealType = form.getDealType();

		if (dealType != null) {
			if (dealType.equals(POST_DEAL_TYPE.LOCAL_DEAL.getDealType())) {
				entity.setLocalDeal(true);
				MailingAddress address = new MailingAddress();
				address.setAddressLine1(form.getAddressLine1());
				address.setAddressLine2(form.getAddressLine2());
				address.setState(form.getState());
				address.setCity(form.getCity());
				address.setZip(form.getZip());

				location.setMailingAddress(address);

			} else if (dealType
					.equals(POST_DEAL_TYPE.ONLINE_DEAL.getDealType())) {
				entity.setOnlineDeal(true);
				location.setUrl(form.getUrl());
			}
		}

		entity.setLocation(location);

		dealPostDao.create(entity);

		User user = userDao.getEntity(form.getUserId());
		if (null != user.getTotalPosts()) {
			user.setTotalPosts(user.getTotalPosts() + 1);
		} else {
			user.setTotalPosts(1);

		}
		userDao.saveOrUpdate(user);

		WebUtil.updateSessionUser(session);

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

		if (savedDealPost != null) {
			savedDealPost.setActive(false);
			savedDealPost.setReasonForDeleting(form.getReason());

			dealPostDao.saveOrUpdate(savedDealPost);
			solrSearchService.delete(savedDealPost.getId(), true);
		}
	}

	@Override
	@Transactional
	public DealPost getDealPostbyId(long dealPostId) {
		return dealPostDao.getEntity(dealPostId);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveOrUpdate(DealPostForm form) {

		DealPost savedDealPost = dealPostDao.getEntity(form.getDealPostId());

		if (savedDealPost != null) {
			savedDealPost.setTitle(form.getTitle());
			savedDealPost.setDescription(form.getDescription());
			savedDealPost.setSalePrice(form.getSalePrice());
			savedDealPost.setRetailPrice(form.getRetailPrice());
			savedDealPost.setDiscountPercent(DropUtil.calculateDiscount(
					form.getSalePrice(), form.getRetailPrice()));
			savedDealPost.setSpecialInstructions(form.getSpecialInstructions());
			savedDealPost.setCouponsRequired(form.getCouponsRequired());
			savedDealPost.setMembershipRequired(form.getMembershipRequired());
			savedDealPost.setIpAddress(form.getIpAddress());
			savedDealPost.setDealCategory(categoryDao.loadEntity(form
					.getCategory()));
			savedDealPost.setUpdatedOn(new Date());

			/*
			 * String dateFormat = msgConfig.getProperty("date.format"); Date
			 * starts = DropUtil.convertStringToDate(form.getStarts(),
			 * dateFormat); Date expires =
			 * DropUtil.convertStringToDate(form.getExpires(), dateFormat);
			 * 
			 * savedDealPost.setStarts(starts);
			 * savedDealPost.setExpires(expires);
			 */

			Location location = savedDealPost.getLocation();

			String editedDealType = form.getDealType();

			if (editedDealType.equals(POST_DEAL_TYPE.LOCAL_DEAL.getDealType())) {

				MailingAddress address = null;

				if (savedDealPost.getLocalDeal()) {
					// saved deal type is local so update mailing address
					address = location.getMailingAddress();
				} else {
					// Saved Deal type is online so delete it and set a new
					// mailing address
					address = new MailingAddress();
					savedDealPost.setLocalDeal(true);
					savedDealPost.setOnlineDeal(false);
					location.setUrl(null);
				}

				address.setAddressLine1(form.getAddressLine1());
				address.setAddressLine2(form.getAddressLine2());
				address.setState(form.getState());
				address.setCity(form.getCity());
				address.setZip(form.getZip());

				location.setMailingAddress(address);

			} else if (editedDealType.equals(POST_DEAL_TYPE.ONLINE_DEAL
					.getDealType())) {

				if (!savedDealPost.getOnlineDeal()) {

					savedDealPost.setOnlineDeal(true);
					savedDealPost.setLocalDeal(false);
					MailingAddress address = location.getMailingAddress();
					mailingAddressService.delete(address);
					location.setMailingAddress(null);
				}

				location.setUrl(form.getUrl());
			}

			savedDealPost.setLocation(location);

			dealPostDao.saveOrUpdate(savedDealPost);
		}
	}

	@Override
	@Transactional
	public DealPost getDealPostWithUser(Long dealPostId) {
		return dealPostDao.getDealPostWithUser(dealPostId);
	}

	@Override
	@Transactional
	public List<DealPost> getAllDealPost() {
		return dealPostDao.getFirstNEntities(0, 18);
	}
}
