package th.go.excise.edbarcode.ws.provider.service;

import java.lang.reflect.InvocationTargetException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.edbarcode.common.constant.WebServiceConstant;
import th.go.excise.edbarcode.ws.client.barcode.service.SubmitOnlineBackService;
import th.go.excise.edbarcode.ws.provider.oxm.EbarcodeSubmitOnlineRequest;
import th.go.excise.edbarcode.ws.provider.oxm.EbarcodeSubmitOnlineResponse;

@Service("submitOnlineWebService")
public class SubmitOnlineWebServiceImpl implements SubmitOnlineWebService {
	
	private static final Logger logger = LogManager.getLogger(SubmitOnlineWebServiceImpl.class);
	
	@Autowired
	SubmitOnlineBackService submitOnlineBackService;
	
	@Override
	public EbarcodeSubmitOnlineResponse getResponse(EbarcodeSubmitOnlineRequest request) {
		logger.info("getResponse method");
		
		// Call Service
		EbarcodeSubmitOnlineResponse response = null;
		try {
			// Create WebService Request
			th.go.excise.edbarcode.ws.client.barcode.oxm.EbarcodeSubmitOnlineRequest wsRequest = prepareWsRequest(request);
			
			th.go.excise.edbarcode.ws.client.barcode.oxm.EbarcodeSubmitOnlineResponse wsResponse = submitOnlineBackService.doService(wsRequest);
			
			if (WebServiceConstant.STA_HEADER.RESULT_CODE_OK.equalsIgnoreCase(wsResponse.getSubmitOnlineStatus())) {
				// success
				response = prepareWsResponse(wsResponse);
			} else {
				// error
				response = new EbarcodeSubmitOnlineResponse();
				// TODO
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response = new EbarcodeSubmitOnlineResponse();
			// TODO
		}
		
		return response;
	}
	
	private th.go.excise.edbarcode.ws.client.barcode.oxm.EbarcodeSubmitOnlineRequest prepareWsRequest(EbarcodeSubmitOnlineRequest request) throws IllegalAccessException, InvocationTargetException {
		
		th.go.excise.edbarcode.ws.client.barcode.oxm.SubmitOnlineHeader wsSummitOnlineHeader = new th.go.excise.edbarcode.ws.client.barcode.oxm.SubmitOnlineHeader();
		// TODO
		
		th.go.excise.edbarcode.ws.client.barcode.oxm.SR12011Info wsSR12011Info = new th.go.excise.edbarcode.ws.client.barcode.oxm.SR12011Info();
		// TODO
		
		th.go.excise.edbarcode.ws.client.barcode.oxm.EbarcodeSubmitOnlineRequest wsRequest = new th.go.excise.edbarcode.ws.client.barcode.oxm.EbarcodeSubmitOnlineRequest();
		wsRequest.setSubmitOnlineHeader(wsSummitOnlineHeader);
		wsRequest.setSR12011Info(wsSR12011Info);
		
		return wsRequest;
	}
	
	private EbarcodeSubmitOnlineResponse prepareWsResponse(th.go.excise.edbarcode.ws.client.barcode.oxm.EbarcodeSubmitOnlineResponse wsResponse) throws IllegalAccessException, InvocationTargetException {
		
		EbarcodeSubmitOnlineResponse response = new EbarcodeSubmitOnlineResponse();
		response.setSubmitOnlineStatus(wsResponse.getSubmitOnlineStatus());
		response.setSubmitOnlineDesc(wsResponse.getSubmitOnlineDesc());
		response.setReferenceNumber(wsResponse.getReferenceNumber());
		
		return response;
	}

}
