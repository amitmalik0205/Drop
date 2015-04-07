import java.io.IOException;
import java.math.BigDecimal;

import javax.xml.bind.JAXBException;

import com.drop.rest.request.dto.UpdateWantDropDTO;
import com.fasterxml.jackson.databind.ObjectMapper;


public class UpdateWantDropTest {

	public static void main(String[] args) throws JAXBException, IOException {

		String url = "http://localhost:8080/Drop/rest/drop-service/update-want-drop";

		UpdateWantDropDTO dto = new UpdateWantDropDTO();
		dto.setTitle("Rest Drop Want update");
		dto.setDescription("Rest Drop Want Description update");
		dto.setMaxPrice(new BigDecimal(10000));
		dto.setCategoryId(2L);
		dto.setTipAmount(new BigDecimal(1000));
		dto.setAcceptCoupons(true);
		dto.setWantNew(true);
		dto.setWantUsed(true);
		dto.setWouldBuyLocally(true);
		dto.setWouldBuyOnline(false);
		dto.setRefurbishedOK(true);
		
		dto.setWantDropId(13L);

		ObjectMapper mapper = new ObjectMapper();
		String content = mapper.writeValueAsString(dto);
		System.out.println(content);

		TestUtil.sendRequest(url, content, "POST");

	}
}
