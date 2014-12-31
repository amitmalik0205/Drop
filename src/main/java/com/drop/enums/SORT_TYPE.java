package com.drop.enums;

public enum SORT_TYPE {

	PRICE("PRICE"), TITLE("TITLE") , DATE("DATE"), ;

	private final String sortType;

	private SORT_TYPE(String sortType) {
		this.sortType = sortType;
	}

	public String getSortType() {
		return sortType;
	}
}
