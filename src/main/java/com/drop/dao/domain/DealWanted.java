package com.drop.dao.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.drop.util.DropUtil;


@Entity
@Table(name = "deal_wanted")
@NamedQueries({
    @NamedQuery(name = "DealWanted.getAllActiveDealWantedForUser", 
    		query = "FROM DealWanted dw join fetch dw.dealCategory dc WHERE dw.user.userId = :userId and dw.active=true")
})

public class DealWanted implements Serializable {
	
	private static final long serialVersionUID = 4328274086721337311L;

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

	private BigDecimal maxPrice;
	
	@Column(name = "max_price")
	public BigDecimal getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(BigDecimal maxPrice) {
		this.maxPrice = maxPrice;
	}
	
	private BigDecimal tipAmount; // how much the dropee will pay to the finder
	
	@Column(name = "tip_amount")
	public BigDecimal getTipAmount() {
		return tipAmount;
	}

	public void setTipAmount(BigDecimal tipAmount) {
		this.tipAmount = tipAmount;
	}

	private Boolean acceptCoupons;
	
	@Column(name = "accept_coupons")
	public Boolean getAcceptCoupons() {
		return acceptCoupons;
	}

	public void setAcceptCoupons(Boolean acceptCoupons) {
		this.acceptCoupons = acceptCoupons;
	}

	private Boolean wouldBuyOnline;
	
	@Column(name = "would_buy_online")
	public Boolean getWouldBuyOnline() {
		return wouldBuyOnline;
	}

	public void setWouldBuyOnline(Boolean wouldBuyOnline) {
		this.wouldBuyOnline = wouldBuyOnline;
	}

	private Boolean wouldBuyLocally;
	
	@Column(name = "would_buy_locally")
	public Boolean getWouldBuyLocally() {
		return wouldBuyLocally;
	}

	public void setWouldBuyLocally(Boolean wouldBuyLocally) {
		this.wouldBuyLocally = wouldBuyLocally;
	}

	private Boolean wantNew;
	
	@Column(name = "want_new")
	public Boolean getWantNew() {
		return wantNew;
	}

	public void setWantNew(Boolean wantNew) {
		this.wantNew = wantNew;
	}

	private Boolean wantUsed;
	
	@Column(name = "want_used")
	public Boolean getWantUsed() {
		return wantUsed;
	}

	public void setWantUsed(Boolean wantUsed) {
		this.wantUsed = wantUsed;
	}

	private Boolean refurbishedOK;
	
	@Column(name = "refurbished_ok")
	public Boolean getRefurbishedOK() {
		return refurbishedOK;
	}

	public void setRefurbishedOK(Boolean refurbishedOK) {
		this.refurbishedOK = refurbishedOK;
	}

	// private DealMatchID matchedId;
	
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
	
	private DealCategory dealCategory;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "deal_category_id")
	public DealCategory getDealCategory() {
		return dealCategory;
	}

	public void setDealCategory(DealCategory dealCategory) {
		this.dealCategory = dealCategory;
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

}
