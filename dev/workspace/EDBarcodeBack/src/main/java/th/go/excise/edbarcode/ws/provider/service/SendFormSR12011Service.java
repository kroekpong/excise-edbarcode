package th.go.excise.edbarcode.ws.provider.service;

import th.go.excise.edbarcode.ws.provider.oxm.EbarcodeSendFormSR12011Request;
import th.go.excise.edbarcode.ws.provider.oxm.EbarcodeSendFormSR12011Response;

public interface SendFormSR12011Service {
	
	public EbarcodeSendFormSR12011Response getResponse(EbarcodeSendFormSR12011Request request);
	
}
