package com.drop.service;

import java.util.List;

import com.drop.dao.domain.DealPost;
import com.drop.dao.domain.DealWanted;
import com.drop.dto.DealPostDTO;
import com.drop.dto.DealWantedDTO;
import com.drop.enums.SORT_TYPE;

public interface ISolrSearchService {

	public void add(DealPost dealPost);

	public void add(DealWanted dealWanted);

	public List<DealPost> search(DealWanted dealWanted, int pageNumber);

	public List<DealWantedDTO> searchWanted(String dealWantedString,
			int pageNumber, SORT_TYPE sortType, String categoryName);

	public List<DealPostDTO> searchDrops(String dealPostString, int pageNumber,
			SORT_TYPE sortType, String categoryName);

	public void delete(long id, boolean isDrop);
}
