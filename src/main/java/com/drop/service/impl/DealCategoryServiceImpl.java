package com.drop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.drop.dao.IDealCategoryDao;
import com.drop.dao.domain.DealCategory;
import com.drop.service.IDealCategoryService;

@Service
public class DealCategoryServiceImpl implements IDealCategoryService {

	@Autowired
	private IDealCategoryDao categoryDao;
	
	@Override
	@Transactional
	public List<DealCategory> getAllDealCategories() {
		return categoryDao.findAll();
	}
}
