package com.drop.controller.form;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.drop.dao.domain.DealCategory;

public class DealPostForm {
	
	@NotEmpty(message = "Deal title is required")
	@Length(max=150)
	private String title;

	@NotEmpty(message = "Deal description is required")
	@Length(max=150)
	private String description;
	
	@NotNull(message = "Please select a category")
	@Min(value = 1, message = "Please select a category")
	private Long category;
	
	private List<DealCategory> dealCategories;	

	@NotNull(message = "Sale Price is required")
	@Min(value = 1, message = "Sale Price must be greater then 1")
	@Max(value = Long.MAX_VALUE, message = "Sale Price is out of range")
	private BigDecimal salePrice;
	
	@NotNull(message = "Retail Price is required")
	@Min(value = 1, message = "Retail Price must be greater then 1")
	@Max(value = Long.MAX_VALUE, message = "Retail Price is out of range")
	private BigDecimal retailPrice;
	
	private Double discountPercent;
	
	private String expires;
	
	private String starts;
	
	@Length(max=150)
	private String specialInstructions;
	
	@NotNull(message = "Please select deal type")
	private String dealType;

	private Boolean couponsRequired;

	private Boolean membershipRequired;

	private String ipAddress;
	
	private Long userId;
	
	private String addressLine1;

	private String addressLine2;

	private String city;

	private String state;

	private String zip; 
	
	private String url;
	
	private Long dealPostId;

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
	
	public String getDealType() {
		return dealType;
	}

	public void setDealType(String dealType) {
		this.dealType = dealType;
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
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getDealPostId() {
		return dealPostId;
	}

	public void setDealPostId(Long dealPostId) {
		this.dealPostId = dealPostId;
	}
}
