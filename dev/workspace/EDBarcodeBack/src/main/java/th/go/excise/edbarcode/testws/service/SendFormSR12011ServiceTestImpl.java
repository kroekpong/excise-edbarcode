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

import th.go.excise.edbarcode.ws.provider.oxm.EbarcodeSendFormSR12011Request;
import th.go.excise.edbarcode.ws.provider.oxm.EbarcodeSendFormSR12011Response;

@Service("sendFormSR12011Service")
public class SendFormSR12011ServiceTestImpl implements SendFormSR12011ServiceTest {

private static final Logger logger = LogManager.getLogger(SendFormSR12011ServiceTestImpl.class);
	
	@Autowired
	private WebServiceTemplate sendFormSR12011WsTemplateTest;
	
	@Override
	public String doService(String xmlString, String uri) {
		
		logger.info(" ########################### Bessfore Call sendFormSR12011WsTemplateTest");
		
		EbarcodeSendFormSR12011Response response = null;
		EbarcodeSendFormSR12011Request request = null;
		
		try {
			InputStream xmlInputStream = new ByteArrayInputStream(xmlString.getBytes("UTF-8"));
			System.out.println(xmlInputStream);
			
			MessageFactory messageFactory = MessageFactory.newInstance();
			SOAPMessage soapMessage = messageFactory.createMessage(new MimeHeaders(), xmlInputStream);
			xmlInputStream.close();
			
			Unmarshaller unmarshaller = JAXBContext.newInstance(EbarcodeSendFormSR12011Request.class).createUnmarshaller();
			
			request = (EbarcodeSendFormSR12011Request) unmarshaller.unmarshal(soapMessage.getSOAPBody().extractContentAsDocument());
			

			
		}catch(Exception e){
			logger.debug(e.getMessage(), e);
			e.printStackTrace();
			
			
		}
		
		logger.info(" ########################### After Call  sendFormSR12011WsTemplateTest");
		
		sendFormSR12011WsTemplateTest.setDefaultUri(uri);
		response = (EbarcodeSendFormSR12011Response)sendFormSR12011WsTemplateTest.marshalSendAndReceive(request);
		System.out.println(response);
		StringWriter sw = new StringWriter();
		  try {
		   
		   JAXBContext jaxbContext = JAXBContext.newInstance(EbarcodeSendFormSR12011Response.class);   
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

	@Override
	public String getWsUri() {
		// TODO Auto-generated method stub
		return sendFormSR12011WsTemplateTest.getDefaultUri();
	}
	
}
