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
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.w3c.dom.Document;

import th.go.excise.edbarcode.ws.provider.oxm.EbarcodeGetSR12011ReportRequest;
import th.go.excise.edbarcode.ws.provider.oxm.EbarcodeGetSR12011ReportResponse;

@Service("getSRReportServiceTest")
public class GetSR12011ReportServiceTest {
	
	private static final Logger logger = LogManager.getLogger(GetSR12011ReportServiceTest.class);
	
	@Value("${edBarcodeServiceTest.getSR12011Report.init.xml}")
	private String linkURI;
	
	@Autowired
	private WebServiceTemplate getSR12011ReportWsTemplateTest;
	
	@Autowired
	private ReadFileXMLServiceTest readFileXMLServiceTest;

	public String xmlcallws(String xmlDataString) {
	
	
		EbarcodeGetSR12011ReportRequest request = null;
		try {
			InputStream is = new ByteArrayInputStream(xmlDataString.getBytes("UTF-8"));
			SOAPMessage soapMessage = MessageFactory.newInstance().createMessage(new MimeHeaders(), is);
			is.close();
			
			Unmarshaller unmarshaller = JAXBContext.newInstance(EbarcodeGetSR12011ReportRequest.class).createUnmarshaller();
			request = (EbarcodeGetSR12011ReportRequest)unmarshaller.unmarshal(soapMessage.getSOAPBody().extractContentAsDocument());
			
		} catch (JAXBException e) {
			logger.error(e.getStackTrace());
		}  catch (IOException e) {
			logger.error(e.getStackTrace());
		} catch (SOAPException e) {
			logger.error(e.getStackTrace());
		}
		
		EbarcodeGetSR12011ReportResponse ebarcodeGetSR12011ReportResponse = callClientWs(request);
		
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
			logger.error(e.getStackTrace());
		} catch (JAXBException e) {
			logger.error(e.getStackTrace());
		} catch (SOAPException e) {
			logger.error(e.getStackTrace());
		} catch (IOException e) {
			logger.error(e.getStackTrace());
		} catch (TransformerException e) {
			logger.error(e.getStackTrace());
		}
		return xmlString;

	}
	
	private EbarcodeGetSR12011ReportResponse callClientWs(EbarcodeGetSR12011ReportRequest ebarcodeGetSR12011ReportRequest){
		
		EbarcodeGetSR12011ReportResponse ebarcodeGetSR12011ReportResponse = (EbarcodeGetSR12011ReportResponse) getSR12011ReportWsTemplateTest.marshalSendAndReceive(ebarcodeGetSR12011ReportRequest);
		return ebarcodeGetSR12011ReportResponse;
		
	}

	public String getStringRequestXMLInit(){
		String strGetRequestXMLInit = null;
		strGetRequestXMLInit = readFileXMLServiceTest.callRequest(linkURI).toString();
		return strGetRequestXMLInit;
	}
	
	public String getURI(){
		String strURI = null;
		
		strURI = getSR12011ReportWsTemplateTest.getDefaultUri();
		
		return strURI;
	}
	
}
