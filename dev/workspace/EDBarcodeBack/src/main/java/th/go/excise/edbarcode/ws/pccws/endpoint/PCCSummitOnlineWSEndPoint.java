package th.go.excise.edbarcode.ws.pccws.endpoint;

import javax.xml.datatype.DatatypeConfigurationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import th.go.excise.edbarcode.common.constant.WebServiceConstant;
import th.go.excise.edbarcode.ws.pccws.oxm.PCCRequest;
import th.go.excise.edbarcode.ws.pccws.oxm.PCCResponse;
import th.go.excise.edbarcode.ws.provider.service.SubmitOnlineService;

@Endpoint
public class PCCSummitOnlineWSEndPoint {
	
	private static final Log logger = LogFactory.getLog(PCCSummitOnlineWSEndPoint.class);
	
	@Autowired
	private SubmitOnlineService submitOnlineService;
	
	@PayloadRoot(localPart = "PCCRequest", namespace = WebServiceConstant.NAMESPACE_URI)
	@ResponsePayload
	public PCCResponse doEnpoint(@RequestPayload PCCRequest request) throws DatatypeConfigurationException {
		logger.info(" In doEndpoint submitOnlineRequest");
		
		PCCResponse response = null;

		try {
			response = new PCCResponse();
			// Status Code & Description
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response = new PCCResponse();
			// Status Code & Description
		}
		
		return response;
	}
	
}
