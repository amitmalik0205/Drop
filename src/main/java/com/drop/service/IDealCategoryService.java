package com.drop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.drop.dao.domain.DealCategory;

@Service
public interface IDealCategoryService {

	public List<DealCategory> getAllDealCategories();
}
