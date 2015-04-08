package th.go.excise.edbarcode.demo.service;

import th.go.excise.edbarcode.demo.bean.EntrepreneurDemo;
import th.go.excise.edbarcode.demo.bean.ExciseDataFromRefCode;
import th.go.excise.edbarcode.demo.bean.ProductDemo;

public interface ExciseDemoService {
	
	public EntrepreneurDemo getEntrepreneurDetail(String licenseNo);
	
	public ProductDemo getProductDetail(String productCode);
	
	public ExciseDataFromRefCode getDataFromRefCode(String referenceCode);
	
}
