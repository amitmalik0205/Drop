package com.drop.dao.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "business_details")
public class BusinessDetails {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name  = "user_id")
	private User user;
	
	@Column(name = "business_desc")
	private String businessDesc;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getBusinessDesc() {
		return businessDesc;
	}

	public void setBusinessDesc(String businessDesc) {
		this.businessDesc = businessDesc;
	}
}
