package th.go.excise.edbarcode.ws.service;

import java.util.List;

import th.go.excise.edbarcode.ws.oxm.AlcoholTaxFormSummary;
import th.go.excise.edbarcode.ws.oxm.ProductTax;

public interface SubmitOnlineService {
	
	public String createTmpData(String licenseNo, List<ProductTax> productTaxList, AlcoholTaxFormSummary alcoholTaxFormSummary);
	
}
