package th.go.excise.edbarcode.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import th.go.excise.edbarcode.demo.bean.EntrepreneurDemo;
import th.go.excise.edbarcode.demo.bean.ExciseDataFromRefCode;
import th.go.excise.edbarcode.demo.bean.ProductDemo;
import th.go.excise.edbarcode.demo.service.ExciseDemoService;

@Controller
public class ExciseDemoAjaxController {
	
	private static final Log logger = LogFactory.getLog(ExciseDemoAjaxController.class);
	
	@Autowired
	private ExciseDemoService exciseDemoService;
	
	@RequestMapping(value = "/getEntrepreneurInfo", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody EntrepreneurDemo getEntrepreneurInfo(HttpServletRequest httpRequest) {
		logger.info("Inside getEntrepreneurInfo()");
		
		String licenseNo = httpRequest.getParameter("licenseNo");
		logger.info("licenseNo: " + licenseNo);
		
		EntrepreneurDemo entDemo = exciseDemoService.getEntrepreneurDetail(licenseNo);
		logger.debug(ToStringBuilder.reflectionToString(entDemo, ToStringStyle.MULTI_LINE_STYLE));
		
		return entDemo;
	}
	
	@RequestMapping(value = "/getProductInfo", method = RequestMethod.GET, headers = "Accept=application/json")
	public ProductDemo getProductInfo(HttpServletRequest httpRequest) {
		logger.info("Inside getProductInfo()");
		
		String productCode = httpRequest.getParameter("productCode");
		logger.info("productCode: " + productCode);
		
		ProductDemo prdDemo = exciseDemoService.getProductDetail(productCode);
		logger.debug(ToStringBuilder.reflectionToString(prdDemo, ToStringStyle.MULTI_LINE_STYLE));
		
		return prdDemo;
	}
	
	@RequestMapping(value = "/getDataFromRefCode", method = RequestMethod.GET, headers = "Accept=application/json")
	public ExciseDataFromRefCode getDataFromRefCode(HttpServletRequest httpRequest) {
		logger.info("Inside getDataFromRefCode()");
		
		String referenceCode = httpRequest.getParameter("referenceCode");
		logger.info("referenceCode: " + referenceCode);
		
		ExciseDataFromRefCode data = exciseDemoService.getDataFromRefCode(referenceCode);
		logger.debug(ToStringBuilder.reflectionToString(data, ToStringStyle.MULTI_LINE_STYLE));
		
		return data;
	}
	
}
