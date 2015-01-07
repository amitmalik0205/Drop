package com.drop.dao.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

import com.drop.enums.DEAL_MATCH_STATUS;

/**
 * You can have many matches and the user can accept one or more.   Matches are always displayed FIFO and only one match is shown at a time. 
 * when the droppee rejects a match, the next match is shown.   When the dropee accepts a match, then all other matches are dead for this user.
 * 
 * @author mark
 *
 */

@Entity
@Table(name = "deal_match")
@NamedQueries({
    @NamedQuery(name = "DealMatch.getDealMatchByDealWantedAndDealPost", 
    		query = "FROM DealMatch dm WHERE dm.dealWanted.id=:dealWantedId AND dm.dealPost.id=:dealPostId"),
    		
    @NamedQuery(name = "DealMatch.getDealMatchWithUserByDealWanted", 
    		query = "FROM DealMatch dm JOIN FETCH dm.dealWanted dw JOIN FETCH dw.user u WHERE dw.id=:dealWantedId AND dm.dealPost.id=:dealPostId"),
    		
    @NamedQuery(name = "DealMatch.getDealMatchWithDealWanted",
    		query = "FROM DealMatch dm JOIN FETCH dm.dealWanted dw JOIN FETCH dw.user u WHERE dm.id= :dealMatchId and dm.status='ACCEPTED'"),
    
    @NamedQuery(name = "DealMatch.getDealMatchWithDealPost",
		query = "FROM DealMatch dm JOIN FETCH dm.dealPost dw JOIN FETCH dw.user u WHERE dm.id= :dealMatchId and dm.status='ACCEPTED'")
})

public class DealMatch implements Serializable {
	
	private static final long serialVersionUID = -6572223479626654798L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "deal_post_id")
	private DealPost dealPost;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "deal_wanted_id")
	private DealWanted dealWanted;	
	
	@Column(name = "date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date matchCreated;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status")	
	private DEAL_MATCH_STATUS status;
	
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

	public DEAL_MATCH_STATUS getStatus() {
		return status;
	}

	public void setStatus(DEAL_MATCH_STATUS status) {
		this.status = status;
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

	public DealWanted getDealWanted() {
		return dealWanted;
	}

	public void setDealWanted(DealWanted dealWanted) {
		this.dealWanted = dealWanted;
	}

	
}
