package th.go.excise.edbarcode.ws.provider.service;

import th.go.excise.edbarcode.ws.provider.oxm.EbarcodeSendToBackendRequest;
import th.go.excise.edbarcode.ws.provider.oxm.EbarcodeSendToBackendResponse;

public interface SendToBackendService {
	
	public EbarcodeSendToBackendResponse getResponse(EbarcodeSendToBackendRequest request);
	
}
