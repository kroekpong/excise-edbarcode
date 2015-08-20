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
import th.go.excise.edbarcode.ws.provider.oxm.EbarcodeSendFormSR12011Request;
import th.go.excise.edbarcode.ws.provider.oxm.EbarcodeSendFormSR12011Response;
import th.go.excise.edbarcode.ws.provider.service.SendFormSR12011Service;

@Endpoint
public class SendFormSR12011Endpoint {
	
	private static final Logger logger = LogManager.getLogger(SendFormSR12011Endpoint.class);
	
	@Autowired
	private SendFormSR12011Service sendFormSR12011Service;
	
	@PayloadRoot(localPart = "EbarcodeSendFormSR12011Request", namespace = WebServiceConstant.NAMESPACE_URI)
	@ResponsePayload
	public EbarcodeSendFormSR12011Response doEnpoint(@RequestPayload EbarcodeSendFormSR12011Request request) {
		logger.info(" In doEndpoint SendFormSR12011Request");
		
		EbarcodeSendFormSR12011Response response = sendFormSR12011Service.getResponse(request);
		
		return response;
	}
}
