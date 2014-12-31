package com.drop.controllers;

import org.springframework.ui.ModelMap;

import com.drop.controller.form.SearchDealForm;

public class BaseController {

	protected void initializeCommonModel(ModelMap map) {
		SearchDealForm dealForm = new SearchDealForm();
		map.addAttribute("searchDealForm", dealForm);
	}
}
