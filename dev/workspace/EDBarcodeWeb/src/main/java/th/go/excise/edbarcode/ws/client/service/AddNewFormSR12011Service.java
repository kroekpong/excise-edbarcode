package th.go.excise.edbarcode.ws.client.service;

import th.go.excise.edbarcode.ws.client.oxm.StaBacRequest;
import th.go.excise.edbarcode.ws.client.oxm.StaBacResponse;

public interface AddNewFormSR12011Service {
	
	public StaBacResponse doService(StaBacRequest request);
	
}
