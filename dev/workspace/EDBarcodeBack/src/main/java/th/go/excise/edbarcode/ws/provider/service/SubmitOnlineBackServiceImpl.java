package th.go.excise.edbarcode.ws.provider.service;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.edbarcode.common.constant.WebServiceConstant;
import th.go.excise.edbarcode.common.util.DateUtils;
import th.go.excise.edbarcode.ws.client.sta.service.AddNewFormSR12011Service;
import th.go.excise.edbarcode.ws.provider.bean.XmlData;
import th.go.excise.edbarcode.ws.provider.oxm.EbarcodeSubmitOnlineRequest;
import th.go.excise.edbarcode.ws.provider.oxm.EbarcodeSubmitOnlineResponse;

@Service("submitOnlineBackService")
public class SubmitOnlineBackServiceImpl implements SubmitOnlineBackService {
	
	private static final Logger logger = LogManager.getLogger(SubmitOnlineBackServiceImpl.class);
	
	@Autowired
	private AddNewFormSR12011Service addNewFormSR12011Service;
	
	@Override
	public EbarcodeSubmitOnlineResponse getResponse(EbarcodeSubmitOnlineRequest request) {
		logger.info("getResponse method");
		
		EbarcodeSubmitOnlineResponse response = null;
		
		try {
			// Create WebService Request
			th.go.excise.edbarcode.ws.client.sta.oxm.StaBacRequest wsRequest = prepareWsRequest(request);
			
			// Call Service
			th.go.excise.edbarcode.ws.client.sta.oxm.StaBacResponse wsResponse = addNewFormSR12011Service.doService(wsRequest);
			
			if (WebServiceConstant.STATUS_CODE.OK.equalsIgnoreCase(wsResponse.getHeader().getResultCode())) {
				// success
				response = prepareWsResponse(wsResponse);
				response.setSubmitOnlineStatus(wsResponse.getHeader().getResultCode());
				response.setSubmitOnlineDesc(WebServiceConstant.STATUS_DESC.SUCCESS);
			} else {
				// error
				response = new EbarcodeSubmitOnlineResponse();
				response.setSubmitOnlineStatus(wsResponse.getBody().getError().getCode());
				response.setSubmitOnlineDesc(wsResponse.getBody().getError().getDescription());
				logger.error("Call SubmitOnlineWebService Failed: {}: {}", response.getSubmitOnlineStatus(), response.getSubmitOnlineDesc());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response = new EbarcodeSubmitOnlineResponse();
			response.setSubmitOnlineStatus(WebServiceConstant.STATUS_CODE.ERROR);
			response.setSubmitOnlineDesc(e.getMessage());
		}
		
		return response;
	}

	private th.go.excise.edbarcode.ws.client.sta.oxm.StaBacRequest prepareWsRequest(EbarcodeSubmitOnlineRequest request) throws JAXBException, IOException {
		
		// Prepare Request Header
		th.go.excise.edbarcode.ws.client.sta.oxm.HeaderRequest wsHeader = new th.go.excise.edbarcode.ws.client.sta.oxm.HeaderRequest();
		wsHeader.setSourceSystem(WebServiceConstant.STA.SYSTEM_BARCODE);
		wsHeader.setDestinationSystem(WebServiceConstant.STA.SYSTEM_STA_BACK);
		wsHeader.setTransactionCode(WebServiceConstant.STA.TRAN_CODE_ADD_NEW_FORM_SR12011);
		
		// Prepare Request Body
		Date currentDate = new Date();
		th.go.excise.edbarcode.ws.client.sta.oxm.DataInformation wsDataInformation = new th.go.excise.edbarcode.ws.client.sta.oxm.DataInformation();
		wsDataInformation.setCusId(request.getSubmitOnlineHeader().getCusId());
		wsDataInformation.setCompanyId(request.getSubmitOnlineHeader().getCompanyId());
		wsDataInformation.setCompanyName(request.getSR12011Info().getTaxpayerInfo().getCompanyName());
		wsDataInformation.setTaxpayerId(request.getSubmitOnlineHeader().getTaxpayerId());
		wsDataInformation.setTaxpayerName(request.getSR12011Info().getTaxpayerInfo().getTaxpayerName());
		wsDataInformation.setExciseOfficeId(request.getSubmitOnlineHeader().getExciseOfficeId());
		wsDataInformation.setFormPeriodBeginDate(DateUtils.wsDateFormat.format(currentDate));
		wsDataInformation.setFormPeriodEndDate(DateUtils.wsDateFormat.format(currentDate));
		wsDataInformation.setFormPeriodResubmissionCode("");
		wsDataInformation.setPaymentExciseAmount(request.getSR12011Info().getSummaryInfo().getPaymentExciseAmount().toString());
		wsDataInformation.setPaymentMunicipalAmount(request.getSR12011Info().getSummaryInfo().getPaymentMunicipalAmount().toString());
		wsDataInformation.setPaymentFundHealthAmount(request.getSR12011Info().getSummaryInfo().getPaymentFundHealthAmount().toString());
		wsDataInformation.setPaymentFundTVAmount(request.getSR12011Info().getSummaryInfo().getPaymentFundTVAmount().toString());
		wsDataInformation.setPaymentFundSportAmount(request.getSR12011Info().getSummaryInfo().getPaymentFundSportAmount().toString());
		wsDataInformation.setInternetUniqueId(request.getSubmitOnlineHeader().getInternetUniqueId());
		
		// Prepare Xml CData
		XmlData xmlData = new XmlData();
		xmlData.setSubmitOnlineHeader(request.getSubmitOnlineHeader());
		xmlData.setSr12011Info(request.getSR12011Info());
		String formDataBinary = prepareXmlCData(xmlData);
		System.out.println(formDataBinary);
		
		th.go.excise.edbarcode.ws.client.sta.oxm.BinaryInformation wsBinaryInformation = new th.go.excise.edbarcode.ws.client.sta.oxm.BinaryInformation();
		wsBinaryInformation.setFormDataBinary(formDataBinary);
		
		th.go.excise.edbarcode.ws.client.sta.oxm.Body wsBody = new th.go.excise.edbarcode.ws.client.sta.oxm.Body();
		wsBody.setDataInformation(wsDataInformation);
		wsBody.setBinaryInformation(wsBinaryInformation);
		
		th.go.excise.edbarcode.ws.client.sta.oxm.StaBacRequest wsRequest = new th.go.excise.edbarcode.ws.client.sta.oxm.StaBacRequest();
		wsRequest.setHeader(wsHeader);
		wsRequest.setBody(wsBody);
		
		return wsRequest;
	}
	
	private String prepareXmlCData(XmlData xmlData) throws JAXBException, IOException {
		JAXBContext jaxbContext = JAXBContext.newInstance(XmlData.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		
		jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		
		StringWriter writer = new StringWriter();
		jaxbMarshaller.marshal(xmlData, writer);
		
		String xmlDataString = writer.getBuffer().toString();
		writer.close();
		
		return xmlDataString;
	}

	private EbarcodeSubmitOnlineResponse prepareWsResponse(th.go.excise.edbarcode.ws.client.sta.oxm.StaBacResponse wsResponse) {
		
		EbarcodeSubmitOnlineResponse response = new EbarcodeSubmitOnlineResponse();
		response.setReferenceNumber(wsResponse.getBody().getFormSubmissionId());
		
		return response;
	}

}
