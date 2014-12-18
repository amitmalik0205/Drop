package com.drop.controller.form;

public class DealMatchForm {
	
	private long dealPostId;
	
	private long dealWantedId;
	
	private String reasonToReject;

	public long getDealPostId() {
		return dealPostId;
	}

	public void setDealPostId(long dealPostId) {
		this.dealPostId = dealPostId;
	}

	public long getDealWantedId() {
		return dealWantedId;
	}

	public void setDealWantedId(long dealWantedId) {
		this.dealWantedId = dealWantedId;
	}

	public String getReasonToReject() {
		return reasonToReject;
	}

	public void setReasonToReject(String reasonToReject) {
		this.reasonToReject = reasonToReject;
	}	
}
