package th.go.excise.edbarcode.report.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import th.go.excise.edbarcode.demo.service.ExciseDemoService;
import th.go.excise.edbarcode.report.domain.R018HDomain;
import th.go.excise.edbarcode.report.service.R018Service;

@Controller
public class R018AjaxController  {
	
	private static final Logger logger = LogManager.getLogger();
	
	@Autowired
	private R018Service r018Service;
	
	@RequestMapping(value = "/getR018", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody R018HDomain getR018(HttpServletRequest httpRequest) {
		logger.info("Inside getR018()");
 
		
		return r018Service.getMockReportData();
	}
	
	
	
	 
}
