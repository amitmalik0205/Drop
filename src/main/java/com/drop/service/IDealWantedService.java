package com.drop.service;

import java.util.List;

import com.drop.controller.form.DealWantedForm;
import com.drop.dao.domain.DealWanted;

public interface IDealWantedService {

	public void saveDealWanted(DealWantedForm form);
	
	public List<DealWanted> getAllDealWantedForUser(Long userId);
}
