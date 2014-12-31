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

import com.drop.dao.domain.DealMatch;
import com.drop.dao.domain.DealPost;
import com.drop.dao.domain.DealWanted;
import com.drop.dto.DealPostDTO;
import com.drop.dto.DealWantedDTO;
import com.drop.enums.DEAL_MATCH_STATUS;
import com.drop.enums.SORT_TYPE;
import com.drop.service.IDealMatchService;
import com.drop.service.IDealPostService;
import com.drop.service.ISolrSearchService;
import com.drop.util.DropConstants;

@Service
public class SolrSearchServiceImpl implements ISolrSearchService {

	SolrServer solrServer;

	@Autowired
	private IDealPostService dealPostService;

	@Autowired
	private IDealMatchService dealMatchService;

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
		document.addField("dealExpiry", dealPost.getExpires());
		document.addField("created", dealPost.getCreatedOn());
		document.addField("isDrop", true);

		try {
			UpdateResponse response = solrServer.add(document);
			solrServer.commit();

		} catch (SolrServerException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void add(DealWanted dealWanted) {
		if (new Boolean(applicationConfig.getProperty("skipSolr"))) {
			return;
		}

		SolrInputDocument document = new SolrInputDocument();
		document.addField("id", dealWanted.getId());
		document.addField("title", dealWanted.getTitle());
		document.addField("retailPrice", dealWanted.getMaxPrice());
		document.addField("onlineDeal", dealWanted.getWouldBuyOnline());
		document.addField("dealCategory", dealWanted.getDealCategory()
				.getName());
		document.addField("isDrop", false);
		document.addField("salePrice", dealWanted.getMaxPrice());
		document.addField("localDeal", dealWanted.getWouldBuyLocally());
		document.addField("created", dealWanted.getCreatedOn());
		try {
			UpdateResponse response = solrServer.add(document);
			solrServer.commit();

		} catch (SolrServerException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<DealPost> search(DealWanted dealWanted, int pageNumber) {
		if (new Boolean(applicationConfig.getProperty("skipSolr"))) {
			return null;
		}

		List<DealPost> postsList = new ArrayList<>();
		List<DealPost> postsWithMatchesList = new ArrayList<>();

		StringBuilder query = new StringBuilder();
		query.append("salePrice:[ * TO " + dealWanted.getMaxPrice().toString()
				+ " ] AND title: * " + dealWanted.getTitle() + " *");
		if (dealWanted.getWouldBuyOnline() && dealWanted.getWouldBuyLocally()) {
			query.append(" AND (onlineDeal:" + dealWanted.getWouldBuyOnline()
					+ " OR localDeal:" + dealWanted.getWouldBuyLocally() + ")");
		} else if (dealWanted.getWouldBuyOnline()) {
			query.append(" AND onlineDeal:" + dealWanted.getWouldBuyOnline());
		} else if (dealWanted.getWouldBuyLocally()) {
			query.append(" AND localDeal:" + dealWanted.getWouldBuyLocally());
		}
		/*
		 * query.append(" AND dealCategory:" +
		 * dealWanted.getDealCategory().getName());
		 */
		query.append(" AND dealExpiry: [NOW TO *]");

		SolrQuery parameters = new SolrQuery(query.toString());
		parameters.setStart(pageNumber * 10);
		try {

			QueryResponse response = solrServer.query(parameters);
			SolrDocumentList solrDocumentList = response.getResults();

			for (SolrDocument solrDocument : solrDocumentList) {

				DealPost dealPost = dealPostService.getDealPostbyId(new Long(
						(String) solrDocument.getFieldValue("id")));

				// Find a Deal Match for Deal Post
				if (dealPost != null) {
					DealMatch dealMatch = dealMatchService
							.getDealMatchByDealWantedAndDealPost(
									dealWanted.getId(), dealPost.getId());

					if (dealMatch != null) {

						if (dealMatch.getStatus() == DEAL_MATCH_STATUS.ACCEPTED) {
							dealPost.setDealMatch(dealMatch);
							postsWithMatchesList.add(dealPost);
						}

						continue;
					}
					postsList.add(dealPost);
				}
			}

			postsWithMatchesList.addAll(postsList);

			System.out.println("SolrDocument" + solrDocumentList.size());
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return postsWithMatchesList;
	}

	public List<DealWantedDTO> searchWanted(String dealWantedString,
			int pageNumber, SORT_TYPE sortType, String categoryName) {

		List<DealWantedDTO> dealWantedList = new ArrayList<>();

		StringBuilder query = new StringBuilder();
		query.append("title: * " + dealWantedString + " *");
		query.append("AND isDrop:false");
		if (null != categoryName) {
			query.append(" AND dealCategory:" + categoryName);
		}

		SolrQuery parameters = new SolrQuery(query.toString());
		parameters.setStart(pageNumber * 18);
		try {

			QueryResponse response = solrServer.query(parameters);
			SolrDocumentList solrDocumentList = response.getResults();

			for (SolrDocument solrDocument : solrDocumentList) {

			}

			System.out.println("SolrDocument" + solrDocumentList.size());
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dealWantedList;

	}

	public List<DealPostDTO> searchDrops(String dealPostString, int pageNumber,
			SORT_TYPE sortType, String categoryName) {

		List<DealPostDTO> dealPostList = new ArrayList<>();

		StringBuilder query = new StringBuilder();
		query.append("title: * " + dealPostString + " *");
		query.append("AND isDrop:true");
		if (null != categoryName) {
			query.append(" AND dealCategory:" + categoryName);
		}

		SolrQuery parameters = new SolrQuery(query.toString());
		parameters.setStart(pageNumber * 18);
		try {

			QueryResponse response = solrServer.query(parameters);
			SolrDocumentList solrDocumentList = response.getResults();

			for (SolrDocument solrDocument : solrDocumentList) {

			}

			System.out.println("SolrDocument" + solrDocumentList.size());
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dealPostList;

	}

}
