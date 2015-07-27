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
import th.go.excise.edbarcode.ws.provider.oxm.EbarcodeSyncMasterDataRequest;
import th.go.excise.edbarcode.ws.provider.oxm.EbarcodeSyncMasterDataResponse;
import th.go.excise.edbarcode.ws.provider.service.SyncMasterDataService;

@Endpoint
public class SyncMasterDataEndpoint {
	
	private static final Logger logger = LogManager.getLogger();
	
	@Autowired
	private SyncMasterDataService syncMasterDataService;
	
	@PayloadRoot(localPart = "EbarcodeSyncMasterDataRequest", namespace = WebServiceConstant.NAMESPACE_URI)
	@ResponsePayload
	public EbarcodeSyncMasterDataResponse doEndpoint(@RequestPayload EbarcodeSyncMasterDataRequest request) throws DatatypeConfigurationException {
		logger.info(" In doEndpoint syncMasterDataRequest");
		
		EbarcodeSyncMasterDataResponse response = null;

		try {
			response = syncMasterDataService.getResponse(request);
			// Status Code & Description
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response = new EbarcodeSyncMasterDataResponse();
			// Status Code & Description
		}
		
		return response;
	}
	
}