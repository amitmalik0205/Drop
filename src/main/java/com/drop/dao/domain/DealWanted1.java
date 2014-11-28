package com.drop.dao.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "deal_wanted")
public class DealWanted1 {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private String id;

	@Column(name = "max_price")
	private BigDecimal maxPrice;

	@Column(name = "description")
	private String description;

	@Column(name = "accept_coupons")
	private Boolean acceptCoupons;

	@Column(name = "would_buy_online")
	private Boolean wouldBuyOnline;

	@Column(name = "would_buy_locally")
	private Boolean wouldBuyLocally;

	@Column(name = "tip_amount")
	private BigDecimal tipAmount; // how much the dropee will pay to the finder

	@Column(name = "want_new")
	private Boolean wantNew;

	@Column(name = "want_used")
	private Boolean wantUsed;

	@Column(name = "refurbished_ok")
	private Boolean refurbishedOK;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "deal_category_id")
	private DealCategory dealCategory;//

	// private DealMatchID matchedId;
	@Column(name = "active")
	private Boolean active;

	@Column(name = "reason_for_deleting")
	private String reasonForDeleting;

	@Column(name = "ip_address")
	private String ipAddress; // location of user's IP when they posted this
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigDecimal getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(BigDecimal maxPrice) {
		this.maxPrice = maxPrice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getAcceptCoupons() {
		return acceptCoupons;
	}

	public void setAcceptCoupons(Boolean acceptCoupons) {
		this.acceptCoupons = acceptCoupons;
	}

	public Boolean getWouldBuyOnline() {
		return wouldBuyOnline;
	}

	public void setWouldBuyOnline(Boolean wouldBuyOnline) {
		this.wouldBuyOnline = wouldBuyOnline;
	}

	public Boolean getWouldBuyLocally() {
		return wouldBuyLocally;
	}

	public void setWouldBuyLocally(Boolean wouldBuyLocally) {
		this.wouldBuyLocally = wouldBuyLocally;
	}

	public BigDecimal getTipAmount() {
		return tipAmount;
	}

	public void setTipAmount(BigDecimal tipAmount) {
		this.tipAmount = tipAmount;
	}

	public Boolean getWantNew() {
		return wantNew;
	}

	public void setWantNew(Boolean wantNew) {
		this.wantNew = wantNew;
	}

	public Boolean getWantUsed() {
		return wantUsed;
	}

	public void setWantUsed(Boolean wantUsed) {
		this.wantUsed = wantUsed;
	}

	public Boolean getRefurbishedOK() {
		return refurbishedOK;
	}

	public void setRefurbishedOK(Boolean refurbishedOK) {
		this.refurbishedOK = refurbishedOK;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getReasonForDeleting() {
		return reasonForDeleting;
	}

	public void setReasonForDeleting(String reasonForDeleting) {
		this.reasonForDeleting = reasonForDeleting;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public DealCategory getDealCategory() {
		return dealCategory;
	}

	public void setDealCategory(DealCategory dealCategory) {
		this.dealCategory = dealCategory;
	}

	
}
