package th.go.excise.edbarcode.ws.client.pcc.service;

import th.go.excise.edbarcode.ws.client.pcc.oxm.InsertSR12011OperationRequest;
import th.go.excise.edbarcode.ws.client.pcc.oxm.InsertSR12011OperationResponse;

public interface InsertSR12011OperationService {
	
	public InsertSR12011OperationResponse doService(InsertSR12011OperationRequest request);
	
}
