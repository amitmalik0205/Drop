package com.drop.controller.form;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class AccountSettingsForm {

	@NotEmpty(message = "First Name is required")
	private String firstName;
	
	@NotEmpty(message = "Last Name is required")
	private String lastName;
	
	@Email(message = "Wrong email format")
	@NotEmpty(message = "Email is required")
	private String email;
	
	@Pattern(regexp="(^$|[0-9])", message="Only numbers are allowed in phone number")
	private String phone;
	
	private String skypeName;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSkypeName() {
		return skypeName;
	}

	public void setSkypeName(String skypeName) {
		this.skypeName = skypeName;
	}
}
