package com.drop.dao.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
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


@Entity
@Table(name = "user_rating")
public class UserRating implements Serializable {
	
	private static final long serialVersionUID = -5965639004241643763L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ratee_id")
	private User rateeId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rater_id")
	private User raterId;
	
	@Column(name = "rating")
	private Integer rating;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	// the match the user was rated on
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "deal_match_id")
	private DealMatch dealMatch;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getRateeId() {
		return rateeId;
	}

	public void setRateeId(User rateeId) {
		this.rateeId = rateeId;
	}

	public User getRaterId() {
		return raterId;
	}

	public void setRaterId(User raterId) {
		this.raterId = raterId;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public DealMatch getDealMatch() {
		return dealMatch;
	}

	public void setDealMatch(DealMatch dealMatch) {
		this.dealMatch = dealMatch;
	}
	
	
	
}
