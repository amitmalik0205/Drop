package com.drop.dto;

import java.math.BigDecimal;
import java.util.Date;

public class DealWantedDTO {

	private Long id;
	
	private String imageName;
	

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private BigDecimal maxPrice;

	public BigDecimal getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(BigDecimal maxPrice) {
		this.maxPrice = maxPrice;
	}

	private BigDecimal tipAmount; // how much the dropee will pay to the finder

	public BigDecimal getTipAmount() {
		return tipAmount;
	}

	public void setTipAmount(BigDecimal tipAmount) {
		this.tipAmount = tipAmount;
	}

	private Boolean acceptCoupons;

	public Boolean getAcceptCoupons() {
		return acceptCoupons;
	}

	public void setAcceptCoupons(Boolean acceptCoupons) {
		this.acceptCoupons = acceptCoupons;
	}

	private Boolean wouldBuyOnline;

	public Boolean getWouldBuyOnline() {
		return wouldBuyOnline;
	}

	public void setWouldBuyOnline(Boolean wouldBuyOnline) {
		this.wouldBuyOnline = wouldBuyOnline;
	}

	private Boolean wouldBuyLocally;

	public Boolean getWouldBuyLocally() {
		return wouldBuyLocally;
	}

	public void setWouldBuyLocally(Boolean wouldBuyLocally) {
		this.wouldBuyLocally = wouldBuyLocally;
	}

	private Boolean wantNew;

	public Boolean getWantNew() {
		return wantNew;
	}

	public void setWantNew(Boolean wantNew) {
		this.wantNew = wantNew;
	}

	private Boolean wantUsed;

	public Boolean getWantUsed() {
		return wantUsed;
	}

	public void setWantUsed(Boolean wantUsed) {
		this.wantUsed = wantUsed;
	}

	private Boolean refurbishedOK;

	public Boolean getRefurbishedOK() {
		return refurbishedOK;
	}

	public void setRefurbishedOK(Boolean refurbishedOK) {
		this.refurbishedOK = refurbishedOK;
	}

	// private DealMatchID matchedId;

	private Boolean active;

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	private String dealCategory;

	public String getDealCategory() {
		return dealCategory;
	}

	public void setDealCategory(String dealCategory) {
		this.dealCategory = dealCategory;
	}

	private Date createdOn;

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

}
