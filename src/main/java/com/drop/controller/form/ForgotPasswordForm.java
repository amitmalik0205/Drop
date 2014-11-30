package com.drop.controller.form;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class ForgotPasswordForm {

	@Email(message = "Wrong email format")
	@NotEmpty(message = "Email is required")
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
