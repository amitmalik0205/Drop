package com.drop.rest.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.drop.dao.domain.DealCategory;
import com.drop.dao.domain.DealPost;
import com.drop.dao.domain.DealWanted;
import com.drop.dao.domain.MailingAddress;
import com.drop.dao.domain.User;
import com.drop.rest.request.dto.GetDealCategoriesDTO;
import com.drop.rest.request.dto.LoginDTO;
import com.drop.service.IDealCategoryService;
import com.drop.service.IUserService;
import com.drop.util.DropUtil;



@Service
@Path("drop-service")
public class DropRestService {
	
	private static final Logger logger = Logger.getLogger(DropRestService.class);
	
	@Autowired
	@Qualifier("msgConfig")
	private Properties msgConfig;
		
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IDealCategoryService categoryService;
	
	
	@Path("test")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String text() throws IOException {
		return "Its working";
	}
	
	
	@POST
	@Path("login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(rollbackFor = Exception.class)
	public Response login(LoginDTO loginDto) {
		
		DropServiceResponse response = new DropServiceResponse();
		response.setCode("login001");
		response.setMessage(msgConfig.getProperty("login001"));
				
		try {
			
			userService.login(loginDto);

		} catch (Exception e) {
			e.printStackTrace();
			response.setCode("login002");
			response.setMessage(msgConfig.getProperty("login002"));
			logger.fatal(DropUtil.getExceptionDescriptionString(e));
			throw new WebApplicationException(Response.ok(response).build());
		}
		
		return Response.ok(response).build();
	}
	
	
	@GET
	@Path("get-profile")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response getProfile(@QueryParam("email") String email) {
		
		DropServiceResponse response = new DropServiceResponse();
		
		try {

			User savedUser = userService.getUserByEmail(email);
			
			if(savedUser == null) {
				
				response.setCode("getProfile002");
				response.setMessage(msgConfig.getProperty("getProfile002"));
				
			} else {
				
				LoginDTO loginDTO = new LoginDTO();
				loginDTO.setUserId(savedUser.getUserId());
				loginDTO.setFirstName(savedUser.getFirstName());
				loginDTO.setLastName(savedUser.getLastName());
				loginDTO.setEmail(savedUser.getEmail());
				loginDTO.setPhoneNumber(savedUser.getPhoneNumber());
				
				Set<MailingAddress> addressList = savedUser.getAddresses();
				
				if(addressList.size() > 0) {
					
					for(MailingAddress address : addressList) {
						loginDTO.setAddressLine1(address.getAddressLine1());
						loginDTO.setAddressLine2(address.getAddressLine2());
						loginDTO.setCity(address.getCity());
						loginDTO.setState(address.getState());
						loginDTO.setZip(address.getZip());
						break;
					}
				}
				
				return Response.ok(loginDTO).build();
			}			

		} catch (Exception e) {
			e.printStackTrace();
			response.setCode("getProfile001");
			response.setMessage(msgConfig.getProperty("getProfile001"));
			logger.fatal(DropUtil.getExceptionDescriptionString(e));
			throw new WebApplicationException(Response.ok(response).build());
		}
		
		return Response.ok(response).build();
	}
	
	
	@POST
	@Path("update-profile")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(rollbackFor = Exception.class)
	public Response updateProfile(LoginDTO loginDto) {
		
		DropServiceResponse response = new DropServiceResponse();
		response.setCode("updateProfile002");
		response.setMessage(msgConfig.getProperty("updateProfile002"));
				
		try {
			
			userService.updateUserProfile(loginDto);

		} catch (Exception e) {
			e.printStackTrace();
			response.setCode("updateProfile001");
			response.setMessage(msgConfig.getProperty("updateProfile001"));
			logger.fatal(DropUtil.getExceptionDescriptionString(e));
			throw new WebApplicationException(Response.ok(response).build());
		}
		
		return Response.ok(response).build();
	}
	
	
	@GET
	@Path("get-deal-categories")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response getDealCategories() {
		
		DropServiceResponse response = new DropServiceResponse();
		
		List<GetDealCategoriesDTO> categorylist = new ArrayList<GetDealCategoriesDTO>();
		
		try {
			
			categorylist = categoryService.getAllDealCategoriesDto();
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode("getDealCategories001");
			response.setMessage(msgConfig.getProperty("getDealCategories001"));
			logger.fatal(DropUtil.getExceptionDescriptionString(e));
			throw new WebApplicationException(Response.ok(response).build());
		}
		
		return Response.ok(categorylist).build();
	}
	
	
	@GET
	@Path("get-home-page-drops")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response getHomeDropsTest() {
		
		DropServiceResponse response = new DropServiceResponse();
		
		//Object object[] = new Object[3];
		
		List<Object> list = new ArrayList<Object>();
		
		List<DealWanted> dropWantedList = new ArrayList<DealWanted>();
		List<DealPost> dropPostList = new ArrayList<DealPost>();
		
		list.add(dropWantedList);
		list.add(dropPostList);
		
		try {

			

		} catch (Exception e) {
			e.printStackTrace();
			response.setCode("getSecurityQuestion001");
			response.setMessage(msgConfig.getProperty("getSecurityQuestion001"));
			logger.fatal(DropUtil.getExceptionDescriptionString(e));
			throw new WebApplicationException(Response.ok(response).build());
		}
		return Response.ok(list).build();
	}

}
