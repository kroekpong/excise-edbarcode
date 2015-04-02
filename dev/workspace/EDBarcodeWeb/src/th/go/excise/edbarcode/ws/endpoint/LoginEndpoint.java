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
	public LoginResponse doEndpoint(@RequestPayload LoginRequest loginRequest) {
		logger.info(" In doEndpoint loginRequest");

		String userName = loginRequest.getUserName();
		String password = loginRequest.getPassword();

		logger.info(" User Name:" + loginRequest.getUserName());
		logger.info(" Password:" + loginRequest.getPassword());

		LoginResponse response = new LoginResponse();
		if (userName != null && userName != null) {
			if ("001".equals(userName) && "password".equals(password)) {
				response.setStatus("0");
				response.setDescription("Success");
			} else if ("002".equals(userName) && "password".equals(password)) {
				response.setStatus("0");
				response.setDescription("Success");
			} else {
				response.setStatus("1");
				response.setDescription("Invalid User Name or Password");
			}
		} else {
			response.setStatus("1");
			response.setDescription("Invalid User Name or Password");
		}

		return response;
	}

}