package th.go.excise.edbarcode.ws.provider.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import th.go.excise.edbarcode.ws.provider.oxm.EbarcodeSubmitOnlineRequest;
import th.go.excise.edbarcode.ws.provider.oxm.EbarcodeSubmitOnlineResponse;

@Service("submitOnlineBackService")
public class SubmitOnlineBackServiceImpl implements SubmitOnlineBackService {
	
	private static final Logger logger = LogManager.getLogger(SubmitOnlineBackServiceImpl.class);

	@Override
	public EbarcodeSubmitOnlineResponse getResponse(EbarcodeSubmitOnlineRequest request) {
		EbarcodeSubmitOnlineResponse response = new EbarcodeSubmitOnlineResponse();
		return response;
	}

}
