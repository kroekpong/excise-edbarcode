package th.go.excise.edbarcode.testws.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import th.go.excise.edbarcode.testws.service.TestGetSR12011ReportService;

@Controller
public class TestGetSR12011ReportController {
	
	@Autowired
	TestGetSR12011ReportService testGetSRReportService;

	@RequestMapping(value = "/testgetsr12011report.htm", method = RequestMethod.GET)
	public ModelAndView welcome(HttpServletRequest httpRequest) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("tesGetSrReportws");
//		mav.addObject("strxml", "Stringngngngngngngngngngngn/n/nStringngngngngngngn/nasdasd");
		mav.addObject("strurl",testGetSRReportService.getwsuri() );
		System.out.println(" ####### testgetsr12011report ####");
		return mav;

	}
	
	@RequestMapping(value = "/testgetsr12011reportsubmit.htm", method = RequestMethod.POST)
	public ModelAndView submit(@RequestParam("strxml") String strxml,@RequestParam("strurl") String strurl) {
		System.out.println(strxml);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("tesGetSrReportws");
		mav.addObject("strxml", strxml);
		mav.addObject("strurl", strurl );
		mav.addObject("strxmlrt", testGetSRReportService.xmlcallws(strxml,strurl));
		System.out.println(" ####### testgetsr12011reportsubmit ####");
		return mav;

	}

}
