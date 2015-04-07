package com.drop.service.impl;

import java.util.ArrayList;
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
import com.drop.dao.domain.DealCategory;
import com.drop.dao.domain.DealPost;
import com.drop.dao.domain.Location;
import com.drop.dao.domain.MailingAddress;
import com.drop.dao.domain.User;
import com.drop.enums.POST_DEAL_TYPE;
import com.drop.rest.request.dto.PostDropDTO;
import com.drop.rest.request.dto.UpdateDropDTO;
import com.drop.rest.response.dto.GetMyDropsDTO;
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
		entity.setAverageRating(0.0);
		entity.setTotalRatings(0);

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
				entity.setOnlineDeal(false);
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
				entity.setLocalDeal(false);
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

			
			String dateFormat = msgConfig.getProperty("date.format");
			Date starts = DropUtil.convertStringToDate(form.getStarts(),
					dateFormat);
			Date expires = DropUtil.convertStringToDate(form.getExpires(),
					dateFormat);
			savedDealPost.setStarts(starts);
			savedDealPost.setExpires(expires);
			 

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
			solrSearchService.edit(savedDealPost);
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
	
	@Override
	@Transactional
	public DealPost loadDealPostbyId(long dealPostId) {
		return dealPostDao.loadEntity(dealPostId);
	}
	
	@Override
	@Transactional
	public DealPost getDealPostWithUserAndRating(long dealPostId) {
		return dealPostDao.getDealPostWithUserAndRating(dealPostId);
	}
	
	
	@Override
	public void saveDealPost(PostDropDTO postDropDTO) {
		
		DealPost entity = new DealPost();
		
		User user = userDao.getUserByEmail(postDropDTO.getEmail());
		
		if(user != null) {
			
			entity.setTitle(postDropDTO.getTitle());			
			entity.setDealCategory(categoryDao.loadEntity(postDropDTO.getCategory()));
			
			String dateFormat = msgConfig.getProperty("date.format");
			Date starts = DropUtil
					.convertStringToDate(postDropDTO.getStarts(), dateFormat);
			Date expires = DropUtil.convertStringToDate(postDropDTO.getExpires(),
					dateFormat);
			
			entity.setStarts(starts);
			entity.setExpires(expires);
						
			entity.setSalePrice(postDropDTO.getSalePrice());
			entity.setRetailPrice(postDropDTO.getRetailPrice());
			
			entity.setDiscountPercent(DropUtil.calculateDiscount(
					postDropDTO.getSalePrice(), postDropDTO.getRetailPrice()));
			
			entity.setDescription(postDropDTO.getDescription());							
			entity.setSpecialInstructions(postDropDTO.getSpecialInstructions());
			entity.setCouponsRequired(postDropDTO.getCouponsRequired());
			entity.setMembershipRequired(postDropDTO.getMembershipRequired());										
				
			entity.setImagePath(postDropDTO.getImagePath());
			
			Location location = new Location();
			String dealType = postDropDTO.getDealType();

			if (dealType != null) {
				if (dealType.equals(POST_DEAL_TYPE.LOCAL_DEAL.getDealType())) {
					
					entity.setLocalDeal(true);
					entity.setOnlineDeal(false);
					
					MailingAddress address = new MailingAddress();
					address.setAddressLine1(postDropDTO.getAddressLine1());
					address.setAddressLine2(postDropDTO.getAddressLine2());
					address.setState(postDropDTO.getState());
					address.setCity(postDropDTO.getCity());
					address.setZip(postDropDTO.getZip());
					

					location.setMailingAddress(address);

				} else if (dealType
						.equals(POST_DEAL_TYPE.ONLINE_DEAL.getDealType())) {
					
					entity.setOnlineDeal(true);
					entity.setLocalDeal(false);
					
					location.setUrl(postDropDTO.getUrl());
				}
			}

			entity.setLocation(location);
			
			entity.setUser(user);			
			entity.setActive(true);
			entity.setCreatedOn(new Date());
			entity.setAverageRating(0.0);
			entity.setTotalRatings(0);

			dealPostDao.create(entity);
			
			if (null != user.getTotalPosts()) {
				user.setTotalPosts(user.getTotalPosts() + 1);
			} else {
				user.setTotalPosts(1);

			}
			
			userDao.saveOrUpdate(user);
			
			solrSearchService.add(entity);
						
		}
	
	}
	
	
	@Override
	public List<GetMyDropsDTO> getAllActiveDealPostForUser(String email) {
		
		 List<GetMyDropsDTO> dtoList = new ArrayList<GetMyDropsDTO>();
		 
		 User user = userDao.getUserByEmail(email);
		 
		 if(user != null) {
			 
			 List<DealPost> list = dealPostDao.getAllActiveDealPostForUser(user.getUserId());
			 
			 for(DealPost dealPost : list) {
				 
				 GetMyDropsDTO dto = new GetMyDropsDTO();
				 
				 dto.setDropId(dealPost.getId());
				 dto.setTitle(dealPost.getTitle());
				 
				 DealCategory category = dealPost.getDealCategory();
				 dto.setCategoryId(category.getId());
				 dto.setCategoryName(category.getName());
				 
				 dto.setStarts(DropUtil.convertDateToString(dealPost.getStarts(), "yyyy-MM-dd HH:mm:ss"));
				 dto.setExpires(DropUtil.convertDateToString(dealPost.getExpires(), "yyyy-MM-dd HH:mm:ss"));
				 
				 dto.setSalePrice(dealPost.getSalePrice());
				 dto.setRetailPrice(dealPost.getRetailPrice());
				 
				 dto.setDescription(dealPost.getDescription());
				 dto.setSpecialInstructions(dealPost.getSpecialInstructions());
				 
				 dto.setCouponsRequired(dealPost.getCouponsRequired());
				 dto.setMembershipRequired(dealPost.getMembershipRequired());
				 
				 Location location = dealPost.getLocation();	
				 
				if(dealPost.getLocalDeal()) {							
					MailingAddress address = location.getMailingAddress();
					
					dto.setAddressLine1(address.getAddressLine1());
					dto.setAddressLine2(address.getAddressLine2());
					dto.setState(address.getState());
					dto.setCity(address.getCity());
					dto.setZip(address.getZip());
					
				} else if (dealPost.getOnlineDeal()) {
					
					dto.setUrl(location.getUrl());
				}
				 
				dto.setImagePath(DropUtil.appendServerUrlToPath(dealPost.getImagePath()));
				
				 dtoList.add(dto);
			 }
		 }
		 
		 return dtoList;		
	}
	
	
	@Override
	public void saveOrUpdate(UpdateDropDTO dto) {

		DealPost savedDealPost = dealPostDao.getEntity(dto.getDropId());

		if (savedDealPost != null) {
			
			savedDealPost.setTitle(dto.getTitle());
			savedDealPost.setDescription(dto.getDescription());
			
			savedDealPost.setSalePrice(dto.getSalePrice());
			savedDealPost.setRetailPrice(dto.getRetailPrice());
			
			savedDealPost.setDiscountPercent(DropUtil.calculateDiscount(
					dto.getSalePrice(), dto.getRetailPrice()));
			
			savedDealPost.setSpecialInstructions(dto.getSpecialInstructions());
			
			savedDealPost.setCouponsRequired(dto.getCouponsRequired());
			savedDealPost.setMembershipRequired(dto.getMembershipRequired());
			
			savedDealPost.setDealCategory(categoryDao.loadEntity(dto.getCategoryId()));
			savedDealPost.setUpdatedOn(new Date());

			
			String dateFormat = msgConfig.getProperty("date.format");
			Date starts = DropUtil.convertStringToDate(dto.getStarts(),
					dateFormat);
			Date expires = DropUtil.convertStringToDate(dto.getExpires(),
					dateFormat);
			savedDealPost.setStarts(starts);
			savedDealPost.setExpires(expires);
			 

			Location location = savedDealPost.getLocation();

			String editedDealType = dto.getDealType();

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

				address.setAddressLine1(dto.getAddressLine1());
				address.setAddressLine2(dto.getAddressLine2());
				address.setState(dto.getState());
				address.setCity(dto.getCity());
				address.setZip(dto.getZip());

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

				location.setUrl(dto.getUrl());
			}

			savedDealPost.setLocation(location);

			dealPostDao.saveOrUpdate(savedDealPost);
			
			solrSearchService.edit(savedDealPost);
		}		
	}
}
