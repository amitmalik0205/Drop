package com.drop.enums;


public enum DEAL_MATCH_STATUS {
	
	ACCEPTED("ACCEPTED"), REJECTED("REJECTED"), GOTIT("GOTIT");

	private final String status;

	private DEAL_MATCH_STATUS(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
}
