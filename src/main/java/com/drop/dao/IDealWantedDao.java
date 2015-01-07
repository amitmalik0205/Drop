package com.drop.dao;

import java.util.List;

import com.drop.dao.domain.DealWanted;

public interface IDealWantedDao extends IGenericDao<DealWanted> {

	public List<DealWanted> getAllDealWantedForUser(Long userId);
	
	public DealWanted getDealWantedWithUser(long dealWantedId);
}
