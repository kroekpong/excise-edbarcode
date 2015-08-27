package th.go.excise.edbarcode.testws.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.w3c.dom.Document;

import th.go.excise.edbarcode.ws.provider.oxm.EbarcodeSyncMasterDataRequest;
import th.go.excise.edbarcode.ws.provider.oxm.EbarcodeSyncMasterDataResponse;

@Service("syncMasterDataServiceTest")
public class SyncMasterDataServiceTest {
	
	private static final Logger logger = LogManager.getLogger(SyncMasterDataServiceTest.class);

		
	@Autowired
	private WebServiceTemplate syncMasterDataWsTemplateTest;

	public String xmlcallws(String xmlDataString,String uri) {
		
		EbarcodeSyncMasterDataRequest request = null;
		
		
		try {
			InputStream is = new ByteArrayInputStream(xmlDataString.getBytes("UTF-8"));
			SOAPMessage soapMessage = MessageFactory.newInstance().createMessage(new MimeHeaders(), is);
			is.close();
			
			Unmarshaller unmarshaller = JAXBContext.newInstance(EbarcodeSyncMasterDataRequest.class).createUnmarshaller();
			request = (EbarcodeSyncMasterDataRequest)unmarshaller.unmarshal(soapMessage.getSOAPBody().extractContentAsDocument());
			
		} catch (JAXBException e) {
			logger.error(e.getStackTrace());
		} catch (IOException e) {
			logger.error(e.getStackTrace());
		} catch (SOAPException e) {
			logger.error(e.getStackTrace());
		}
		
		
		EbarcodeSyncMasterDataResponse ebarcodeSyncMasterDataResponse = callClientWs(request);
		
		String output =null;
		
		try{
			Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			Marshaller marshaller = JAXBContext.newInstance(EbarcodeSyncMasterDataResponse.class).createMarshaller();
			marshaller.marshal(ebarcodeSyncMasterDataResponse, document);

			SOAPMessage soapMessage = MessageFactory.newInstance().createMessage();
			soapMessage.getSOAPBody().addDocument(document);

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			soapMessage.writeTo(outputStream);
			output = new String(outputStream.toByteArray());
			
		} catch (ParserConfigurationException e) {
			logger.error(e.getStackTrace());
		} catch (JAXBException e) {
			logger.error(e.getStackTrace());
		} catch (SOAPException e) {
			logger.error(e.getStackTrace());
		} catch (IOException e) {
			logger.error(e.getStackTrace());
		}
		return output;
	}
	
	private EbarcodeSyncMasterDataResponse callClientWs(EbarcodeSyncMasterDataRequest request){
		
		EbarcodeSyncMasterDataResponse ebarcodeSyncMasterDataResponse= (EbarcodeSyncMasterDataResponse)syncMasterDataWsTemplateTest.marshalSendAndReceive(request);
		return ebarcodeSyncMasterDataResponse;
		
	}
	
	
	public String getwsuri() {

		return syncMasterDataWsTemplateTest.getDefaultUri();
	}

}
