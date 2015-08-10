package th.go.excise.edbarcode.ws.provider.service;

import th.go.excise.edbarcode.ws.provider.oxm.EbarcodeSubmitOnlineRequest;
import th.go.excise.edbarcode.ws.provider.oxm.EbarcodeSubmitOnlineResponse;

public interface SubmitOnlineBackService {
	
	public EbarcodeSubmitOnlineResponse getResponse(EbarcodeSubmitOnlineRequest request);
	
}
