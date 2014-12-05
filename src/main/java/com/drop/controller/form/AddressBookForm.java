package com.drop.controller.form;

import java.util.LinkedHashSet;
import java.util.Set;

import com.drop.dao.domain.MailingAddress;

public class AddressBookForm {

	private String addressLine1;
	
	private String addressLine2;
	
	private String state;
	
	private String city;
	
	private String zip;
	
	Set<MailingAddress> addressList = new LinkedHashSet<MailingAddress>();
	
	private Long addressId;

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public Set<MailingAddress> getAddressList() {
		return addressList;
	}

	public void setAddressList(Set<MailingAddress> addressList) {
		this.addressList = addressList;
	}
	
	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
}
