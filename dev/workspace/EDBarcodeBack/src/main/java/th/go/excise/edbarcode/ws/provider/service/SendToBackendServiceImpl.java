package th.go.excise.edbarcode.ws.provider.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.edbarcode.common.constant.WebServiceConstant;
import th.go.excise.edbarcode.ws.client.pcc.oxm.InsertSR12011OperationRequest;
import th.go.excise.edbarcode.ws.client.pcc.service.InsertSR12011OperationService;
import th.go.excise.edbarcode.ws.provider.oxm.EbarcodeSendToBackendRequest;
import th.go.excise.edbarcode.ws.provider.oxm.EbarcodeSendToBackendResponse;

@Service("sendToBackendService")
public class SendToBackendServiceImpl implements SendToBackendService {
	
	private static final Logger logger = LogManager.getLogger(SendToBackendServiceImpl.class);
	
	@Autowired
	private InsertSR12011OperationService insertSR12011OperationService;
	
	@Override
	public EbarcodeSendToBackendResponse getResponse(EbarcodeSendToBackendRequest request) {
		logger.info("getResponse method");
		
		EbarcodeSendToBackendResponse response = null;
		
		try {
			// Create WebService Request
			th.go.excise.edbarcode.ws.client.pcc.oxm.InsertSR12011OperationRequest wsRequest = prepareWsRequest(request);
			
			// Call Service
			th.go.excise.edbarcode.ws.client.pcc.oxm.InsertSR12011OperationResponse wsResponse = insertSR12011OperationService.doService(wsRequest);
			
			if (WebServiceConstant.STATUS_CODE.OK.equalsIgnoreCase(wsResponse.getBody().getReturnCode())) {
				// success
				response = new EbarcodeSendToBackendResponse();
				response.setSendTobackendStatus(wsResponse.getBody().getReturnCode());
				response.setSendTobackendDesc(wsResponse.getBody().getReturnDesc());
			} else {
				// error
				response = new EbarcodeSendToBackendResponse();
				response.setSendTobackendStatus(wsResponse.getBody().getReturnCode());
				response.setSendTobackendDesc(wsResponse.getBody().getReturnDesc());
				logger.error("Call SendToBackendService Failed: {}: {}", response.getSendTobackendStatus(), response.getSendTobackendDesc());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response = new EbarcodeSendToBackendResponse();
			response.setSendTobackendStatus(WebServiceConstant.STATUS_CODE.ERROR);
			response.setSendTobackendDesc(e.getMessage());
		}
		
		return response;
	}

	private InsertSR12011OperationRequest prepareWsRequest(EbarcodeSendToBackendRequest request) {
		th.go.excise.edbarcode.ws.client.pcc.oxm.InsertSR12011OperationRequest wsRequest = new th.go.excise.edbarcode.ws.client.pcc.oxm.InsertSR12011OperationRequest();
		return wsRequest;
	}
}
