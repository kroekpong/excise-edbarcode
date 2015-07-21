package th.go.excise.edbarcode.ws.provider.service;

import java.util.List;

import th.go.excise.edbarcode.ws.provider.oxm.AlcoholTaxFormSummary;
import th.go.excise.edbarcode.ws.provider.oxm.ProductTax;

public interface SubmitOnlineService {
	
	public String createTmpData(String licenseNo, List<ProductTax> productTaxList, AlcoholTaxFormSummary alcoholTaxFormSummary);
	
}
