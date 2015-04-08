package th.go.excise.edbarcode.ws.endpoint;

import javax.xml.datatype.DatatypeConfigurationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

import th.go.excise.edbarcode.ws.oxm.Entrepreneur;
import th.go.excise.edbarcode.ws.oxm.SyncMasterDataRequest;
import th.go.excise.edbarcode.ws.oxm.SyncMasterDataResponse;
import th.go.excise.edbarcode.ws.service.SyncMasterDataService;

@Endpoint
public class SyncMasterDataEndpoint {
	
	private static final Logger logger = LogManager.getLogger();
	
	@Autowired
	private SyncMasterDataService syncMasterDataService;
	
	@PayloadRoot(localPart = "syncMasterDataRequest", namespace = "http://www.excise.go.th/xsd/barcode")
	public SyncMasterDataResponse doEndpoint(@RequestPayload SyncMasterDataRequest syncMasterDataRequest) throws DatatypeConfigurationException {
		logger.info(" In doEndpoint syncMasterDataRequest");
		
		String licenseNo = syncMasterDataRequest.getLicenseNo();
		logger.info(" licenseNo:" + licenseNo);
		
		SyncMasterDataResponse response = new SyncMasterDataResponse();

		try {
			Entrepreneur entrepreneur = syncMasterDataService.getMasterData(licenseNo);

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