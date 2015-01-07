package com.drop.service;

import java.util.List;

import com.drop.controller.form.DealWantedForm;
import com.drop.controller.form.ReasonToDeleteForm;
import com.drop.dao.domain.DealWanted;

public interface IDealWantedService {

	public Long saveDealWanted(DealWantedForm form);
	
	public List<DealWanted> getAllDealWantedForUser(Long userId);
	
	public void saveOrUpdate(DealWantedForm form);
	
	public DealWanted getDealWantedById(Long dealWantedId);
	
	public void deleteDealWanted(ReasonToDeleteForm form);
	
	public List<DealWanted> getAllDealWanted();
	
	public DealWanted loadDealWantedById(Long dealWantedId);
}
