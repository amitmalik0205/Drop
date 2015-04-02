import java.io.IOException;

import javax.xml.bind.JAXBException;


public class GetDealCategoriesTest {
	
	public static void main(String[] args) throws JAXBException, IOException {
		
		String url = "http://localhost:8080/Drop/rest/drop-service/get-deal-categories";
		
		TestUtil.sendRequest(url, "", "GET");

	}

}
