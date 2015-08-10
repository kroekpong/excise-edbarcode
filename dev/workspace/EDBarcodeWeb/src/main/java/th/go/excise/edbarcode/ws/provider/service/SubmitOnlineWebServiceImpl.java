package th.go.excise.edbarcode.ws.provider.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import th.go.excise.edbarcode.ws.provider.oxm.EbarcodeSubmitOnlineRequest;
import th.go.excise.edbarcode.ws.provider.oxm.EbarcodeSubmitOnlineResponse;

@Service("submitOnlineService")
public class SubmitOnlineWebServiceImpl implements SubmitOnlineWebService {
	
	private static final Logger logger = LogManager.getLogger();

	@Override
	public EbarcodeSubmitOnlineResponse getResponse(EbarcodeSubmitOnlineRequest request) {
		EbarcodeSubmitOnlineResponse response = new EbarcodeSubmitOnlineResponse();
		return response;
	}

}
