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
import th.go.excise.edbarcode.ws.provider.oxm.EbarcodeSubmitOnlineRequest;
import th.go.excise.edbarcode.ws.provider.oxm.EbarcodeSubmitOnlineResponse;
import th.go.excise.edbarcode.ws.provider.service.SubmitOnlineWebService;

@Endpoint
public class SubmitOnlineEndPoint {
	
	private static final Logger logger = LogManager.getLogger();
	
	@Autowired
	private SubmitOnlineWebService submitOnlineWebService;
	
	@PayloadRoot(localPart = "EbarcodeSubmitOnlineRequest", namespace = WebServiceConstant.NAMESPACE_URI)
	@ResponsePayload
	public EbarcodeSubmitOnlineResponse doEnpoint(@RequestPayload EbarcodeSubmitOnlineRequest request) throws DatatypeConfigurationException {
		logger.info(" In doEndpoint submitOnlineRequest");
		
		EbarcodeSubmitOnlineResponse response = null;

		try {
			response = submitOnlineWebService.getResponse(request);
			// Status Code & Description
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response = new EbarcodeSubmitOnlineResponse();
			// Status Code & Description
		}
		
		return response;
	}
	
}
