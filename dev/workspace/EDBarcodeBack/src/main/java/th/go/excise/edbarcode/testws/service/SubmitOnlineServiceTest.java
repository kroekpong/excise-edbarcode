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

import th.go.excise.edbarcode.ws.provider.oxm.EbarcodeSubmitOnlineRequest;
import th.go.excise.edbarcode.ws.provider.oxm.EbarcodeSubmitOnlineResponse;

@Service("submitOnlineService")
public class SubmitOnlineServiceTest {
	
	private static final Logger logger = LogManager.getLogger(SubmitOnlineServiceTest.class);
	
	@Value("${edBarcodeServiceTest.submitOnline.init.xml}")
	private String linkURI;
	
	@Autowired
	private WebServiceTemplate submitOnlineWsTemplateTest;
	
	@Autowired
	private ReadFileXMLServiceTest readFileXMLServiceTest;
	
	private	EbarcodeSubmitOnlineResponse response = null;
	private EbarcodeSubmitOnlineRequest request = null;
	
	public String doService(String xmlString) {
		
		logger.info(" ########################### Bessfore Call submitOnlineWsTemplateTest");
		
		try {
			InputStream xmlInputStream = new ByteArrayInputStream(xmlString.getBytes("UTF-8"));
			System.out.println(xmlInputStream);
			
			MessageFactory messageFactory = MessageFactory.newInstance();
			SOAPMessage soapMessage = messageFactory.createMessage(new MimeHeaders(), xmlInputStream);
			xmlInputStream.close();
			
			Unmarshaller unmarshaller = JAXBContext.newInstance(EbarcodeSubmitOnlineRequest.class).createUnmarshaller();
			
			request = (EbarcodeSubmitOnlineRequest) unmarshaller.unmarshal(soapMessage.getSOAPBody().extractContentAsDocument());
			

			
		}catch(Exception e){
			
			logger.debug(e.getMessage(), e);
			e.printStackTrace();
		}
		
		logger.info(" ########################### After Call  submitOnlineWsTemplateTest");
		
		response = callClientWs(request);
		
		String output = null;
		String xmlStringOutput = null;
		  try {
		   
			Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			Marshaller marshaller = JAXBContext.newInstance(EbarcodeSubmitOnlineResponse.class).createMarshaller();
			marshaller.marshal(response, document);

			SOAPMessage soapMessage = MessageFactory.newInstance().createMessage();
			soapMessage.getSOAPBody().addDocument(document);

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			soapMessage.writeTo(outputStream);
			output = new String(outputStream.toByteArray()); 
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        transformerFactory.setAttribute("indent-number", 2);
			Transformer transformer = transformerFactory.newTransformer(); 
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			
			StreamResult result = new StreamResult(new StringWriter());
			Source source = new StreamSource(new StringReader(output));
			transformer.transform(source, result);
			xmlStringOutput = result.getWriter().toString();
		      
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
				logger.error(e.getStackTrace());
			}
		  
		  return xmlStringOutput;
	}
	
	private EbarcodeSubmitOnlineResponse callClientWs(EbarcodeSubmitOnlineRequest ebarcodeSubmitOnlineRequest){
		
		EbarcodeSubmitOnlineResponse ebarcodeSubmitOnlineResponse = (EbarcodeSubmitOnlineResponse)submitOnlineWsTemplateTest.marshalSendAndReceive(ebarcodeSubmitOnlineRequest);
		return ebarcodeSubmitOnlineResponse;
	}
	
	public String getStringRequestXMLInit(){
		
		String strGetRequestXMLInit = null;
		strGetRequestXMLInit = readFileXMLServiceTest.callRequest(linkURI).toString();
		return strGetRequestXMLInit;
	}
	
	public String getURI(){
		String strURI = null;
		
		strURI = submitOnlineWsTemplateTest.getDefaultUri();
		
		return strURI;
	}
	
}
