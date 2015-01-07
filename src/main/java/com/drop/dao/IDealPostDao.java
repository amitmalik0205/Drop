package com.drop.dao;

import java.util.List;

import com.drop.dao.domain.DealPost;

public interface IDealPostDao extends IGenericDao<DealPost> {

	public List<DealPost> getAllActiveDealPostForUser(Long userId);
	
	public DealPost getDealPostWithUser(Long dealPostId);
	
	public DealPost getDealPostWithUserAndRating(Long dealPostId);
}
