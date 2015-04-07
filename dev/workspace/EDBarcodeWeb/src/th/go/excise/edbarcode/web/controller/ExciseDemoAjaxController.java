package th.go.excise.edbarcode.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import th.go.excise.edbarcode.common.bean.EntrepreneurDemo;
import th.go.excise.edbarcode.common.bean.ProductDemo;

@RestController
public class ExciseDemoAjaxController {
	
	private static final Logger logger = LogManager.getLogger();
	
	@RequestMapping(value = "/getEntrepreneurInfo", method = RequestMethod.GET, headers = "Accept=application/json")
	public EntrepreneurDemo getEntrepreneurInfo(HttpServletRequest httpRequest) {
		logger.info("Inside getEntrepreneurInfo()");
		
		String licenseNo = httpRequest.getParameter("licenseNo");
		logger.info("licenseNo: " + licenseNo);
		
		EntrepreneurDemo entDemo = new EntrepreneurDemo();
		
		entDemo.setLicenseNo("2558/70605817600002");
		entDemo.setLicenseAllowedName("บริษัท สยามไวเนอรี่ จำกัด");
		entDemo.setFactoryName("บริษัท สยามไวเนอรี่ จำกัด");
		entDemo.setLicenseStartDate("01/01/2558");
		entDemo.setLicenseEndDate("31/12/2558");
		entDemo.setTaxNo("3-1015-1763-7");
		entDemo.setFactoryAddress("9/2 หมู่ 3 ต.บางโทรัด อ.เมืองสมุทรสาคร จ.สมุทรสาคร 74000");
		
		return entDemo;
	}
	
	@RequestMapping(value = "/getProductInfo", method = RequestMethod.GET, headers = "Accept=application/json")
	public ProductDemo getProductInfo(HttpServletRequest httpRequest) {
		logger.info("Inside getProductInfo()");
		
		String productCode = httpRequest.getParameter("productCode");
		logger.info("productCode: " + productCode);
		
		ProductDemo prdDemo = new ProductDemo();
		
		prdDemo.setProductGroup("สุราแช่อื่นๆ");
		prdDemo.setProductName("สุราแช่ผลไม้ Berri Estates Five Star White");
		prdDemo.setDegree("12.50");
		prdDemo.setSize("3.000");
		
//		prdDemo.setProductGroup("สุราแช่อื่นๆ");
//		prdDemo.setProductName("สุราแช่ผลไม้ Finca de Malpica Smooth Red");
//		prdDemo.setDegree("13.50");
//		prdDemo.setSize("0.750");
		
		return prdDemo;
	}
	
}
