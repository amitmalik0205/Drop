package com.drop.rest.services;

import java.io.IOException;
import java.io.InputStream;
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
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.drop.dao.domain.MailingAddress;
import com.drop.dao.domain.User;
import com.drop.dto.DealPostDTO;
import com.drop.dto.DealWantedDTO;
import com.drop.enums.SORT_TYPE;
import com.drop.rest.request.dto.GetDealCategoriesDTO;
import com.drop.rest.request.dto.LoginDTO;
import com.drop.rest.request.dto.PostDropDTO;
import com.drop.rest.request.dto.PostWantDropDTO;
import com.drop.rest.request.dto.UpdateDropDTO;
import com.drop.rest.request.dto.UpdateWantDropDTO;
import com.drop.rest.response.dto.GetMyDropsDTO;
import com.drop.rest.response.dto.GetMyWantDropsDTO;
import com.drop.rest.response.dto.HomePageDropsDTO;
import com.drop.service.IDealCategoryService;
import com.drop.service.IDealPostService;
import com.drop.service.IDealWantedService;
import com.drop.service.ISolrSearchService;
import com.drop.service.IUserService;
import com.drop.util.DropUtil;
import com.drop.util.PropertiesFileReaderUtil;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
@Path("drop-service")
public class DropRestService {
	
	private static final Logger logger = Logger.getLogger(DropRestService.class);
	
	@Autowired
	@Qualifier("applicationConfig")
	private Properties applicationConfig;
	
	@Autowired
	@Qualifier("msgConfig")
	private Properties msgConfig;
		
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IDealCategoryService categoryService;	
	
	@Autowired
	private IDealWantedService dealWantedService;
	
	@Autowired
	private IDealPostService dealPostService;
	
	@Autowired
	private ISolrSearchService solrSearchService;
	
	
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
	public Response getHomeDrops() {
		
		DropServiceResponse response = new DropServiceResponse();
		
		HomePageDropsDTO dto = new HomePageDropsDTO();					
		
		try {
						
			List<DealWantedDTO> dropWantedList = solrSearchService.getDropsWantedForHome();
			
			if(dropWantedList.size() >= 3 ) {
				
				List<DealWantedDTO> dropWantedSubList = dropWantedList.subList(0, 3);							
				
				dto.setHomePageWantDrops(dropWantedSubList);
				
			} else {
				
				dto.setHomePageWantDrops(dropWantedList);
			}
			
						
			List<DealPostDTO> dropPostList = solrSearchService.getDropsForHomePage();
			
			if (dropPostList.size() >= 3) {

				List<DealPostDTO> dropPostSubList = dropPostList.subList(0, 3);
				
				dto.setHomePageDrops(dropPostSubList);

			} else {

				dto.setHomePageDrops(dropPostList);
			}
			
			
			List<DealPostDTO> expireSoonDropList = solrSearchService.getDropsForHomePage();
			
			if (expireSoonDropList.size() >= 3) {

				List<DealPostDTO> expireSoonDropSubList = expireSoonDropList.subList(0, 3);
				
				dto.setHomePageExpireSoonDrops(expireSoonDropSubList);

			} else {

				dto.setHomePageExpireSoonDrops(expireSoonDropList);
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.setCode("getHomeDrops001");
			response.setMessage(msgConfig.getProperty("getHomeDrops001"));
			logger.fatal(DropUtil.getExceptionDescriptionString(e));
			throw new WebApplicationException(Response.ok(response).build());
		}
		return Response.ok(dto).build();
	}
	
	
	@POST
	@Path("post-want-drop")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional(rollbackFor = Exception.class)
	public Response postWantDrop(PostWantDropDTO dealWantedDTO) {
		
		DropServiceResponse response = new DropServiceResponse();
		response.setCode("postWantDrop002");
		response.setMessage(msgConfig.getProperty("postWantDrop002"));
		
		try {
			
			dealWantedService.saveDealWanted(dealWantedDTO);

		} catch (Exception e) {
			e.printStackTrace();
			response.setCode("postWantDrop001");
			response.setMessage(msgConfig.getProperty("postWantDrop001"));
			logger.fatal(DropUtil.getExceptionDescriptionString(e));
			throw new WebApplicationException(Response.ok(response).build());
		}
		
		return Response.ok(response).build();
	}

	
	@POST
	@Path("post-drop")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(rollbackFor = Exception.class)
	public Response postDrop(FormDataMultiPart multiPart) {	
		
		DropServiceResponse response = new DropServiceResponse();
		
		boolean isError = false;
		String imageName = null;		

		String publicURL = PropertiesFileReaderUtil.getApplicationProperty("deal.image.public.url");
		String rootFolderPath = PropertiesFileReaderUtil.getApplicationProperty("drop.image.storage.path");
		
		
		//Get entity which contains Drop details
		List<FormDataBodyPart> dropPart = multiPart.getFields("dropDetails"); 
		PostDropDTO dropObj = null;
		
		try {

			//Convert deal JSON to object
			ObjectMapper mapper = new ObjectMapper();
			try {
				dropObj = mapper.readValue(dropPart.get(0).getValueAs(String.class), PostDropDTO.class);
			} catch (Exception e1) {
				e1.printStackTrace();
				isError = true;
				response.setMessage(msgConfig.getProperty("postDrop003"));
				response.setCode("postDrop003");
			} 
			
			if(!isError) {
				
				// Store main image as InputStream
				List<FormDataBodyPart> imageList = multiPart.getFields("dropImage");  
				FormDataBodyPart dropImage = imageList.get(0);
				InputStream inputStream = dropImage.getValueAs(InputStream.class);			
				
			    //Upload image
			    String randomFolderName = "drop_images"; //DropUtil.getRandomNumber().toString();
			    
			    try {

					imageName = DropUtil.uploadImageOnServer(inputStream, randomFolderName, rootFolderPath);
				
				} catch (Exception e) {
					e.printStackTrace();
					isError = true;
					response.setMessage(msgConfig.getProperty("postDrop004"));
					response.setCode("postDrop004");
				}
			    			    
			    if(!isError) {			    	
			    	//Set image path for drop
			    	dropObj.setImagePath(publicURL + "/" + randomFolderName + "/" + imageName);
			    	
			    	dealPostService.saveDealPost(dropObj);
			    			    			    
			    }
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode("postDrop001");
			response.setMessage(msgConfig.getProperty("postDrop001"));
			logger.fatal(DropUtil.getExceptionDescriptionString(e));
			throw new WebApplicationException(Response.ok(response).build());
		}
	    
		return Response.ok(response).build();
	}
	
		
	@GET
	@Path("get-my-want-drops")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response getMyWantDrops(@QueryParam("email") String email) {
		
		List<GetMyWantDropsDTO> dtoList = new ArrayList<GetMyWantDropsDTO>();
		
		DropServiceResponse response = new DropServiceResponse();
		
		try {
			
			dtoList = dealWantedService.getAllDealWantedForUser(email);

		} catch (Exception e) {
			e.printStackTrace();
			response.setCode("getMyWantDrops001");
			response.setMessage(msgConfig.getProperty("getMyWantDrops001"));
			logger.fatal(DropUtil.getExceptionDescriptionString(e));
			throw new WebApplicationException(Response.ok(response).build());
		}
		
		return Response.ok(dtoList).build();
	}
	
	
	@GET
	@Path("get-my-drops")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response getMyDrops(@QueryParam("email") String email) {
		
		List<GetMyDropsDTO> dtoList = new ArrayList<GetMyDropsDTO>();
		
		DropServiceResponse response = new DropServiceResponse();
		
		try {
			
			dtoList = dealPostService.getAllActiveDealPostForUser(email);

		} catch (Exception e) {
			e.printStackTrace();
			response.setCode("getMyDrops001");
			response.setMessage(msgConfig.getProperty("getMyDrops001"));
			logger.fatal(DropUtil.getExceptionDescriptionString(e));
			throw new WebApplicationException(Response.ok(response).build());
		}
		
		return Response.ok(dtoList).build();
	}
	
	
	@POST
	@Path("update-want-drop")
	@Consumes((MediaType.APPLICATION_JSON))
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(rollbackFor = Exception.class)
	public Response updateWantDrop(UpdateWantDropDTO updateWantDropDTO) {
				
		DropServiceResponse response = new DropServiceResponse();
		response.setCode("updateWantDrop002");
		response.setMessage(msgConfig.getProperty("updateWantDrop002"));
		
		try {
			
			dealWantedService.saveOrUpdate(updateWantDropDTO);
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode("updateWantDrop001");
			response.setMessage(msgConfig.getProperty("updateWantDrop001"));
			logger.fatal(DropUtil.getExceptionDescriptionString(e));
			throw new WebApplicationException(Response.ok(response).build());
		}
		
		return Response.ok(response).build();
	} 
	
	
	@POST
	@Path("update-drop")
	@Consumes((MediaType.APPLICATION_JSON))
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(rollbackFor = Exception.class)
	public Response updateDrop(UpdateDropDTO updateDropDTO) {
				
		DropServiceResponse response = new DropServiceResponse();
		response.setCode("updateDrop002");
		response.setMessage(msgConfig.getProperty("updateDrop002"));
		
		try {
			
			dealPostService.saveOrUpdate(updateDropDTO);
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode("updateDrop001");
			response.setMessage(msgConfig.getProperty("updateDrop001"));
			logger.fatal(DropUtil.getExceptionDescriptionString(e));
			throw new WebApplicationException(Response.ok(response).build());
		}
		
		return Response.ok(response).build();
	} 
	
	
	@GET
	@Path("get-drops-for-category")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response getDropsForCategory(
			@QueryParam("categoryId") long categoryId,
			@QueryParam("pageNumber") int pageNumber) {

		List<DealPostDTO> dropList = new ArrayList<DealPostDTO>();

		DropServiceResponse response = new DropServiceResponse();

		try {

			dropList = solrSearchService.searchDrops("", pageNumber,
					SORT_TYPE.DATE, categoryId);

		} catch (Exception e) {
			e.printStackTrace();
			response.setCode("getDropsForCategory001");
			response.setMessage(msgConfig.getProperty("getDropsForCategory001"));
			logger.fatal(DropUtil.getExceptionDescriptionString(e));
			throw new WebApplicationException(Response.ok(response).build());
		}

		return Response.ok(dropList).build();
	}
}
