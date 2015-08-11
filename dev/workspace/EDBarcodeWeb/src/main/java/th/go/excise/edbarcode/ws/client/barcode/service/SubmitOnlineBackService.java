package th.go.excise.edbarcode.ws.client.barcode.service;

import th.go.excise.edbarcode.ws.client.barcode.oxm.EbarcodeSubmitOnlineRequest;
import th.go.excise.edbarcode.ws.client.barcode.oxm.EbarcodeSubmitOnlineResponse;

public interface SubmitOnlineBackService {
	
	public EbarcodeSubmitOnlineResponse doService(EbarcodeSubmitOnlineRequest request);
	
}
