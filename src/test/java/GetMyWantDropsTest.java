import java.io.IOException;

import javax.xml.bind.JAXBException;


public class GetMyWantDropsTest {
	
	public static void main(String[] args) throws JAXBException, IOException {
		
		String url = "http://localhost:8080/Drop/rest/drop-service/get-my-want-drops?email=a@b1.com";
		
		TestUtil.sendRequest(url, "", "GET");

	}
}
