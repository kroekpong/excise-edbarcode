package th.go.excise.edbarcode.ws.endpoint;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

import th.go.excise.edbarcode.ws.oxm.Entrepreneur;
import th.go.excise.edbarcode.ws.oxm.Product;
import th.go.excise.edbarcode.ws.oxm.SyncMasterDataRequest;
import th.go.excise.edbarcode.ws.oxm.SyncMasterDataResponse;

 
@Endpoint
public class SyncMasterDataEndpoint {   
	
	private static final Logger logger = LogManager.getLogger();
    
 
	@PayloadRoot(localPart = "syncMasterDataRequest", namespace = "http://www.excise.go.th/xsd/barcode")
    public  SyncMasterDataResponse  doEnpoint( @RequestPayload SyncMasterDataRequest syncMasterDataRequest) {    	 
    	  logger.info(" In doEnpoint loginRequest" );
    	  
     
    	  
    	  
    	  logger.info(" licenseNo:" +syncMasterDataRequest.getLicenseNo());
    	  
    	  
    	  
    	  SyncMasterDataResponse response  =	new SyncMasterDataResponse();
    	  
    	  
    	  
    	  Entrepreneur entrepreneur = new Entrepreneur();
    	  
    	  entrepreneur.setLicenseNo("XXX");
    	  
     
    	  
    	  Product p1 = new Product();
    	  p1.setProductName("Test 1");
    	  
    	  Product p2 = new Product();
    	  p2.setProductName("Test 2");
    	  
     
    	  
    	  
    	  entrepreneur.getProductList().add(p1);
    	  entrepreneur.getProductList().add(p2);
    	  
    	  
    	  response.setEntrepreneur(entrepreneur);
     
    	 
    	 
    	return response;
    }
    
    
}