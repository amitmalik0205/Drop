package com.drop.service;

import java.util.List;

import com.drop.dao.domain.DealCategory;
import com.drop.rest.request.dto.GetDealCategoriesDTO;

public interface IDealCategoryService {

	public List<DealCategory> getAllDealCategories();

	public String getCategoryImageName(long categoryId);
	
	public List<GetDealCategoriesDTO> getAllDealCategoriesDto();
}
