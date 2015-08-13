package th.go.excise.edbarcode.ws.provider.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.edbarcode.common.constant.WebServiceConstant;
import th.go.excise.edbarcode.ws.client.pcc.insert0112.client.InsertPOSO0112OperationService;
import th.go.excise.edbarcode.ws.client.pcc.insert0112.oxm.InsertPOSO0112Operation;
import th.go.excise.edbarcode.ws.client.pcc.insert0112.oxm.InsertPOSO0112OperationResponse;
import th.go.excise.edbarcode.ws.provider.oxm.EbarcodeSendToBackendRequest;
import th.go.excise.edbarcode.ws.provider.oxm.EbarcodeSendToBackendResponse;

@Service("sendToBackendService")
public class SendToBackendServiceImpl implements SendToBackendService {
	
	private static final Logger logger = LogManager.getLogger(SendToBackendServiceImpl.class);
	
	@Autowired
	private InsertPOSO0112OperationService insertPOSO0112OperationService;
	
	@Override
	public EbarcodeSendToBackendResponse getResponse(EbarcodeSendToBackendRequest request) {
		logger.info("getResponse method");
		
		EbarcodeSendToBackendResponse response = null;
		
		try {
			// Create WebService Request
			InsertPOSO0112Operation wsRequest = prepareWsRequest(request);
			
			// Call Service
			InsertPOSO0112OperationResponse wsResponse = insertPOSO0112OperationService.doService(wsRequest);
			
			if (WebServiceConstant.STATUS_CODE.OK.equalsIgnoreCase(wsResponse.getReturn().getReturnCode())) {
				// success
				response = new EbarcodeSendToBackendResponse();
				response.setSendTobackendStatus(wsResponse.getReturn().getReturnCode());
				response.setSendTobackendDesc(wsResponse.getReturn().getReturnDesc());
			} else {
				// error
				response = new EbarcodeSendToBackendResponse();
				response.setSendTobackendStatus(wsResponse.getReturn().getReturnCode());
				response.setSendTobackendDesc(wsResponse.getReturn().getReturnDesc());
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

	private InsertPOSO0112Operation prepareWsRequest(EbarcodeSendToBackendRequest request) {
		InsertPOSO0112Operation wsRequest = new InsertPOSO0112Operation();
		return wsRequest;
	}
}
