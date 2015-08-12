package th.go.excise.edbarcode.ws.provider.service;

import org.springframework.stereotype.Service;

import th.go.excise.edbarcode.ws.provider.oxm.EbarcodeSendToBackendRequest;
import th.go.excise.edbarcode.ws.provider.oxm.EbarcodeSendToBackendResponse;

@Service("sendToBackendService")
public class SendToBackendServiceImpl implements SendToBackendService {

	@Override
	public EbarcodeSendToBackendResponse getResponse(EbarcodeSendToBackendRequest request) {
		EbarcodeSendToBackendResponse response = new EbarcodeSendToBackendResponse();
		return response;
	}
}
