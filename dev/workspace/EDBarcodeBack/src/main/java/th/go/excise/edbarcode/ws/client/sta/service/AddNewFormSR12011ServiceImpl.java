package th.go.excise.edbarcode.ws.client.sta.service;

import org.springframework.stereotype.Service;

import th.go.excise.edbarcode.ws.client.sta.oxm.StaBacRequest;
import th.go.excise.edbarcode.ws.client.sta.oxm.StaBacResponse;

@Service("addNewFormSR12011Service")
public class AddNewFormSR12011ServiceImpl implements AddNewFormSR12011Service {

	@Override
	public StaBacResponse doService(StaBacRequest request) {
		StaBacResponse response = new StaBacResponse();
		return response;
	}

}
