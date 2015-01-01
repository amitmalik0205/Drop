package com.drop.controller.form;

import java.io.Serializable;

import com.drop.enums.SORT_TYPE;

public class SearchDealForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private String searchString;
	
	private String searchType;
	
	private String selectedCategory;
	
	private SORT_TYPE sortType;
	
	private int currentPage;
	
	private int maxItemsOnpage;

	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSelectedCategory() {
		return selectedCategory;
	}

	public void setSelectedCategory(String selectedCategory) {
		this.selectedCategory = selectedCategory;
	}

	public SORT_TYPE getSortType() {
		return sortType;
	}

	public void setSortType(SORT_TYPE sortType) {
		this.sortType = sortType;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getMaxItemsOnpage() {
		return maxItemsOnpage;
	}

	public void setMaxItemsOnpage(int maxItemsOnpage) {
		this.maxItemsOnpage = maxItemsOnpage;
	}
}