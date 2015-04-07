package com.drop.service;

import java.util.List;

import com.drop.controller.form.DealPostForm;
import com.drop.controller.form.ReasonToDeleteForm;
import com.drop.dao.domain.DealPost;
import com.drop.rest.request.dto.PostDropDTO;
import com.drop.rest.request.dto.UpdateDropDTO;
import com.drop.rest.response.dto.GetMyDropsDTO;

public interface IDealPostService {

	public void saveDealPost(DealPostForm form);
	
	public List<DealPost> getAllActiveDealPostForUser(Long userId);
	
	public void deleteDealPost(ReasonToDeleteForm form);
	
	public DealPost getDealPostbyId(long dealPostId);
	
	public void saveOrUpdate(DealPostForm form);
	
	public DealPost getDealPostWithUser(Long dealPostId);
	
	public List<DealPost> getAllDealPost();
	
	public DealPost loadDealPostbyId(long dealPostId);
	
	public DealPost getDealPostWithUserAndRating(long dealPostId);
	
	public void saveDealPost(PostDropDTO postDropDTO);
	
	public List<GetMyDropsDTO> getAllActiveDealPostForUser(String email);
	
	public void saveOrUpdate(UpdateDropDTO dto);
}
