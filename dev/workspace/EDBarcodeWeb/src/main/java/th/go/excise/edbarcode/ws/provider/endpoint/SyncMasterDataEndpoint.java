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
import th.go.excise.edbarcode.ws.provider.oxm.Entrepreneur;
import th.go.excise.edbarcode.ws.provider.oxm.SyncMasterDataRequest;
import th.go.excise.edbarcode.ws.provider.oxm.SyncMasterDataResponse;
import th.go.excise.edbarcode.ws.provider.service.SyncMasterDataService;

@Endpoint
public class SyncMasterDataEndpoint {
	
	private static final Logger logger = LogManager.getLogger();
	
	@Autowired
	private SyncMasterDataService syncMasterDataService;
	
	@PayloadRoot(localPart = "syncMasterDataRequest", namespace = WebServiceConstant.NAMESPACE_URI)
	@ResponsePayload
	public SyncMasterDataResponse doEndpoint(@RequestPayload SyncMasterDataRequest syncMasterDataRequest) throws DatatypeConfigurationException {
		logger.info(" In doEndpoint syncMasterDataRequest");
		
		String taxNo = syncMasterDataRequest.getTaxNo();
		logger.info(" licenseNo:" + taxNo);
		
		SyncMasterDataResponse response = new SyncMasterDataResponse();

		try {
			Entrepreneur entrepreneur = syncMasterDataService.getMasterData(taxNo);

			response.setStatus("0");
			response.setDescription("Success");
			response.setEntrepreneur(entrepreneur);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setStatus("1");
			response.setDescription("Failed");
		}
		
		return response;
	}
	
}