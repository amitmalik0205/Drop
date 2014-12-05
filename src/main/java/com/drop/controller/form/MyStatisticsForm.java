package com.drop.controller.form;

import java.math.BigDecimal;

public class MyStatisticsForm {

	private BigDecimal moneyEarned;
	
	private BigDecimal moneyPaid;
	
	private Integer totalPosts;
	
	private Integer warningsSent;

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
}
