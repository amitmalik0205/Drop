import java.io.IOException;

import javax.xml.bind.JAXBException;

import com.drop.rest.request.dto.LoginDTO;
import com.fasterxml.jackson.databind.ObjectMapper;


public class UpdateProfileTest {

	public static void main(String[] args) throws JAXBException, IOException {
		
		String url = "http://localhost:8080/Drop/rest/drop-service/update-profile";
	
		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setFirstName("Rest New");
		loginDTO.setLastName("user");
		loginDTO.setEmail("amit.cdac@gmail.com");
		loginDTO.setPhoneNumber("1234567");
		loginDTO.setAddressLine1("25B, AG_1");
		loginDTO.setAddressLine2("Vikaspuri");
		loginDTO.setCity("New Delhi");
		loginDTO.setState("Delhi");
		loginDTO.setZip("110018");
		
		ObjectMapper mapper = new ObjectMapper();
		String content = mapper.writeValueAsString(loginDTO);
		System.out.println(content);

		TestUtil.sendRequest(url, content, "POST");
		
	}
}
