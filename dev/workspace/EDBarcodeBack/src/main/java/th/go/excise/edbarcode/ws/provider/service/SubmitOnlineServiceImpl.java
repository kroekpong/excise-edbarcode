package th.go.excise.edbarcode.ws.provider.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import th.go.excise.edbarcode.ws.provider.oxm.EbarcodeSubmitOnlineRequest;
import th.go.excise.edbarcode.ws.provider.oxm.EbarcodeSubmitOnlineResponse;

@Service("submitOnlineService")
public class SubmitOnlineServiceImpl implements SubmitOnlineService {
	
	private static final Log logger = LogFactory.getLog(SubmitOnlineServiceImpl.class);

	@Override
	public EbarcodeSubmitOnlineResponse getResponse(EbarcodeSubmitOnlineRequest request) {
		EbarcodeSubmitOnlineResponse response = new EbarcodeSubmitOnlineResponse();
		return response;
	}

}
