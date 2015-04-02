package com.drop.rest.services;

import java.io.Serializable;

public class DropServiceResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String code;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
