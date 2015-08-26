package th.go.excise.edbarcode.testws.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;

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
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import th.go.excise.edbarcode.ws.provider.oxm.EbarcodeGetSR12011ReportRequest;
import th.go.excise.edbarcode.ws.provider.oxm.EbarcodeGetSR12011ReportResponse;

@Service("testGetSRReportService")
public class TestGetSR12011ReportServiceImpl implements TestGetSR12011ReportService {
	
	private static final Logger logger = LogManager.getLogger();
	
	@Autowired
	private WebServiceTemplate getSR12011ReportWsTemplateTest;

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
		
		getSR12011ReportWsTemplateTest.setDefaultUri(uri);
		EbarcodeGetSR12011ReportResponse ebarcodeGetSR12011ReportResponse = (EbarcodeGetSR12011ReportResponse) getSR12011ReportWsTemplateTest.marshalSendAndReceive(request);
		
		String xmlString =null;
		try{
			Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			Marshaller marshaller = JAXBContext.newInstance(EbarcodeGetSR12011ReportResponse.class).createMarshaller();
			marshaller.marshal(ebarcodeGetSR12011ReportResponse, document);

			SOAPMessage soapMessage = MessageFactory.newInstance().createMessage();
			soapMessage.getSOAPBody().addDocument(document);

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			soapMessage.writeTo(outputStream);
			String output = new String(outputStream.toByteArray());
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        transformerFactory.setAttribute("indent-number", 2);
			Transformer transformer = transformerFactory.newTransformer(); 
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			
			StreamResult result = new StreamResult(new StringWriter());
			Source source = new StreamSource(new StringReader(output));
			transformer.transform(source, result);
			xmlString = result.getWriter().toString();

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
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return xmlString;

	}

	@Override
	public String getwsuri() {

		return getSR12011ReportWsTemplateTest.getDefaultUri();
	}
	

	
	
	
}
