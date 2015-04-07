package com.drop.rest.exception.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrServerException;

import com.drop.rest.services.DropServiceResponse;
import com.drop.util.DropUtil;
import com.drop.util.PropertiesFileReaderUtil;

@Provider
public class SolrServerExceptionMapper implements ExceptionMapper<SolrServerException> {

	private static final Logger logger = Logger
			.getLogger(SolrServerExceptionMapper.class);

	@Override
	public Response toResponse(SolrServerException ex) {
		ex.printStackTrace();		
		logger.fatal(DropUtil.getExceptionDescriptionString(ex));
		DropServiceResponse response = new DropServiceResponse();
		response.setCode("solerServerException");
		response.setMessage(PropertiesFileReaderUtil
				.getPropertyValue("solerServerException"));
		return Response.ok(response).build();
	}
}
