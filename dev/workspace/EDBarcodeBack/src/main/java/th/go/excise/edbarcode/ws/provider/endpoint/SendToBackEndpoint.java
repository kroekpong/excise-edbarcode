package th.go.excise.edbarcode.ws.provider.endpoint;

import javax.xml.datatype.DatatypeConfigurationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import th.go.excise.edbarcode.common.constant.WebServiceConstant;
import th.go.excise.edbarcode.ws.provider.oxm.EbarcodeSendToBackendRequest;
import th.go.excise.edbarcode.ws.provider.oxm.EbarcodeSendToBackendResponse;
import th.go.excise.edbarcode.ws.provider.service.SendToBackendService;

@Endpoint
public class SendToBackEndpoint {
	
	private static final Logger logger = LogManager.getLogger(SendToBackEndpoint.class);
	
	@Autowired
	private SendToBackendService sendToBackendService;
	
	@PayloadRoot(localPart = "EbarcodeSendToBackendRequest", namespace = WebServiceConstant.NAMESPACE_URI)
	@ResponsePayload
	public EbarcodeSendToBackendResponse doEnpoint(@RequestPayload EbarcodeSendToBackendRequest request) throws DatatypeConfigurationException {
		logger.info(" In doEndpoint SendToBackendRequest");
		
		EbarcodeSendToBackendResponse response = null;

		try {
			response = sendToBackendService.getResponse(request);
			// Status Code & Description
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response = new EbarcodeSendToBackendResponse();
			// Status Code & Description
		}
		
		return response;
	}
}
