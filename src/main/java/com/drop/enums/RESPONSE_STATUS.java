package com.drop.enums;

public enum RESPONSE_STATUS {

	SUCCESS("SUCCESS"), ERROR("ERROR");
	
	private final String status;

	private RESPONSE_STATUS(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
}
