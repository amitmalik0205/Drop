package com.drop.dao.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "deal_post")
public class DealPost implements Serializable {

	private static final long serialVersionUID = 3534368434423368L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@Column(name = "sale_price")
	private BigDecimal salePrice;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "deal_category_id")
	private DealCategory dealCategory;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "expires")
	@Temporal(TemporalType.TIMESTAMP)
	private Date expires;

	@Column(name = "starts")
	@Temporal(TemporalType.TIMESTAMP)
	private Date starts;

	@Column(name = "discount_percent")
	private Double discountPercent;

	@Column(name = "retail_price")
	private BigDecimal retailPrice;

	@Column(name = "online_deal")
	private Boolean onlineDeal;

	@Column(name = "local_deal")
	private Boolean localDeal;

	@Column(name = "special_instructions")
	private String specialInstructions;

	@Column(name = "coupons_required")
	private Boolean couponsRequired;

	@Column(name = "membership_required")
	private Boolean membershipRequired;

	@Column(name = "active")
	private Boolean active;

	@Column(name = "reason_for_deleting")
	private String reasonForDeleting;

	@Column(name = "ip_address")
	private String ipAddress; // location of user's IP when they posted this

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "location_id")
	private Location location;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}
	
	public DealCategory getDealCategory() {
		return dealCategory;
	}

	public void setDealCategory(DealCategory dealCategory) {
		this.dealCategory = dealCategory;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public Double getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(Double discountPercent) {
		this.discountPercent = discountPercent;
	}

	public BigDecimal getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(BigDecimal retailPrice) {
		this.retailPrice = retailPrice;
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

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	
}
