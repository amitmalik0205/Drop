package com.drop.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.stereotype.Service;

import com.drop.dao.domain.DealPost;
import com.drop.dao.domain.DealWanted;
import com.drop.service.ISolrSearchService;
import com.drop.util.DropConstants;

@Service
public class SolrSearchServiceImpl implements ISolrSearchService {

	SolrServer solrServer;

	public SolrSearchServiceImpl() {
		solrServer = new HttpSolrServer(DropConstants.SOLR_SEARCH_URL);
	}

	public void add(DealPost dealPost) {
		SolrInputDocument document = new SolrInputDocument();
		document.addField("id", dealPost.getId());
		document.addField("retailPrice", dealPost.getRetailPrice());
		document.addField("price", "49.99");
		try {
			UpdateResponse response = solrServer.add(document);
			solrServer.commit();

		} catch (SolrServerException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	public List<DealPost> search(DealWanted dealWanted) {

		return null;
	}

}
