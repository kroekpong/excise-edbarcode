package th.go.excise.edbarcode.ws.endpoint;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

import th.go.excise.edbarcode.ws.oxm.LoginRequest;
import th.go.excise.edbarcode.ws.oxm.LoginResponse;

 
@Endpoint
public class LoginEndpoint {   
	
	private static final Logger logger = LogManager.getLogger();
    
 
	@PayloadRoot(localPart = "loginRequest", namespace = "http://www.excise.go.th/xsd/barcode")
    public  LoginResponse  doEnpoint( @RequestPayload LoginRequest loginRequest) {    	 
    	  logger.info(" In doEnpoint loginRequest" );
    	  
    	  logger.info(" User Name:" +loginRequest.getUserName());
    	  logger.info(" Password:" +loginRequest.getPassword());
    	  
    	  
    	  
    	  LoginResponse response  =	new LoginResponse();
    	  response.setStatus("0");
    	  response.setDescription("Success");     	 
    	return response;
    }
    
    
}