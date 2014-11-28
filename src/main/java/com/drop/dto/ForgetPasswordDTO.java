package com.drop.dto;

import java.io.Serializable;

public class ForgetPasswordDTO implements Serializable {

	private static final long serialVersionUID = 4471738198603801697L;

	private String password;
	
	private String firstName;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
}
