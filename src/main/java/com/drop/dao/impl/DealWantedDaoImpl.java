package com.drop.dao.impl;

import org.springframework.stereotype.Repository;

import com.drop.dao.IDealWantedDao;
import com.drop.dao.domain.DealWanted;

@Repository
public class DealWantedDaoImpl extends GenericDaoImpl<DealWanted> implements IDealWantedDao {

	public DealWantedDaoImpl() {
		super(DealWanted.class);
	}
}
