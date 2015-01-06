package com.drop.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.drop.dao.domain.User;
import com.drop.util.DropUtil;

public class DealPostDTO {

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

	private BigDecimal salePrice;

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	private String dealCategory;

	public String getDealCategory() {
		return dealCategory;
	}

	public void setDealCategory(String dealCategory) {
		this.dealCategory = dealCategory;
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

	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	private Date expires;

	public Date getExpires() {
		return expires;
	}

	public void setExpires(Date expires) {
		this.expires = expires;
		this.setTimeToExpire(DropUtil.getDealTimeToExpire(expires));
	}

	private Date starts;

	public Date getStarts() {
		return starts;
	}

	public void setStarts(Date starts) {
		this.starts = starts;
	}

	private Double discountPercent;

	public Double getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(Double discountPercent) {
		this.discountPercent = discountPercent;
	}

	private BigDecimal retailPrice;

	public BigDecimal getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(BigDecimal retailPrice) {
		this.retailPrice = retailPrice;
	}

	private Boolean onlineDeal;

	public Boolean getOnlineDeal() {
		return onlineDeal;
	}

	public void setOnlineDeal(Boolean onlineDeal) {
		this.onlineDeal = onlineDeal;
	}

	private Boolean localDeal;

	public Boolean getLocalDeal() {
		return localDeal;
	}

	public void setLocalDeal(Boolean localDeal) {
		this.localDeal = localDeal;
	}

	private String specialInstructions;

	public String getSpecialInstructions() {
		return specialInstructions;
	}

	public void setSpecialInstructions(String specialInstructions) {
		this.specialInstructions = specialInstructions;
	}

	private Boolean couponsRequired;

	public Boolean getCouponsRequired() {
		return couponsRequired;
	}

	public void setCouponsRequired(Boolean couponsRequired) {
		this.couponsRequired = couponsRequired;
	}

	private Boolean membershipRequired;

	public Boolean getMembershipRequired() {
		return membershipRequired;
	}

	public void setMembershipRequired(Boolean membershipRequired) {
		this.membershipRequired = membershipRequired;
	}

	private Boolean active;

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	private Date createdOn;

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
		this.date = DropUtil.convertDateToString(createdOn);
	}

	private String date;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	private String timeToExpire;

	public String getTimeToExpire() {
		return timeToExpire;
	}

	public void setTimeToExpire(String timeToExpire) {
		this.timeToExpire = timeToExpire;
	}

}
