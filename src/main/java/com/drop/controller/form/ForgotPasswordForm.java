package com.drop.controller.form;

import org.hibernate.validator.constraints.Email;

public class ForgotPasswordForm {

	@Email(message = "Wrong email format")
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
