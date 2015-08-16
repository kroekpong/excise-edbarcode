package th.go.excise.edbarcode.ws.client.pcc.insert0112.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

import th.go.excise.edbarcode.ws.client.pcc.insert0112.oxm.InsertPOSO0112Operation;
import th.go.excise.edbarcode.ws.client.pcc.insert0112.oxm.InsertPOSO0112OperationResponse;

@Service("insertPOSO0112OperationService")
public class InsertPOSO0112OperationServiceImpl implements InsertPOSO0112OperationService {
	
	private static final Logger logger = LogManager.getLogger(InsertPOSO0112OperationServiceImpl.class);

	@Autowired
	private WebServiceTemplate insertPOSO0112OperationWsTemplate;

	public InsertPOSO0112OperationResponse doService(InsertPOSO0112Operation insertPOSO0112OperationRequest) {
		InsertPOSO0112OperationResponse response = new InsertPOSO0112OperationResponse();
		logger.info(" In  InsertPOSO112OperationService.doService ###");
		try {

			logger.info(" #####################################  :" + insertPOSO0112OperationWsTemplate);

			response = (InsertPOSO0112OperationResponse) insertPOSO0112OperationWsTemplate.marshalSendAndReceive(insertPOSO0112OperationRequest);

			logger.info(" ##################################### After Call InsertPOSO0112OperationService response:  " + response);

		} catch (Exception ex) {
			logger.info(" ############## Error Call EAUTWSSupport :" + ex.getMessage());
			ex.printStackTrace();
		}

		return response;
	}

}