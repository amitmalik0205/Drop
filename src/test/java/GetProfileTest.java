import java.io.IOException;

import javax.xml.bind.JAXBException;


public class GetProfileTest {
	
	public static void main(String[] args) throws JAXBException, IOException {
		
		String url = "http://localhost:8080/Drop/rest/drop-service/get-profile?email=amit.cdac@gmail.com";
		
		TestUtil.sendRequest(url, "", "GET");

	}

}
