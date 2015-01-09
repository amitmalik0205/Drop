package com.drop.controller.form;

import java.io.Serializable;

import com.drop.enums.SORT_TYPE;

public class SearchDealForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private String searchString;
	
	private String searchType;
	
	private long selectedCategory;
	
	private SORT_TYPE sortType;
	
	private int currentPage;
	
	private int itemsOnpage;
	
	private int maxResultOnPage;

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

	public long getSelectedCategory() {
		return selectedCategory;
	}

	public void setSelectedCategory(long selectedCategory) {
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

	public int getItemsOnpage() {
		return itemsOnpage;
	}

	public void setItemsOnpage(int itemsOnpage) {
		this.itemsOnpage = itemsOnpage;
	}

	public int getMaxResultOnPage() {
		return maxResultOnPage;
	}

	public void setMaxResultOnPage(int maxResultOnPage) {
		this.maxResultOnPage = maxResultOnPage;
	}	
	
}
