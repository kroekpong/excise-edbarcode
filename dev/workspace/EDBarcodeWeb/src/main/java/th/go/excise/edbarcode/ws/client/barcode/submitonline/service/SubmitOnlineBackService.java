package th.go.excise.edbarcode.ws.client.barcode.submitonline.service;

import th.go.excise.edbarcode.ws.client.barcode.submitonline.oxm.EbarcodeSubmitOnlineRequest;
import th.go.excise.edbarcode.ws.client.barcode.submitonline.oxm.EbarcodeSubmitOnlineResponse;

public interface SubmitOnlineBackService {
	
	public EbarcodeSubmitOnlineResponse doService(EbarcodeSubmitOnlineRequest request);
	
}
