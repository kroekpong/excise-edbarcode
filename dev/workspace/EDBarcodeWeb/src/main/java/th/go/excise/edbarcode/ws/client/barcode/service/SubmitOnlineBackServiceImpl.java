package th.go.excise.edbarcode.ws.client.barcode.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

import th.go.excise.edbarcode.ws.client.barcode.oxm.EbarcodeSubmitOnlineRequest;
import th.go.excise.edbarcode.ws.client.barcode.oxm.EbarcodeSubmitOnlineResponse;

@Service("submitOnlineBackService")
public class SubmitOnlineBackServiceImpl implements SubmitOnlineBackService {

	private static final Logger logger = LogManager.getLogger(SubmitOnlineBackServiceImpl.class);

	@Autowired
	private WebServiceTemplate submitOnlineWsTemplate;

	@Override
	public EbarcodeSubmitOnlineResponse doService(EbarcodeSubmitOnlineRequest request) {
		
		logger.info(" ########################### Before Call submitOnlineWsTemplate");
		
		// Call Web Service
		EbarcodeSubmitOnlineResponse response = (EbarcodeSubmitOnlineResponse) submitOnlineWsTemplate.marshalSendAndReceive(request);
		
		logger.info(" ########################### After Call  submitOnlineWsTemplate");
		
		return response;
	}

}
