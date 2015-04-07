package com.drop.rest.response.dto;

import java.io.Serializable;
import java.util.List;

import com.drop.dto.DealPostDTO;
import com.drop.dto.DealWantedDTO;

public class HomePageDropsDTO implements Serializable {

	private static final long serialVersionUID = -1818139548055193609L;	
	

	private List<DealWantedDTO> homePageWantDrops;
	
	private List<DealPostDTO> homePageDrops;
	
	private List<DealPostDTO> homePageExpireSoonDrops;
	

	public List<DealWantedDTO> getHomePageWantDrops() {
		return homePageWantDrops;
	}

	public void setHomePageWantDrops(List<DealWantedDTO> homePageWantDrops) {
		this.homePageWantDrops = homePageWantDrops;
	}

	public List<DealPostDTO> getHomePageDrops() {
		return homePageDrops;
	}

	public void setHomePageDrops(List<DealPostDTO> homePageDrops) {
		this.homePageDrops = homePageDrops;
	}

	public List<DealPostDTO> getHomePageExpireSoonDrops() {
		return homePageExpireSoonDrops;
	}

	public void setHomePageExpireSoonDrops(List<DealPostDTO> homePageExpireSoonDrops) {
		this.homePageExpireSoonDrops = homePageExpireSoonDrops;
	}
	
}
