package com.drop.service;

import com.drop.dao.domain.MailingAddress;

public interface IMailingAddressService {

	public MailingAddress getMailingAddressById(Long id);
	
	public void saveOrUpdate(MailingAddress address);
	
	public void delete(MailingAddress address);
}
