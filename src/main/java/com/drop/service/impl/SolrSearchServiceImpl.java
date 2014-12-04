package com.drop.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

	@Autowired
	@Qualifier("applicationConfig")
	private Properties applicationConfig;

	public void add(DealPost dealPost) {
		if (new Boolean(applicationConfig.getProperty("skipSolr"))) {
			return;
		}

		SolrInputDocument document = new SolrInputDocument();
		document.addField("id", dealPost.getId());
		document.addField("title", dealPost.getTitle());
		document.addField("retailPrice", dealPost.getRetailPrice().toString());
		document.addField("onlineDeal", dealPost.getOnlineDeal());
		document.addField("dealCategory", dealPost.getDealCategory().getName());
		document.addField("salePrice", dealPost.getSalePrice());
		document.addField("localDeal", dealPost.getLocalDeal());
		try {
			UpdateResponse response = solrServer.add(document);
			solrServer.commit();

		} catch (SolrServerException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<DealPost> search(DealWanted dealWanted) {
		if (new Boolean(applicationConfig.getProperty("skipSolr"))) {
			return null;
		}

		SolrQuery parameters = new SolrQuery();
		parameters.set("q", "salePrice:" + dealWanted.getMaxPrice().toString()
				+ ", onlineDeal:" + dealWanted.getWouldBuyOnline()
				+ ", localDeal:" + dealWanted.getWouldBuyLocally()
				+ ", dealCategory:" + dealWanted.getDealCategory().getName()
				+ ", title:" + dealWanted.getTitle());
		parameters.setRequestHandler("/select");
		try {
			QueryResponse response = solrServer.query(parameters);
			SolrDocumentList solrDocumentList = response.getResults();
			System.out.println("SolrDocument" + solrDocumentList.size());
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
