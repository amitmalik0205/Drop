package com.drop.dao.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "user")
@NamedQueries({
    @NamedQuery(name = "User.getUserByEmailAndPassword", query = "FROM User WHERE email = :email and password = :pwd"),
    @NamedQuery(name = "User.getUserByEmail", query = "FROM User WHERE email = :email"),
})
public class User implements Serializable {

	private static final long serialVersionUID = -1997512730718094749L;

	@Id
	@GeneratedValue
	@Column(name = "userId")
	private Long userId;
	
	@Column(name = "firstName", nullable = false)
	private String firstName;
	
	@Column(name = "lastName", nullable = false)
	private String lastName;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "moneyEarned")
	private BigDecimal moneyEarned;	// how much they have made from posting drops
	
	@Column(name = "moneyPaid")
	private BigDecimal moneyPaid;  // how much money they have paid others to find drops
	
	@Column(name = "totalPosts")
	private Integer totalPosts;
	
	@Column(name = "location")
	private String locationDescription;	// city, state
	
	@Column(name = "warningsSent")
	private Integer warningsSent;	// number of warnings sent to user
	
	@Column(name = "phoneNumber")
	private String phoneNumber;	// for SMS
	
	@Column(name = "skypeName")
	private String skypeName;
	
	@Column(name = "autoApproveListings")
	private Boolean autoApproveListings;	// system function.  if true, then no need for drop.com admin to approve listing
	
	@Column(name = "autoApproveWarning")
	private String autoApproveWarning;   // the reason the MonitoringService gave on why we need to not auto-approve
	
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name = "user_id")
	@OrderBy("id")
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

	public String getLocationDescription() {
		return locationDescription;
	}

	public void setLocationDescription(String locationDescription) {
		this.locationDescription = locationDescription;
	}

	public Set<MailingAddress> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<MailingAddress> addresses) {
		this.addresses = addresses;
	}
}
