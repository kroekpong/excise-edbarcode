package th.go.excise.edbarcode.ws.provider.endpoint;

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
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import th.go.excise.edbarcode.common.constant.WebServiceConstant;
import th.go.excise.edbarcode.ws.provider.oxm.AlcoholTaxFormSummary;
import th.go.excise.edbarcode.ws.provider.oxm.ProductTax;
import th.go.excise.edbarcode.ws.provider.oxm.SubmitOnlineRequest;
import th.go.excise.edbarcode.ws.provider.oxm.SubmitOnlineResponse;
import th.go.excise.edbarcode.ws.provider.service.SubmitOnlineService;

@Endpoint
public class SubmitOnlineEndPoint {
	
	private static final Logger logger = LogManager.getLogger();
	
	@Autowired
	private SubmitOnlineService submitOnlineService;
	
	@PayloadRoot(localPart = "submitOnlineRequest", namespace = WebServiceConstant.NAMESPACE_URI)
	@ResponsePayload
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
