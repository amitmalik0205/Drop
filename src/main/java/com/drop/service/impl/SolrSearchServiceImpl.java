package com.drop.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
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

import com.drop.dao.IDealCategoryDao;
import com.drop.dao.domain.DealMatch;
import com.drop.dao.domain.DealPost;
import com.drop.dao.domain.DealWanted;
import com.drop.dto.DealPostDTO;
import com.drop.dto.DealWantedDTO;
import com.drop.enums.DEAL_MATCH_STATUS;
import com.drop.enums.SORT_TYPE;
import com.drop.service.IDealCategoryService;
import com.drop.service.IDealMatchService;
import com.drop.service.IDealPostService;
import com.drop.service.ISolrSearchService;
import com.drop.util.DropConstants;
import com.drop.util.DropUtil;

@Service
public class SolrSearchServiceImpl implements ISolrSearchService {

	SolrServer solrServer;

	@Autowired
	private IDealPostService dealPostService;

	@Autowired
	private IDealMatchService dealMatchService;
	
	@Autowired
	private IDealCategoryService dealCategoryService;

	public SolrSearchServiceImpl() {
		solrServer = new HttpSolrServer(DropConstants.SOLR_SEARCH_URL);
	}

	@Autowired
	@Qualifier("applicationConfig")
	private Properties applicationConfig;

	@Autowired
	@Qualifier("msgConfig")
	private Properties msgConfig;

	public void add(DealPost dealPost) {
		if (new Boolean(applicationConfig.getProperty("skipSolr"))) {
			return;
		}

		SolrInputDocument document = new SolrInputDocument();
		document.addField("id", dealPost.getId());
		document.addField("title", dealPost.getTitle());
		document.addField("retailPrice", dealPost.getRetailPrice().longValue());
		document.addField("onlineDeal", dealPost.getOnlineDeal());
		document.addField("dealCategory", dealPost.getDealCategory().getName());
		document.addField("salePrice", dealPost.getSalePrice().longValue());
		document.addField("localDeal", dealPost.getLocalDeal());
		document.addField("dealExpiry", dealPost.getExpires());
		document.addField("created", dealPost.getCreatedOn());
		document.addField("dealStart", dealPost.getStarts());
		document.addField("description", dealPost.getDescription());
		document.addField("isDrop", true);

		try {
			UpdateResponse response = solrServer.add(document);
			solrServer.commit();

		} catch (SolrServerException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void edit(DealPost dealPost) {
		delete(dealPost.getId(), true);
		add(dealPost);
	}

	public void edit(DealWanted dealWanted) {
		delete(dealWanted.getId(), false);
		add(dealWanted);
	}

	public void add(DealWanted dealWanted) {
		if (new Boolean(applicationConfig.getProperty("skipSolr"))) {
			return;
		}

		SolrInputDocument document = new SolrInputDocument();
		document.addField("id", dealWanted.getId());
		document.addField("title", dealWanted.getTitle());
		document.addField("retailPrice", dealWanted.getMaxPrice().longValue());
		document.addField("onlineDeal", dealWanted.getWouldBuyOnline());
		document.addField("dealCategory", dealWanted.getDealCategory()
				.getName());
		document.addField("isDrop", false);
		document.addField("salePrice", dealWanted.getMaxPrice().longValue());
		document.addField("localDeal", dealWanted.getWouldBuyLocally());
		document.addField("created", dealWanted.getCreatedOn());
		document.addField("description", dealWanted.getDescription());
		if(null != dealWanted.getTipAmount()) {
		document.addField("tipAmount", dealWanted.getTipAmount().longValue());
		} else {
			document.addField("tipAmount", 0);			
		}
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

		long price = dealWanted.getMaxPrice().longValue();

		StringBuilder query = new StringBuilder();
		query.append("salePrice:[ * TO " + price + " ] AND title:*"
				+ dealWanted.getTitle() + "*");
		if (dealWanted.getWouldBuyOnline() && dealWanted.getWouldBuyLocally()) {
			query.append(" AND (onlineDeal:" + dealWanted.getWouldBuyOnline()
					+ " OR localDeal:" + dealWanted.getWouldBuyLocally() + ")");
		} else if (dealWanted.getWouldBuyOnline()) {
			query.append(" AND onlineDeal:" + dealWanted.getWouldBuyOnline());
		} else if (dealWanted.getWouldBuyLocally()) {
			query.append(" AND localDeal:" + dealWanted.getWouldBuyLocally());
		}

		query.append(" AND dealCategory:"
				+ dealWanted.getDealCategory().getName());

		query.append(" AND dealStart: [* TO NOW]");
		query.append(" AND dealExpiry: [NOW TO *]");
		query.append(" AND isDrop:true");

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
		if (null != dealWantedString && !dealWantedString.equalsIgnoreCase("")) {
			query.append("title:*" + dealWantedString + "*");
		} else {
			query.append("title:*");
		}
		query.append(" AND isDrop:" + false);
		if (null != categoryName && !"All".equals(categoryName)) {
			query.append(" AND dealCategory:" + categoryName);
		}

		SolrQuery parameters = new SolrQuery(query.toString());
		parameters
				.setStart(pageNumber
						* new Integer(msgConfig
								.getProperty("search.results.per.page")));
		parameters.setRows(new Integer(msgConfig
				.getProperty("search.results.per.page")));
		switch (sortType) {

		case PRICE:
			parameters.setSort("salePrice", ORDER.desc);
			break;

		case TITLE:
			parameters.setSort("title", ORDER.asc);
			break;

		case DATE:
			parameters.setSort("created", ORDER.desc);
			break;

		default:
			break;

		}

		try {

			QueryResponse response = solrServer.query(parameters);
			SolrDocumentList solrDocumentList = response.getResults();

			for (SolrDocument solrDocument : solrDocumentList) {
				DealWantedDTO dealWantedDTO = new DealWantedDTO();
				dealWantedDTO.setCreatedOn((Date) solrDocument
						.getFieldValue("created"));
				dealWantedDTO.setDealCategory((String) solrDocument
						.getFieldValue("dealCategory"));
				dealWantedDTO.setImageName(dealCategoryService
						.getCategoryImageName(dealWantedDTO.getDealCategory()));
				String description = (String) solrDocument
						.getFieldValue("description");
				if (description.length() > 25) {
					dealWantedDTO.setDescription(description.substring(0, 24)
							+ "...");

				} else {
					dealWantedDTO.setDescription(description);

				}
				dealWantedDTO.setId(new Long((String) solrDocument
						.getFieldValue("id")));
				dealWantedDTO.setMaxPrice(new BigDecimal((Integer) solrDocument
						.getFieldValue("retailPrice")));
				String title = (String) solrDocument.getFieldValue("title");
				if (title.length() > 25) {
					dealWantedDTO.setTitle(title.substring(0, 24) + "...");

				} else {
					dealWantedDTO.setTitle(title);

				}
				dealWantedList.add(dealWantedDTO);
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
		if (null != dealPostString && !dealPostString.equalsIgnoreCase("")) {
			query.append("title:*" + dealPostString + "*");
		} else {
			query.append("title:*");
		}
		query.append(" AND isDrop:" + true);
		if (null != categoryName && !"All".equals(categoryName)) {
			query.append(" AND dealCategory:" + categoryName);
		}
		SolrQuery parameters = new SolrQuery(query.toString());
		parameters
				.setStart(pageNumber
						* new Integer(msgConfig
								.getProperty("search.results.per.page")));
		switch (sortType) {

		case PRICE:
			parameters.setSort("salePrice", ORDER.asc);
			break;

		case TITLE:
			parameters.setSort("title", ORDER.asc);
			break;

		case DATE:
			parameters.setSort("created", ORDER.desc);
			break;

		default:
			break;

		}
		parameters.setRows(new Integer(msgConfig
				.getProperty("search.results.per.page")));

		try {

			QueryResponse response = solrServer.query(parameters);
			SolrDocumentList solrDocumentList = response.getResults();

			for (SolrDocument solrDocument : solrDocumentList) {
				DealPostDTO dealPostDTO = new DealPostDTO();
				dealPostDTO.setCreatedOn((Date) solrDocument
						.getFieldValue("created"));
				dealPostDTO.setDealCategory((String) solrDocument
						.getFieldValue("dealCategory"));
				dealPostDTO.setImageName(dealCategoryService
						.getCategoryImageName(dealPostDTO.getDealCategory()));

				String description = (String) solrDocument
						.getFieldValue("description");
				if (description.length() > 25) {
					dealPostDTO.setDescription(description.substring(0, 24)
							+ "...");

				} else {
					dealPostDTO.setDescription(description);

				}
				dealPostDTO.setId(new Long((String) solrDocument
						.getFieldValue("id")));
				dealPostDTO.setRetailPrice(new BigDecimal(
						(Integer) solrDocument.getFieldValue("retailPrice")));
				dealPostDTO.setSalePrice(new BigDecimal((Integer) solrDocument
						.getFieldValue("salePrice")));
				String title = (String) solrDocument.getFieldValue("title");
				if (title.length() > 25) {
					dealPostDTO.setTitle(title.substring(0, 24) + "...");

				} else {
					dealPostDTO.setTitle(title);

				}
				dealPostDTO.setStarts((Date) solrDocument
						.getFieldValue("dealStart"));
				dealPostDTO.setExpires((Date) solrDocument
						.getFieldValue("dealExpiry"));
				dealPostDTO.setDiscountPercent(DropUtil.calculateDiscount(
						dealPostDTO.getSalePrice(),
						dealPostDTO.getRetailPrice()));
				dealPostList.add(dealPostDTO);

			}

			System.out.println("SolrDocument" + solrDocumentList.size());
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dealPostList;

	}

	public void delete(long id, boolean isDrop) {

		StringBuilder query = new StringBuilder();
		query.append("id:" + id);

		query.append(" AND isDrop:" + isDrop);
		try {

			solrServer.deleteByQuery(query.toString());
			solrServer.commit();
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<DealPostDTO> getDropsForHomePage() {

		List<DealPostDTO> dealPostList = new ArrayList<>();

		StringBuilder query = new StringBuilder();

		query.append("isDrop:" + true);

		SolrQuery parameters = new SolrQuery(query.toString());

		parameters.setRows(50);

		try {

			QueryResponse response = solrServer.query(parameters);
			SolrDocumentList solrDocumentList = response.getResults();

			for (SolrDocument solrDocument : solrDocumentList) {
				DealPostDTO dealPostDTO = new DealPostDTO();
				dealPostDTO.setCreatedOn((Date) solrDocument
						.getFieldValue("created"));
				dealPostDTO.setDealCategory((String) solrDocument
						.getFieldValue("dealCategory"));
				dealPostDTO.setImageName(dealCategoryService
						.getCategoryImageName(dealPostDTO.getDealCategory()));
				String description = (String) solrDocument
						.getFieldValue("description");
				if (description.length() > 25) {
					dealPostDTO.setDescription(description.substring(0, 24)
							+ "...");

				} else {
					dealPostDTO.setDescription(description);

				}
				dealPostDTO.setId(new Long((String) solrDocument
						.getFieldValue("id")));
				dealPostDTO.setRetailPrice(new BigDecimal(
						(Integer) solrDocument.getFieldValue("retailPrice")));
				dealPostDTO.setSalePrice(new BigDecimal((Integer) solrDocument
						.getFieldValue("salePrice")));
				String title = (String) solrDocument.getFieldValue("title");
				if (title.length() > 25) {
					dealPostDTO.setTitle(title.substring(0, 24) + "...");

				} else {
					dealPostDTO.setTitle(title);

				}
				dealPostDTO.setStarts((Date) solrDocument
						.getFieldValue("dealStart"));
				dealPostDTO.setExpires((Date) solrDocument
						.getFieldValue("dealExpiry"));
				dealPostDTO.setDiscountPercent(DropUtil.calculateDiscount(
						dealPostDTO.getSalePrice(),
						dealPostDTO.getRetailPrice()));
				dealPostList.add(dealPostDTO);

			}

			System.out.println("SolrDocument" + solrDocumentList.size());
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dealPostList;

	}

	public List<DealWantedDTO> getDropsWantedForHome() {

		List<DealWantedDTO> dealWantedList = new ArrayList<>();

		StringBuilder query = new StringBuilder();

		query.append("isDrop:" + false);

		SolrQuery parameters = new SolrQuery(query.toString());

		parameters.setRows(50);

		parameters.setSort("tipAmount", ORDER.desc);

		try {

			QueryResponse response = solrServer.query(parameters);
			SolrDocumentList solrDocumentList = response.getResults();

			for (SolrDocument solrDocument : solrDocumentList) {
				DealWantedDTO dealWantedDTO = new DealWantedDTO();
				dealWantedDTO.setCreatedOn((Date) solrDocument
						.getFieldValue("created"));
				dealWantedDTO.setDealCategory((String) solrDocument
						.getFieldValue("dealCategory"));
				dealWantedDTO.setImageName(dealCategoryService
						.getCategoryImageName(dealWantedDTO.getDealCategory()));
				String description = (String) solrDocument
						.getFieldValue("description");
				if (description.length() > 25) {
					dealWantedDTO.setDescription(description.substring(0, 24)
							+ "...");

				} else {
					dealWantedDTO.setDescription(description);

				}
				dealWantedDTO.setId(new Long((String) solrDocument
						.getFieldValue("id")));
				dealWantedDTO.setMaxPrice(new BigDecimal((Integer) solrDocument
						.getFieldValue("retailPrice")));
				String title = (String) solrDocument.getFieldValue("title");
				if (title.length() > 25) {
					dealWantedDTO.setTitle(title.substring(0, 24) + "...");

				} else {
					dealWantedDTO.setTitle(title);

				}
				if (null != solrDocument.getFieldValue("tipAmount")) {
					dealWantedDTO.setTipAmount(new BigDecimal(
							(Integer) solrDocument.getFieldValue("tipAmount")));
				}
				dealWantedList.add(dealWantedDTO);

			}

			System.out.println("SolrDocument" + solrDocumentList.size());
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dealWantedList;

	}

}
