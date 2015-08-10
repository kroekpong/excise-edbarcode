package th.go.excise.edbarcode.ws.client.sta.service;

import th.go.excise.edbarcode.ws.client.sta.oxm.StaBacRequest;
import th.go.excise.edbarcode.ws.client.sta.oxm.StaBacResponse;

public interface AddNewFormSR12011Service {
	
	public StaBacResponse doService(StaBacRequest request);
	
}
