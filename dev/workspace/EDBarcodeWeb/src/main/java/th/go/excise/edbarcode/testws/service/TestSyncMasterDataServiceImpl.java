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

@Service("testSyncMasterDataService")
public class TestSyncMasterDataServiceImpl implements TestSyncMasterDataService {
	
	private static final Logger logger = LogManager.getLogger();

		
	@Autowired
	private WebServiceTemplate syncMasterDataWsTemplateTest;

	@Override
	public String xmlcallws(String xmlDataString,String uri) {
		
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SOAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return output;
	}
	
	
	@Override
	public String getwsuri() {

		return syncMasterDataWsTemplateTest.getDefaultUri();
	}
	
	
	
}
