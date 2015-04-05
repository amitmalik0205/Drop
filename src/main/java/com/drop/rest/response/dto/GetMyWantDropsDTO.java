package com.drop.rest.response.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class GetMyWantDropsDTO implements Serializable {

	private static final long serialVersionUID = -2742358316537747253L;
	
	private Long wantDropId;
	
	private String title;
		
	private String description;
		
	private Long categoryId;
	
	private String categoryName;
		
	private BigDecimal maxPrice;
		
	private BigDecimal tipAmount;
	
	private Boolean acceptCoupons;
	
	private Boolean wouldBuyOnline;

	private Boolean wouldBuyLocally;

	private Boolean wantNew;

	private Boolean wantUsed;

	private Boolean refurbishedOK;
	
	private String email;
	
	
	public Long getWantDropId() {
		return wantDropId;
	}

	public void setWantDropId(Long wantDropId) {
		this.wantDropId = wantDropId;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
