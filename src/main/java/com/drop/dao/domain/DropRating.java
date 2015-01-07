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
@Table(name = "drop_rating")
public class DropRating implements Serializable {

	private static final long serialVersionUID = -6926066355715615932L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ratee_id")
	private User ratee;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "rater_id")
	private User rater;
	
	@Column(name = "rating")
	private Integer rating;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "deal_post_id")
	private DealPost dealPost;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getRatee() {
		return ratee;
	}

	public void setRatee(User ratee) {
		this.ratee = ratee;
	}

	public User getRater() {
		return rater;
	}

	public void setRater(User rater) {
		this.rater = rater;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
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

	public DealPost getDealPost() {
		return dealPost;
	}

	public void setDealPost(DealPost dealPost) {
		this.dealPost = dealPost;
	}
}
