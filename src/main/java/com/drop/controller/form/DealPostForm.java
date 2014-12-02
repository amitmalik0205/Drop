package com.drop.controller.form;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.drop.dao.domain.DealCategory;

public class DealPostForm {
	
	private String title;

	private String description;
	
	private Long category;
	
	private List<DealCategory> dealCategories;	

	private BigDecimal salePrice;
	
	private BigDecimal retailPrice;
	
	private Double discountPercent;
	
	private Date expires;
	
	private Date starts;
	
	private String specialInstructions;
	
	private Boolean onlineDeal;

	private Boolean localDeal;

	private Boolean couponsRequired;

	private Boolean membershipRequired;

	private String ipAddress;
	
	private Long userId;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getCategory() {
		return category;
	}

	public void setCategory(Long category) {
		this.category = category;
	}

	public List<DealCategory> getDealCategories() {
		return dealCategories;
	}

	public void setDealCategories(List<DealCategory> dealCategories) {
		this.dealCategories = dealCategories;
	}

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	public BigDecimal getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(BigDecimal retailPrice) {
		this.retailPrice = retailPrice;
	}

	public Double getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(Double discountPercent) {
		this.discountPercent = discountPercent;
	}

	public Date getExpires() {
		return expires;
	}

	public void setExpires(Date expires) {
		this.expires = expires;
	}

	public Date getStarts() {
		return starts;
	}

	public void setStarts(Date starts) {
		this.starts = starts;
	}

	public String getSpecialInstructions() {
		return specialInstructions;
	}

	public void setSpecialInstructions(String specialInstructions) {
		this.specialInstructions = specialInstructions;
	}

	public Boolean getOnlineDeal() {
		return onlineDeal;
	}

	public void setOnlineDeal(Boolean onlineDeal) {
		this.onlineDeal = onlineDeal;
	}

	public Boolean getLocalDeal() {
		return localDeal;
	}

	public void setLocalDeal(Boolean localDeal) {
		this.localDeal = localDeal;
	}

	public Boolean getCouponsRequired() {
		return couponsRequired;
	}

	public void setCouponsRequired(Boolean couponsRequired) {
		this.couponsRequired = couponsRequired;
	}

	public Boolean getMembershipRequired() {
		return membershipRequired;
	}

	public void setMembershipRequired(Boolean membershipRequired) {
		this.membershipRequired = membershipRequired;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
