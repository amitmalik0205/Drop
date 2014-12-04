package com.drop.session.object;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

import com.drop.dao.domain.MailingAddress;

public class SessionUser implements Serializable {

	private static final long serialVersionUID = -6499650610330369835L;
	
	private Long userId;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String password;
	
	private BigDecimal moneyEarned;	// how much they have made from posting drops
	
	private BigDecimal moneyPaid;  // how much money they have paid others to find drops
	
	private Integer totalPosts;
	
	private Integer warningsSent;	// number of warnings sent to user
	
	private String phoneNumber;	// for SMS
	
	private String skypeName;
	
	private Boolean autoApproveListings;	// system function.  if true, then no need for drop.com admin to approve listing
	
	private String autoApproveWarning;   // the reason the MonitoringService gave on why we need to not auto-approve
	
	private Set<MailingAddress> addresses;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public BigDecimal getMoneyEarned() {
		return moneyEarned;
	}

	public void setMoneyEarned(BigDecimal moneyEarned) {
		this.moneyEarned = moneyEarned;
	}

	public BigDecimal getMoneyPaid() {
		return moneyPaid;
	}

	public void setMoneyPaid(BigDecimal moneyPaid) {
		this.moneyPaid = moneyPaid;
	}

	public Integer getTotalPosts() {
		return totalPosts;
	}

	public void setTotalPosts(Integer totalPosts) {
		this.totalPosts = totalPosts;
	}

	public Integer getWarningsSent() {
		return warningsSent;
	}

	public void setWarningsSent(Integer warningsSent) {
		this.warningsSent = warningsSent;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getSkypeName() {
		return skypeName;
	}

	public void setSkypeName(String skypeName) {
		this.skypeName = skypeName;
	}

	public Boolean getAutoApproveListings() {
		return autoApproveListings;
	}

	public void setAutoApproveListings(Boolean autoApproveListings) {
		this.autoApproveListings = autoApproveListings;
	}

	public String getAutoApproveWarning() {
		return autoApproveWarning;
	}

	public void setAutoApproveWarning(String autoApproveWarning) {
		this.autoApproveWarning = autoApproveWarning;
	}

	public Set<MailingAddress> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<MailingAddress> addresses) {
		this.addresses = addresses;
	}
}
