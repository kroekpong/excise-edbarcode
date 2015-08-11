package th.go.excise.edbarcode.ws.client.sta.service;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;

import local.scc.dev.system.rmi.SccAppServer_Stub;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import th.go.excise.edbarcode.ws.client.sta.oxm.StaBacRequest;
import th.go.excise.edbarcode.ws.client.sta.oxm.StaBacResponse;

@Service("addNewFormSR12011Service")
public class AddNewFormSR12011ServiceImpl implements AddNewFormSR12011Service {
	
	private static final Logger logger = LogManager.getLogger(AddNewFormSR12011ServiceImpl.class);
	
	@Value("${summitService.rmi}")
	private String rmiUrl;
	
	@Override
	public StaBacResponse doService(StaBacRequest request) {
		StaBacResponse response = null;
		
		logger.info(" ##################################### Step 1 In AddNewFormSR12011Service.doService");
		
		try {
			Marshaller jaxbMarshaller = JAXBContext.newInstance(StaBacRequest.class).createMarshaller();
			
			StringWriter writer = new StringWriter();
			jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "TIS-620");
			jaxbMarshaller.marshal(request, writer);
			
			String xmlData = writer.getBuffer().toString();
			logger.info("RMI Request: " + xmlData);
			writer.close();
			
			String xmlResult = sendBacRequest(xmlData);
			logger.info("RMI Response: " + xmlResult);
			
			Unmarshaller jaxbUnmarshaller = JAXBContext.newInstance(StaBacResponse.class).createUnmarshaller();
			
			StringReader reader = new StringReader(xmlResult);
			response = (StaBacResponse) jaxbUnmarshaller.unmarshal(reader);
			reader.close();
			
		} catch (PropertyException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		logger.info(" ##################################### After Call AddNewFormSR12011Service.doService response:  "+response);
		
		return response;
	}
	
	private String sendBacRequest(String xmlData) {
		String xmlResult;
		SccAppServer_Stub sccAppServer;
		//String rmiUrl = "rmi://172.17.2.22:21104/SccAppServer";
		//String rmiUrl = "rmi://192.168.42.4:1104/SccAppServer";

		try {
			long startTime = System.currentTimeMillis();
			sccAppServer = (SccAppServer_Stub) Naming.lookup(rmiUrl);
			System.out.println(" #### Lookup Result :" + Naming.lookup(rmiUrl));

			xmlResult = sccAppServer.process(xmlData);

			long finishTime = System.currentTimeMillis();
			System.out.println("TIME : " + (finishTime - startTime) + "mesc");
			return xmlResult;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		} catch (NotBoundException e) {
			e.printStackTrace();
			return null;
		}
	}

}
