package th.go.excise.edbarcode.ws.client.service;

import org.springframework.stereotype.Service;

import th.go.excise.edbarcode.ws.client.oxm.StaBacRequest;
import th.go.excise.edbarcode.ws.client.oxm.StaBacResponse;

@Service("addNewFormSR12011Service")
public class AddNewFormSR12011ServiceImpl implements AddNewFormSR12011Service {

	@Override
	public StaBacResponse doService(StaBacRequest request) {
		StaBacResponse response = new StaBacResponse();
		return response;
	}

}
