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
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

import th.go.excise.edbarcode.ws.provider.oxm.EbarcodeSyncMasterDataRequest;
import th.go.excise.edbarcode.ws.provider.oxm.EbarcodeSyncMasterDataResponse;

@Service("testSyncMasterDataService")
public class TestSyncMasterDataServiceImpl implements TestSyncMasterDataService {
	
	private static final Logger logger = LogManager.getLogger();

		
	@Autowired
	private WebServiceTemplate syncMasterDataWsTemplateTest;

	@Override
	public String xmlcallws(String xmlDataString) {
		
		EbarcodeSyncMasterDataRequest request = null;
		
		
		try {
			InputStream is = new ByteArrayInputStream(xmlDataString.getBytes("UTF-8"));
			SOAPMessage soapMessage = MessageFactory.newInstance().createMessage(new MimeHeaders(), is);
			is.close();
			
			Unmarshaller unmarshaller = JAXBContext.newInstance(EbarcodeSyncMasterDataRequest.class).createUnmarshaller();
			request = (EbarcodeSyncMasterDataRequest)unmarshaller.unmarshal(soapMessage.getSOAPBody().extractContentAsDocument());
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SOAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		EbarcodeSyncMasterDataResponse ebarcodeSyncMasterDataResponse= (EbarcodeSyncMasterDataResponse)syncMasterDataWsTemplateTest.marshalSendAndReceive(request);
		StringWriter sw = new StringWriter();
		
	
		try {
			
			JAXBContext jaxbContext = JAXBContext.newInstance(EbarcodeSyncMasterDataResponse.class);			
			Marshaller marshaller = jaxbContext.createMarshaller();
			
		    marshaller.marshal(ebarcodeSyncMasterDataResponse, sw);
		    
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return sw.toString();
	}
	
	

	
	
	
}
