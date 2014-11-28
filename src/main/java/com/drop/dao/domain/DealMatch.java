package com.drop.dao.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * You can have many matches and the user can accept one or more.   Matches are always displayed FIFO and only one match is shown at a time. 
 * when the droppee rejects a match, the next match is shown.   When the dropee accepts a match, then all other matches are dead for this user.
 * 
 * @author mark
 *
 */

@Entity
@Table(name = "deal_match")
public class DealMatch {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "deal_post_id")
	private DealPost dealPost;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "deal_wanted_id")
	private DealWanted1 dealWanted;	
	
	@Column(name = "date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date matchCreated;
	
	@Column(name = "matchAccepted")	
	private Boolean matchAccepted;
	
	@Column(name = "acceptedDate")		
	private Date acceptedDate;
	
	@Column(name = "tipPaid")
	private BigDecimal tipPaid;
	
	@Column(name = "date_tip_available")		
	private Date dateTipAvailable;
	
	@Column(name = "rejected_reason")	
	private String rejectedReason;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DealPost getDealPost() {
		return dealPost;
	}

	public void setDealPost(DealPost dealPost) {
		this.dealPost = dealPost;
	}

	public Date getMatchCreated() {
		return matchCreated;
	}

	public void setMatchCreated(Date matchCreated) {
		this.matchCreated = matchCreated;
	}

	public Boolean getMatchAccepted() {
		return matchAccepted;
	}

	public void setMatchAccepted(Boolean matchAccepted) {
		this.matchAccepted = matchAccepted;
	}

	public Date getAcceptedDate() {
		return acceptedDate;
	}

	public void setAcceptedDate(Date acceptedDate) {
		this.acceptedDate = acceptedDate;
	}

	public BigDecimal getTipPaid() {
		return tipPaid;
	}

	public void setTipPaid(BigDecimal tipPaid) {
		this.tipPaid = tipPaid;
	}

	public Date getDateTipAvailable() {
		return dateTipAvailable;
	}

	public void setDateTipAvailable(Date dateTipAvailable) {
		this.dateTipAvailable = dateTipAvailable;
	}

	public String getRejectedReason() {
		return rejectedReason;
	}

	public void setRejectedReason(String rejectedReason) {
		this.rejectedReason = rejectedReason;
	}

	public DealWanted1 getDealWanted() {
		return dealWanted;
	}

	public void setDealWanted(DealWanted1 dealWanted) {
		this.dealWanted = dealWanted;
	}

	
}
