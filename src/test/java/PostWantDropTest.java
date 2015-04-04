import java.io.IOException;
import java.math.BigDecimal;

import javax.xml.bind.JAXBException;

import com.drop.rest.request.dto.PostWantDropDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PostWantDropTest {

	public static void main(String[] args) throws JAXBException, IOException {

		String url = "http://localhost:8080/Drop/rest/drop-service/post-want-drop";

		PostWantDropDTO dto = new PostWantDropDTO();
		dto.setTitle("Rest Drop Want");
		dto.setDescription("Rest Drop Want Description");
		dto.setMaxPrice(new BigDecimal(10000));
		dto.setCategory(1L);
		dto.setTipAmount(new BigDecimal(100));
		dto.setAcceptCoupons(true);
		dto.setWantNew(true);
		dto.setWantUsed(true);
		dto.setWouldBuyLocally(true);
		dto.setWouldBuyOnline(false);
		dto.setRefurbishedOK(true);
		dto.setEmail("a@b1.com");

		ObjectMapper mapper = new ObjectMapper();
		String content = mapper.writeValueAsString(dto);
		System.out.println(content);

		TestUtil.sendRequest(url, content, "POST");

	}
}
