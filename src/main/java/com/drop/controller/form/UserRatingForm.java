package com.drop.controller.form;

public class UserRatingForm {

	private long dealMatchId;
	
	private int rating;
	
	private String description;

	public long getDealMatchId() {
		return dealMatchId;
	}

	public void setDealMatchId(long dealMatchId) {
		this.dealMatchId = dealMatchId;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
