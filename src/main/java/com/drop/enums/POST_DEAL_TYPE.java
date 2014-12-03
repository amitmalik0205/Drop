package com.drop.enums;

public enum POST_DEAL_TYPE {

	LOCAL_DEAL("localDeal"), ONLINE_DEAL("onlineDeal");

	private final String dealType;

	private POST_DEAL_TYPE(String dealType) {
		this.dealType = dealType;
	}

	public String getDealType() {
		return dealType;
	}
}
