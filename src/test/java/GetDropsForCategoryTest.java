import java.io.IOException;

import javax.xml.bind.JAXBException;


public class GetDropsForCategoryTest {

public static void main(String[] args) throws JAXBException, IOException {
		
		String url = "http://localhost:8080/DropServer/rest/drop-service/get-drops-for-category?categoryId=3&pageNumber=1";
		
		TestUtil.sendRequest(url, "", "GET");

	}
}
