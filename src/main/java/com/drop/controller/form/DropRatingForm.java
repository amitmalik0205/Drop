package com.drop.controller.form;

public class DropRatingForm {

	private long dealPostId;
	
	private long dealWantedId;
	
	private String description;
	
	private int rating;

	public long getDealPostId() {
		return dealPostId;
	}

	public void setDealPostId(long dealPostId) {
		this.dealPostId = dealPostId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public long getDealWantedId() {
		return dealWantedId;
	}

	public void setDealWantedId(long dealWantedId) {
		this.dealWantedId = dealWantedId;
	}
}
