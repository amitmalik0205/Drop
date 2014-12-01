package com.drop.dao.impl;

import org.springframework.stereotype.Repository;

import com.drop.dao.IDealCategoryDao;
import com.drop.dao.domain.DealCategory;

@Repository("dealCategory")
public class DealCategoryDaoImpl extends GenericDaoImpl<DealCategory> implements
		IDealCategoryDao {
	
	public DealCategoryDaoImpl() {
		super(DealCategory.class);
	}

}
