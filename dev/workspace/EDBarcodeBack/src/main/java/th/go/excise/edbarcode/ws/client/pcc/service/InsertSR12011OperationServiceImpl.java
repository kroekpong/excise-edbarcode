package th.go.excise.edbarcode.ws.client.pcc.service;

import org.springframework.stereotype.Service;

import th.go.excise.edbarcode.ws.client.pcc.oxm.InsertSR12011OperationRequest;
import th.go.excise.edbarcode.ws.client.pcc.oxm.InsertSR12011OperationResponse;
import th.go.excise.edbarcode.ws.client.pcc.oxm.ResponseBody;
import th.go.excise.edbarcode.ws.client.pcc.oxm.ResponseHeader;

@Service("insertSR12011OperationService")
public class InsertSR12011OperationServiceImpl implements InsertSR12011OperationService {

	@Override
	public InsertSR12011OperationResponse doService(InsertSR12011OperationRequest request) {
		
		// Mock Data
		ResponseHeader header = new ResponseHeader();
		header.setServiceID("InsertSR12011Operation");
		header.setSystemID("PCC");
		header.setIPAddress("192.168.3.19");
		
		ResponseBody body = new ResponseBody();
		body.setReturnCode("0");
		body.setReturnDesc("Success");
		
		InsertSR12011OperationResponse response = new InsertSR12011OperationResponse();
		response.setHeader(header);
		response.setBody(body);
		
		return response;
	}

}
