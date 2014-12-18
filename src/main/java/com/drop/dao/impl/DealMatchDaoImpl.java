package com.drop.dao.impl;


import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.drop.dao.IDealMatchDao;
import com.drop.dao.domain.DealMatch;

@Repository
public class DealMatchDaoImpl extends GenericDaoImpl<DealMatch> implements
		IDealMatchDao {

	public DealMatchDaoImpl() {
		super(DealMatch.class);
	}
	
	@Override
	public DealMatch getDealMatchWithUserByDealWanted(long dealWantedId,
			long dealPostId) {
		DealMatch dealMatch = null;
		Session session = getCurrentSession();
		Query query = session.getNamedQuery("DealMatch.getDealMatchWithUserByDealWanted");
		query.setParameter("dealWantedId", dealWantedId);
		query.setParameter("dealPostId", dealPostId);
		dealMatch = (DealMatch)query.uniqueResult();
		return dealMatch;
	}
	
	public DealMatch getDealMatchByDealWantedAndDealPost(long dealWantedId,
			long dealPostId) {		
		DealMatch dealMatch = null;
		Session session = getCurrentSession();
		Query query = session.getNamedQuery("DealMatch.getDealMatchByDealWantedAndDealPost");
		query.setParameter("dealWantedId", dealWantedId);
		query.setParameter("dealPostId", dealPostId);
		dealMatch = (DealMatch)query.uniqueResult();
		return dealMatch;
	}
}
