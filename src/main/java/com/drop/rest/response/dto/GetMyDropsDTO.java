package com.drop.rest.response.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class GetMyDropsDTO implements Serializable {

	private static final long serialVersionUID = -9140894440353512396L;
	
	private Long dropId;
	
	private String title;
	
	private Long categoryId;
	
	private String categoryName;
	
	private String expires;
	
	private String starts;
	
	private BigDecimal salePrice;
	
	private BigDecimal retailPrice;

	private String description;	
	
	private String specialInstructions;
	
	private Boolean couponsRequired;

	private Boolean membershipRequired;
	
	private String dealType;
	
	private String url;
	
	private String addressLine1;

	private String addressLine2;

	private String city;

	private String state;

	private String zip; 
	
	private String imagePath;

	public Long getDropId() {
		return dropId;
	}

	public void setDropId(Long dropId) {
		this.dropId = dropId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getExpires() {
		return expires;
	}

	public void setExpires(String expires) {
		this.expires = expires;
	}

	public String getStarts() {
		return starts;
	}

	public void setStarts(String starts) {
		this.starts = starts;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSpecialInstructions() {
		return specialInstructions;
	}

	public void setSpecialInstructions(String specialInstructions) {
		this.specialInstructions = specialInstructions;
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

	public String getDealType() {
		return dealType;
	}

	public void setDealType(String dealType) {
		this.dealType = dealType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

}
