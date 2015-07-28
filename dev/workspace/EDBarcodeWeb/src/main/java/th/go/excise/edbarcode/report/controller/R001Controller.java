package th.go.excise.edbarcode.report.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import th.go.excise.edbarcode.demo.bean.EntrepreneurDemo;
import th.go.excise.edbarcode.demo.bean.ExciseDataFromRefCode;
import th.go.excise.edbarcode.demo.bean.ProductDemo;
import th.go.excise.edbarcode.demo.service.ExciseDemoService;
import th.go.excise.edbarcode.report.domain.R001HDomain;

@Controller
public class R001Controller {
	
	private static final Logger logger = LogManager.getLogger();
	
	@Autowired
	private ExciseDemoService exciseDemoService;
	
	@RequestMapping(value = "/getR001", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody R001HDomain getR001(HttpServletRequest httpRequest) {
		logger.info("Inside getR001()");
 
		
		return ReportTest.getR001Test();
	}
	
	
	
	 
}
