package th.go.excise.edbarcode.ws.provider.endpoint;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import th.go.excise.edbarcode.common.constant.WebServiceConstant;
import th.go.excise.edbarcode.ws.provider.oxm.EbarcodeSubmitOnlineRequest;
import th.go.excise.edbarcode.ws.provider.oxm.EbarcodeSubmitOnlineResponse;
import th.go.excise.edbarcode.ws.provider.service.SubmitOnlineBackService;

@Endpoint
public class SubmitOnlineEndPoint {
	
	private static final Logger logger = LogManager.getLogger(SubmitOnlineEndPoint.class);
	
	@Autowired
	private SubmitOnlineBackService submitOnlineBackService;
	
	@PayloadRoot(localPart = "EbarcodeSubmitOnlineRequest", namespace = WebServiceConstant.NAMESPACE_URI)
	@ResponsePayload
	public EbarcodeSubmitOnlineResponse doEnpoint(@RequestPayload EbarcodeSubmitOnlineRequest request) {
		logger.info(" In doEndpoint SubmitOnlineRequest");
		
		EbarcodeSubmitOnlineResponse response = submitOnlineBackService.getResponse(request);
		
		return response;
	}
	
}
