package com.drop.service;

import java.util.List;

import com.drop.dao.domain.DealCategory;

public interface IDealCategoryService {

	public List<DealCategory> getAllDealCategories();

	public String getCategoryImageName(long categoryId);
}
