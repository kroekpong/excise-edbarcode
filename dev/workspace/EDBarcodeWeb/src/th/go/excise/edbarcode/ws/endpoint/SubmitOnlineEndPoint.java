package th.go.excise.edbarcode.ws.endpoint;

import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

import th.go.excise.edbarcode.ws.oxm.AlcoholTaxFormSummary;
import th.go.excise.edbarcode.ws.oxm.ProductTax;
import th.go.excise.edbarcode.ws.oxm.SubmitOnlineRequest;
import th.go.excise.edbarcode.ws.oxm.SubmitOnlineResponse;
import th.go.excise.edbarcode.ws.service.SubmitOnlineService;

@Endpoint
public class SubmitOnlineEndPoint {
	
	private static final Logger logger = LogManager.getLogger();
	
	@Autowired
	private SubmitOnlineService submitOnlineService;
	
	@PayloadRoot(localPart = "submitOnlineRequest", namespace = "http://www.excise.go.th/xsd/barcode")
	public SubmitOnlineResponse doEnpoint(@RequestPayload SubmitOnlineRequest submitOnlineRequest) throws DatatypeConfigurationException {
		logger.info(" In doEndpoint submitOnlineRequest");
		
		String licenseNo = submitOnlineRequest.getAlcoholTaxForm().getLicenseNo();
		List<ProductTax> productTaxList = submitOnlineRequest.getAlcoholTaxForm().getProductTaxList();
		AlcoholTaxFormSummary alcoholTaxFormSummary = submitOnlineRequest.getAlcoholTaxForm().getAlcoholTaxFormSummary();
		
		logger.debug("licenseNo: " + licenseNo);
		if (!productTaxList.isEmpty()) {
			logger.debug("productTaxList.size(): " + productTaxList.size());
			for (ProductTax productTax : productTaxList) {
				logger.debug(ToStringBuilder.reflectionToString(productTax, ToStringStyle.MULTI_LINE_STYLE));
			}
		}
		logger.debug(ToStringBuilder.reflectionToString(alcoholTaxFormSummary, ToStringStyle.MULTI_LINE_STYLE));
		
		String referenceCode = submitOnlineService.createTmpData(licenseNo, productTaxList, alcoholTaxFormSummary);
		
		SubmitOnlineResponse response = new SubmitOnlineResponse();
		response.setStatus("0");
		response.setDescription("Submit Online Successful");
		response.setReferenceCode(referenceCode);
		
		return response;
	}
	
}
