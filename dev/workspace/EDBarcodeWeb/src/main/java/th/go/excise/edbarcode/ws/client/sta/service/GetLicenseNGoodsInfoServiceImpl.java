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

import th.go.excise.edbarcode.common.constant.WebServiceConstant;
import th.go.excise.edbarcode.ws.client.sta.oxm.Body;
import th.go.excise.edbarcode.ws.client.sta.oxm.Error;
import th.go.excise.edbarcode.ws.client.sta.oxm.StaBacRequest;
import th.go.excise.edbarcode.ws.client.sta.oxm.StaBacResponse;

@Service("getLicenseNGoodsInfoService")
public class GetLicenseNGoodsInfoServiceImpl implements GetLicenseNGoodsInfoService {
	
	private static final Logger logger = LogManager.getLogger(GetLicenseNGoodsInfoServiceImpl.class);
	
	@Value("${summitService.client.rmi}")
	private String rmiUrl;
	
	@Override
	public StaBacResponse doService(StaBacRequest request) {
		logger.info(" ##################################### Step 1 In GetLicenseNGoodsInfoService.doService");
		
		StaBacResponse response = null;
		
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
			logger.error(e.getMessage(), e);
			response = new StaBacResponse();
			setError(response, e.getMessage());
		} catch (JAXBException e) {
			logger.error(e.getMessage(), e);
			response = new StaBacResponse();
			setError(response, e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			response = new StaBacResponse();
			setError(response, e.getMessage());
		}
		
		logger.info(" ##################################### After Call GetLicenseNGoodsInfoService.doService response:  " + response);
		
		return response;
	}
	
	private String sendBacRequest(String xmlData) {
		String xmlResult = null;

		try {
			long startTime = System.currentTimeMillis();
			SccAppServer_Stub sccAppServer = (SccAppServer_Stub) Naming.lookup(rmiUrl);
			logger.debug("#### Lookup Result: " + Naming.lookup(rmiUrl));

			xmlResult = sccAppServer.process(xmlData);

			long finishTime = System.currentTimeMillis();
			logger.debug("TIME: " + (finishTime - startTime) + "msec");
		} catch (MalformedURLException e) {
			logger.error(e.getMessage(), e);
		} catch (RemoteException e) {
			logger.error(e.getMessage(), e);
		} catch (NotBoundException e) {
			logger.error(e.getMessage(), e);
		}
		
		return xmlResult;
	}
	
	private void setError(StaBacResponse response, String message) {
		Error error = new Error();
		error.setCode(WebServiceConstant.STATUS_CODE.ERROR);
		error.setDescription(message);
		error.setAction(WebServiceConstant.STA.TRAN_CODE_GET_LICENSE_AND_GOODS_INFO);
		
		Body body = new Body();
		body.setError(error);
		
		response.setBody(body);
	}

}
