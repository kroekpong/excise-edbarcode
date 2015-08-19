package th.go.excise.edbarcode.testws.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

import th.go.excise.edbarcode.ws.provider.oxm.EbarcodeSendToBackendRequest;
import th.go.excise.edbarcode.ws.provider.oxm.EbarcodeSendToBackendResponse;

@Service("sendToBackService")
public class SendToBackServiceImpl implements SendToBackService {

private static final Logger logger = LogManager.getLogger(SendToBackServiceImpl.class);
	
	@Autowired
	private WebServiceTemplate sendToBacendkWsTemplateTest;
	
	@Override
	public String doService(String xmlString) {
		
		logger.info(" ########################### Bessfore Call sendToBacendkWsTemplateTest");
		
		EbarcodeSendToBackendResponse response = null;
		EbarcodeSendToBackendRequest request = null;
		
		try {
			InputStream xmlInputStream = new ByteArrayInputStream(xmlString.getBytes("UTF-8"));
			System.out.println(xmlInputStream);
			
			MessageFactory messageFactory = MessageFactory.newInstance();
			SOAPMessage soapMessage = messageFactory.createMessage(new MimeHeaders(), xmlInputStream);
			xmlInputStream.close();
			
			Unmarshaller unmarshaller = JAXBContext.newInstance(EbarcodeSendToBackendRequest.class).createUnmarshaller();
			
			request = (EbarcodeSendToBackendRequest) unmarshaller.unmarshal(soapMessage.getSOAPBody().extractContentAsDocument());
			

			
		}catch(Exception e){
			logger.debug(e.getMessage(), e);
			e.printStackTrace();
			
			
		}
		
		logger.info(" ########################### After Call  sendToBacendkWsTemplateTest");
		
		response = (EbarcodeSendToBackendResponse)sendToBacendkWsTemplateTest.marshalSendAndReceive(request);
		System.out.println(response);
		StringWriter sw = new StringWriter();
		  try {
		   
		   JAXBContext jaxbContext = JAXBContext.newInstance(EbarcodeSendToBackendResponse.class);   
		   Marshaller marshaller = jaxbContext.createMarshaller();
		      marshaller.marshal(response, sw);
		      sw.close();
		      
		  } catch (JAXBException e) {
			  logger.debug(e.getMessage(), e);
			  e.printStackTrace();
		  }
		  catch (IOException e) {
			  logger.debug(e.getMessage(), e);
			  e.printStackTrace();
		  }
		  return sw.toString();
	}
	
}