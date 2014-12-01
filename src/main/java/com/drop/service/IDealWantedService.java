package com.drop.service;

import org.springframework.stereotype.Service;

import com.drop.controller.form.DealWantedForm;

@Service
public interface IDealWantedService {

	public void saveDealWanted(DealWantedForm form);
}
