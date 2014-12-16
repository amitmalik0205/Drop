package com.drop.dao.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.drop.util.DropUtil;

@Entity
@Table(name = "deal_post")
@NamedQueries({
    @NamedQuery(name = "DealPost.getAllActiveDealPostForUser", 
    		query = "FROM DealPost dp join fetch dp.dealCategory dc join fetch dp.user u WHERE u.userId = :userId and dp.active=true")
})

public class DealPost implements Serializable {

	private static final long serialVersionUID = 3534368434423368L;

	
	private Long id;
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	@Access(AccessType.PROPERTY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	private BigDecimal salePrice;
	
	@Column(name = "sale_price")
	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}


	private DealCategory dealCategory;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "deal_category_id")
	public DealCategory getDealCategory() {
		return dealCategory;
	}

	public void setDealCategory(DealCategory dealCategory) {
		this.dealCategory = dealCategory;
	}

	private String title;
	
	@Column(name = "title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
	private String description;
	
	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	private Date expires;
	
	@Column(name = "expires")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getExpires() {
		return expires;
	}

	public void setExpires(Date expires) {
		this.expires = expires;
	}

	
	private Date starts;
	
	@Column(name = "starts")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getStarts() {
		return starts;
	}

	public void setStarts(Date starts) {
		this.starts = starts;
	}
	

	private Double discountPercent;
	
	@Column(name = "discount_percent")
	public Double getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(Double discountPercent) {
		this.discountPercent = discountPercent;
	}

	
	private BigDecimal retailPrice;
	
	@Column(name = "retail_price")
	public BigDecimal getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(BigDecimal retailPrice) {
		this.retailPrice = retailPrice;
	}
	

	private Boolean onlineDeal;
	
	@Column(name = "online_deal")
	public Boolean getOnlineDeal() {
		return onlineDeal;
	}

	public void setOnlineDeal(Boolean onlineDeal) {
		this.onlineDeal = onlineDeal;
	}
	

	private Boolean localDeal;
	
	@Column(name = "local_deal")
	public Boolean getLocalDeal() {
		return localDeal;
	}

	public void setLocalDeal(Boolean localDeal) {
		this.localDeal = localDeal;
	}
	
	
	private String specialInstructions;
	
	@Column(name = "special_instructions")
	public String getSpecialInstructions() {
		return specialInstructions;
	}

	public void setSpecialInstructions(String specialInstructions) {
		this.specialInstructions = specialInstructions;
	}
	

	private Boolean couponsRequired;
	
	@Column(name = "coupons_required")
	public Boolean getCouponsRequired() {
		return couponsRequired;
	}

	public void setCouponsRequired(Boolean couponsRequired) {
		this.couponsRequired = couponsRequired;
	}
	
	
	private Boolean membershipRequired;
	
	@Column(name = "membership_required")
	public Boolean getMembershipRequired() {
		return membershipRequired;
	}

	public void setMembershipRequired(Boolean membershipRequired) {
		this.membershipRequired = membershipRequired;
	}
	
	
	private Boolean active;
	
	@Column(name = "active")
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	
	private String reasonForDeleting;
	
	@Column(name = "reason_for_deleting")
	public String getReasonForDeleting() {
		return reasonForDeleting;
	}

	public void setReasonForDeleting(String reasonForDeleting) {
		this.reasonForDeleting = reasonForDeleting;
	}


	private String ipAddress; // location of user's IP when they posted this
	
	@Column(name = "ip_address")
	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	private Location location;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "location_id")
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	private Date createdOn;
	
	@Column(name = "created_on")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
		this.date = DropUtil.convertDateToString(createdOn);
	}
	
	private String date;

	@Transient
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	
	private Date updatedOn;

	@Column(name = "updated_on")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	
	
	private String startedDateString;
	
	@Transient
	public String getStartedDateString() {
		return startedDateString;
	}

	public void setStartedDateString(String startedDateString) {
		this.startedDateString = startedDateString;
		this.startedDateString = DropUtil.convertDateToString(starts);
	}


	private String endDateString;

	@Transient
	public String getEndDateString() {
		return endDateString;
	}

	public void setEndDateString(String endDateString) {
		this.endDateString = endDateString;
		this.endDateString = DropUtil.convertDateToString(expires);
	}
}
