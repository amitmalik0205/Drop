package com.drop.service;

import com.drop.controller.form.DealMatchForm;
import com.drop.dao.domain.DealMatch;

public interface IDealMatchService {

	public void saveDealMatch(DealMatchForm form);

	/**
	 * Method will fetch DealMatch and user details
	 * 
	 * @param dealWantedId
	 * @param dealPostId
	 * @return
	 */
	public DealMatch getDealMatchWithUserByDealWanted(long dealWantedId,
			long dealPostId);

	public DealMatch getDealMatchByDealWantedAndDealPost(long dealWantedId,
			long dealPostId);
	
	public void saveOrUpdate(DealMatch dealMatch);

}
