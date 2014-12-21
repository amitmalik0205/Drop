package com.drop.service;

import java.util.List;

import com.drop.controller.form.DealPostForm;
import com.drop.controller.form.ReasonToDeleteForm;
import com.drop.dao.domain.DealPost;

public interface IDealPostService {

	public void saveDealPost(DealPostForm form);
	
	public List<DealPost> getAllActiveDealPostForUser(Long userId);
	
	public void deleteDealPost(ReasonToDeleteForm form);
	
	public DealPost getDealPostbyId(long dealPostId);
	
	public void saveOrUpdate(DealPostForm form);
	
	public DealPost getDealPostWithUser(Long dealPostId);
	
	public List<DealPost> getAllDealPost();
}
