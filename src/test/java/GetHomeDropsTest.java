import java.io.IOException;

import javax.xml.bind.JAXBException;


public class GetHomeDropsTest {
	
	public static void main(String[] args) throws JAXBException, IOException {
		
		String url = "http://localhost:8080/DropServer/rest/drop-service/get-home-page-drops";
		
		TestUtil.sendRequest(url, "", "GET");

	}

}
