package com.drop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.drop.dao.IMailingAddressDao;
import com.drop.dao.domain.MailingAddress;
import com.drop.service.IMailingAddressService;

@Service
public class MailingAddressServiceImpl implements IMailingAddressService {

	@Autowired
	private IMailingAddressDao mailingAddressDao;
	
	@Override
	@Transactional
	public MailingAddress getMailingAddressById(Long id) {
		return mailingAddressDao.getEntity(id);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveOrUpdate(MailingAddress address) {
		mailingAddressDao.saveOrUpdate(address);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delete(MailingAddress address) {
		mailingAddressDao.delete(address);
	}
}
