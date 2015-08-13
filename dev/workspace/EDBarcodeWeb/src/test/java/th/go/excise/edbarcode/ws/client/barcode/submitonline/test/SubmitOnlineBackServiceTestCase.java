package th.go.excise.edbarcode.ws.client.barcode.submitonline.test;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import th.go.excise.edbarcode.ws.client.barcode.submitonline.oxm.EbarcodeSubmitOnlineRequest;
import th.go.excise.edbarcode.ws.client.barcode.submitonline.oxm.EbarcodeSubmitOnlineResponse;
import th.go.excise.edbarcode.ws.client.barcode.submitonline.service.SubmitOnlineBackService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"file:WebContent/WEB-INF/config/application-config.xml",
	"file:WebContent/WEB-INF/config/barcode-ws-config.xml"
})
public class SubmitOnlineBackServiceTestCase {
	
	private static final Logger logger = LogManager.getLogger(SubmitOnlineBackServiceTestCase.class);
	
	private static final String XML_PATH = "th/go/excise/edbarcode/ws/client/barcode/submitonline/xml/";
	
	@Autowired
	private SubmitOnlineBackService submitOnlineBackService;
	
	private SOAPMessage getSoapMessage(String xmlFileName) throws SOAPException, IOException {
		InputStream xmlFile = ClassLoader.getSystemResourceAsStream(XML_PATH + xmlFileName);
		System.out.println(xmlFile);
		
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage(new MimeHeaders(), xmlFile);
		xmlFile.close();
		
		return soapMessage;
	}

	@Test
	public void testCallSubmitOnlineWebService() {
		
		EbarcodeSubmitOnlineRequest request = null;
		
		try {
			SOAPMessage soapMessage = getSoapMessage("example.xml");
			Unmarshaller unmarshaller = JAXBContext.newInstance(EbarcodeSubmitOnlineRequest.class).createUnmarshaller();
			
			request = (EbarcodeSubmitOnlineRequest) unmarshaller.unmarshal(soapMessage.getSOAPBody().extractContentAsDocument());
			System.out.println(request);
			
			EbarcodeSubmitOnlineResponse response = submitOnlineBackService.doService(request);
			System.out.println(response);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			fail(e.getMessage());
		}

	}

}
