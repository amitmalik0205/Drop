package com.drop.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.drop.dao.domain.DealPost;
import com.drop.dao.domain.DealWanted;
import com.drop.service.IDealPostService;
import com.drop.service.ISolrSearchService;
import com.drop.util.DropConstants;

@Service
public class SolrSearchServiceImpl implements ISolrSearchService {

	SolrServer solrServer;

	@Autowired
	private IDealPostService dealPostService;

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

		List<DealPost> postsList = new ArrayList<>();
		SolrQuery parameters = new SolrQuery("salePrice:"
				+ dealWanted.getMaxPrice().toString() + " onlineDeal:"
				+ dealWanted.getWouldBuyOnline() + " localDeal:"
				+ dealWanted.getWouldBuyLocally() + " dealCategory:"
				+ dealWanted.getDealCategory().getName() + " title:"
				+ dealWanted.getTitle());
		parameters.setRequestHandler("/select");
		try {
			QueryResponse response = solrServer.query(parameters);
			SolrDocumentList solrDocumentList = response.getResults();
			for (SolrDocument solrDocument : solrDocumentList) {
				DealPost dealPost = dealPostService
						.getDealPostbyId(new Long((String)solrDocument.getFieldValue("id")));
				if(dealPost != null) {
					postsList.add(dealPost);
				}				
			}
			System.out.println("SolrDocument" + solrDocumentList.size());
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return postsList;
	}
}
