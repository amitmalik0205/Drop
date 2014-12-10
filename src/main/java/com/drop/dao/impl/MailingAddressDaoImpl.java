package com.drop.dao.impl;

import org.springframework.stereotype.Repository;

import com.drop.dao.IMailingAddressDao;
import com.drop.dao.domain.MailingAddress;

@Repository
public class MailingAddressDaoImpl extends GenericDaoImpl<MailingAddress>
		implements IMailingAddressDao {

	public MailingAddressDaoImpl() {
		super(MailingAddress.class);
	}

}
