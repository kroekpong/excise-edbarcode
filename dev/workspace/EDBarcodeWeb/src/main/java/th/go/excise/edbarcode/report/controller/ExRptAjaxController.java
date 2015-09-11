package th.go.excise.edbarcode.report.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.framework.common.bean.ServiceResult;
import th.go.excise.edbarcode.report.domain.ExRptDomain;
import th.go.excise.edbarcode.report.service.ExRptService;

@Controller
public class ExRptAjaxController {
	
	private static final Logger logger = LogManager.getLogger();
	
	@Autowired
	private ExRptService exRptService;
	
	@RequestMapping(value = "/getExampleReport", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody ServiceResult<List<ExRptDomain>> getExampleReport(HttpServletRequest httpRequest) {
		logger.info("Inside getExampleReport()");
 
		ServiceResult<List<ExRptDomain>> serviceResult = exRptService.getMockReportData();
		
		return serviceResult;
	}
	
}
