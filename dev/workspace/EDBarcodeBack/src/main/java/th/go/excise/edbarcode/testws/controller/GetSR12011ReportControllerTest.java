package th.go.excise.edbarcode.testws.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import th.go.excise.edbarcode.testws.service.GetSR12011ReportServiceTest;

@Controller
@RequestMapping(value = "/testws/getsr12011report.htm")
public class GetSR12011ReportControllerTest {
	
	private static final Logger logger = LogManager.getLogger(GetSR12011ReportControllerTest.class);
	
	@Autowired
	GetSR12011ReportServiceTest getSRReportServiceTest;

	@RequestMapping( method = RequestMethod.GET)
	public ModelAndView welcome(HttpServletRequest httpRequest) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("tesGetSrReportws");
//		mav.addObject("strxml", "Stringngngngngngngngngngngn/n/nStringngngngngngngn/nasdasd");
		mav.addObject("strurl",getSRReportServiceTest.getUri() );
		logger.info(" ####### testgetsr12011report ####");
		return mav;

	}
	
	@RequestMapping( method = RequestMethod.POST)
	public ModelAndView submit(@RequestParam("strxml") String strxml,@RequestParam("strurl") String strurl) {
		
		logger.info("---input---"+strxml);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("tesGetSrReportws");
		mav.addObject("strxml", strxml);
		mav.addObject("strurl", strurl );
		mav.addObject("strxmlrt", getSRReportServiceTest.xmlcallws(strxml,strurl));
		logger.info(" ####### testgetsr12011reportsubmit ####");
		return mav;

	}

}
