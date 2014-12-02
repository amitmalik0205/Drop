package com.drop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drop.controller.form.DealPostForm;
import com.drop.dao.IDealPostDao;
import com.drop.service.IDealPostService;

@Service
public class DealPostServiceImpl implements IDealPostService {
	
	@Autowired
	private IDealPostDao dealPostDao;

	@Override
	public void saveDealPost(DealPostForm form) {
		
	}

}
