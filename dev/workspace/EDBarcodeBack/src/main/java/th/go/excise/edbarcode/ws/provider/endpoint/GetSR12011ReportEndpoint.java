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
import th.go.excise.edbarcode.ws.provider.oxm.EbarcodeGetSR12011ReportRequest;
import th.go.excise.edbarcode.ws.provider.oxm.EbarcodeGetSR12011ReportResponse;
import th.go.excise.edbarcode.ws.provider.service.GetSR12011ReportService;

@Endpoint
public class GetSR12011ReportEndpoint {
	
	private static final Logger logger = LogManager.getLogger(GetSR12011ReportEndpoint.class);
	
	@Autowired
	private GetSR12011ReportService getSR12011ReportService;
	
	@PayloadRoot(localPart = "EbarcodeGetSR12011ReportRequest", namespace = WebServiceConstant.NAMESPACE_URI)
	@ResponsePayload
	public EbarcodeGetSR12011ReportResponse doEndpoint(@RequestPayload EbarcodeGetSR12011ReportRequest request) throws DatatypeConfigurationException {
		logger.info(" In doEndpoint GetSR12011ReportRequest");
		
		EbarcodeGetSR12011ReportResponse response = getSR12011ReportService.getResponse(request);
		
		return response;
	}
	
}
