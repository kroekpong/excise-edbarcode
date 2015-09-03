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
import th.go.excise.edbarcode.report.domain.R010HDomain;

@Controller
public class R010Controller {
	
	private static final Logger logger = LogManager.getLogger();
	
	@Autowired
	private ExciseDemoService exciseDemoService;
	
	@RequestMapping(value = "/getR010", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody R010HDomain getR001(HttpServletRequest httpRequest) {
		logger.info("Inside getR010()");
 
		
		return Report10Test.getR010Test();
	}
	
	
	
	 
}
