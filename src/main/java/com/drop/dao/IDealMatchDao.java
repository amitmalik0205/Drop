package com.drop.dao;

import com.drop.dao.domain.DealMatch;

public interface IDealMatchDao extends IGenericDao<DealMatch> {

	public DealMatch getDealMatchWithUserByDealWanted(long dealWantedId,
			long dealPostId);
	
	public DealMatch getDealMatchByDealWantedAndDealPost(long dealWantedId,
			long dealPostId);
}
