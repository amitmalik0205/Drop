import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

import javax.xml.bind.JAXBException;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClientBuilder;

import com.drop.rest.request.dto.PostDropDTO;
import com.fasterxml.jackson.databind.ObjectMapper;


public class PostDropTest {

	public static void main(String[] args) throws JAXBException, IOException {

		HttpClient httpclient = HttpClientBuilder.create().build();
		HttpPost httppost = new HttpPost("http://localhost:8080/Drop/rest/drop-service/post-drop");
		
		PostDropDTO  dto = new PostDropDTO();
		dto.setTitle("Rest Service Post title1");
		dto.setCategory(1L);
		dto.setStarts("04/03/2015 7:09 PM");
		dto.setExpires("04/03/2016 7:09 PM");
		dto.setSalePrice(new BigDecimal(1000));
		dto.setRetailPrice(new BigDecimal(1500));
		dto.setDescription("Rest post drop description1");
		dto.setSpecialInstructions("Rest post drop instructions1");
		dto.setCouponsRequired(true);
		dto.setMembershipRequired(false);
		dto.setDealType("onlineDeal");
		dto.setUrl("www.rest.com");
		
		dto.setEmail("a@b1.com");
		
		ObjectMapper mapper = new ObjectMapper();
		String content = mapper.writeValueAsString(dto);
		System.out.println(content);
		
		try {

			MultipartEntityBuilder multipart = MultipartEntityBuilder.create();
			multipart.addPart("dropDetails", new StringBody(content));
			multipart.addPart("dropImage", new FileBody(new File("C:\\happyhours_data\\deal_images\\4.png")));
			

			HttpEntity entity2 = multipart.build();
			httppost.setEntity(entity2);

			httpclient.execute(httppost);

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

}
