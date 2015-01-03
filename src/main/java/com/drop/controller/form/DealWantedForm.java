package com.drop.controller.form;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.drop.dao.domain.DealCategory;

public class DealWantedForm {

	@NotEmpty(message = "Deal title is required")
	@Length(min=10,max=50)
	private String title;
	
	@NotEmpty(message = "Deal description is required")
	@Length(min=10,max=250)
	private String description;
	
	@NotNull(message = "Please select a category")
	@Min(value = 1, message = "Please select a category")
	private Long category;
	
	@NotNull(message = "Please enter maximum price")
	@Min(value = 1, message = "Maximum Price must be greater then 1")
	@Max(value = Long.MAX_VALUE, message = "Maximum Price is out of range")
	private BigDecimal maxPrice;
	
	private BigDecimal tipAmount;
	
	private List<DealCategory> dealCategories;
	
	private Boolean acceptCoupons;
	
	private Boolean wouldBuyOnline;

	private Boolean wouldBuyLocally;

	private Boolean wantNew;

	private Boolean wantUsed;

	private Boolean refurbishedOK;
	
	private String ipAddress;
	
	private Long userId;
	
	private Long dealWantedId;

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

	public BigDecimal getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(BigDecimal maxPrice) {
		this.maxPrice = maxPrice;
	}

	public BigDecimal getTipAmount() {
		return tipAmount;
	}

	public void setTipAmount(BigDecimal tipAmount) {
		this.tipAmount = tipAmount;
	}

	public List<DealCategory> getDealCategories() {
		return dealCategories;
	}

	public void setDealCategories(List<DealCategory> dealCategories) {
		this.dealCategories = dealCategories;
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

	public Long getDealWantedId() {
		return dealWantedId;
	}

	public void setDealWantedId(Long dealWantedId) {
		this.dealWantedId = dealWantedId;
	}

	
}
