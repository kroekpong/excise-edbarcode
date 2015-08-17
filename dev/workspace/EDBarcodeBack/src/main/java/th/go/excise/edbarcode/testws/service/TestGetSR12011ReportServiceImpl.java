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

import th.go.excise.edbarcode.ws.provider.oxm.EbarcodeGetSR12011ReportRequest;
import th.go.excise.edbarcode.ws.provider.oxm.EbarcodeGetSR12011ReportResponse;

@Service("testGetSRReportService")
public class TestGetSR12011ReportServiceImpl implements TestGetSR12011ReportService {
	
	private static final Logger logger = LogManager.getLogger();
	
	@Autowired
	private WebServiceTemplate submitOnlineWsTemplate;

	@Override
	public String xmlcallws(String xmlDataString,String uri) {
	
	
		EbarcodeGetSR12011ReportRequest request = null;
		try {
			InputStream is = new ByteArrayInputStream(xmlDataString.getBytes("UTF-8"));
			SOAPMessage soapMessage = MessageFactory.newInstance().createMessage(new MimeHeaders(), is);
			is.close();
			
			Unmarshaller unmarshaller = JAXBContext.newInstance(EbarcodeGetSR12011ReportRequest.class).createUnmarshaller();
			request = (EbarcodeGetSR12011ReportRequest)unmarshaller.unmarshal(soapMessage.getSOAPBody().extractContentAsDocument());
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SOAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		submitOnlineWsTemplate.setDefaultUri(uri);
		EbarcodeGetSR12011ReportResponse ebarcodeGetSR12011ReportResponse = (EbarcodeGetSR12011ReportResponse) submitOnlineWsTemplate.marshalSendAndReceive(request);
		
		StringWriter sw = new StringWriter();
		try {
			
			JAXBContext jaxbContext = JAXBContext.newInstance(EbarcodeGetSR12011ReportResponse.class);			
			Marshaller marshaller = jaxbContext.createMarshaller();
		    marshaller.marshal(ebarcodeGetSR12011ReportResponse, sw);
		    sw.close();
		    
//		    SOAPMessage soapMessage =  MessageFactory.newInstance().createMessage(new MimeHeaders(),new ByteArrayInputStream(sw.toString().getBytes("UTF-8")));
		    
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		catch (SOAPException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return sw.toString();
	}

	@Override
	public String getwsuri() {

		return submitOnlineWsTemplate.getDefaultUri();
	}
	

	
	
	
}
