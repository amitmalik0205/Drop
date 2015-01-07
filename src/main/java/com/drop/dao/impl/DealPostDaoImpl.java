package com.drop.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.drop.dao.IDealPostDao;
import com.drop.dao.domain.DealPost;

@Repository
public class DealPostDaoImpl extends GenericDaoImpl<DealPost> implements IDealPostDao {
	
	public DealPostDaoImpl() {
		super(DealPost.class);
	}
	
	@Override
	public List<DealPost> getAllActiveDealPostForUser(Long userId) {
		Session session = getCurrentSession();
		List<DealPost> list = null;		
		Query query = session.getNamedQuery("DealPost.getAllActiveDealPostForUser");
		query.setParameter("userId", userId);
		list = query.list();
		return list;
	}
	
	@Override
	public DealPost getDealPostWithUser(Long dealPostId) {
		Session session = getCurrentSession();
		DealPost dealPost = null;		
		Query query = session.getNamedQuery("DealPost.getDealPostWithUser");
		query.setParameter("dealPostId", dealPostId);
		dealPost = (DealPost)query.uniqueResult();
		return dealPost;
	}
	
	@Override
	public DealPost getDealPostWithUserAndRating(Long dealPostId) {
		Session session = getCurrentSession();
		DealPost dealPost = null;		
		Query query = session.getNamedQuery("DealPost.getDealPostWithUserAndRating");
		query.setParameter("dealPostId", dealPostId);
		dealPost = (DealPost)query.uniqueResult();
		return dealPost;
	}
}
