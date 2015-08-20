package th.go.excise.edbarcode.ws.client.barcode.submitonline.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

import th.go.excise.edbarcode.ws.client.barcode.submitonline.oxm.EbarcodeSubmitOnlineRequest;
import th.go.excise.edbarcode.ws.client.barcode.submitonline.oxm.EbarcodeSubmitOnlineResponse;

@Service("submitOnlineBackService")
public class SubmitOnlineBackServiceImpl implements SubmitOnlineBackService {

	private static final Logger logger = LogManager.getLogger(SubmitOnlineBackServiceImpl.class);

	@Autowired
	private WebServiceTemplate submitOnlineWsTemplate;

	@Override
	public EbarcodeSubmitOnlineResponse doService(EbarcodeSubmitOnlineRequest request) {
		
		logger.info(" ########################### Before Call submitOnlineWsTemplate");
		
		// Call Web Service
		//EbarcodeSubmitOnlineResponse response = getDummyResponse();
		EbarcodeSubmitOnlineResponse response = (EbarcodeSubmitOnlineResponse) submitOnlineWsTemplate.marshalSendAndReceive(request);
		
		logger.info(" ########################### After Call  submitOnlineWsTemplate");
		
		return response;
	}
	
	private EbarcodeSubmitOnlineResponse getDummyResponse() {
		EbarcodeSubmitOnlineResponse response = new EbarcodeSubmitOnlineResponse();
		response.setSubmitOnlineStatus("OK");
		response.setSubmitOnlineDesc("Success");
		response.setReferenceNumber("1120989880_TEST");
		return response;
	}

}
