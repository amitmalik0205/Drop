package com.drop.service;

import java.util.List;

import com.drop.dao.domain.DealPost;
import com.drop.dao.domain.DealWanted;

public interface ISolrSearchService {

	public void add(DealPost dealPost);

	public List<DealPost> search(DealWanted dealWanted);

}
