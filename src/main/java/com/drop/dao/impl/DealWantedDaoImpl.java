package com.drop.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.drop.dao.IDealWantedDao;
import com.drop.dao.domain.DealPost;
import com.drop.dao.domain.DealWanted;

@Repository
public class DealWantedDaoImpl extends GenericDaoImpl<DealWanted> implements IDealWantedDao {

	public DealWantedDaoImpl() {
		super(DealWanted.class);
	}
	
	@Override
	public List<DealWanted> getAllDealWantedForUser(Long userId) {
		Session session = getCurrentSession();
		List<DealWanted> list = null;		
		Query query = session.getNamedQuery("DealWanted.getAllActiveDealWantedForUser");
		query.setParameter("userId", userId);
		list = query.list();
		return list;
	}
	
	@Override
	public DealWanted getDealWantedWithUser(long dealWantedId) {
		Session session = getCurrentSession();
		DealWanted dealWanted = null;		
		Query query = session.getNamedQuery("DealWanted.getDealWantedWithUser");
		query.setParameter("dealWantedId", dealWantedId);
		dealWanted = (DealWanted)query.uniqueResult();
		return dealWanted;	
	}
}
