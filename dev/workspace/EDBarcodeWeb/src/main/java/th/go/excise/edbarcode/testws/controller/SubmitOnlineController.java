package th.go.excise.edbarcode.testws.controller;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import th.go.excise.edbarcode.testws.service.SubmitOnlineService;

@Controller
public class SubmitOnlineController {
	
	private static final Logger logger = LogManager.getLogger(SubmitOnlineController.class);
	
	@Autowired
	private SubmitOnlineService submitOnlineService;
	
	@RequestMapping(value = "/showTestSubmitWeb.htm", method = RequestMethod.GET)
	public ModelAndView showTest(){
		logger.info("Inside showTest()");
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("strurl", submitOnlineService.getWsuri());
		mav.setViewName("submitOnlineTestWS");
		
		return mav;
	}
	
	@RequestMapping(value = "/doControllerSubmitWeb.htm", method = RequestMethod.POST)
	public ModelAndView doController(@RequestParam("strInput") String request, @RequestParam("strurl") String strurl){
		logger.info("Inside doController()");
		
		ModelAndView mav = new ModelAndView();
		
		String response = submitOnlineService.doService(request, strurl);
		logger.debug(ToStringBuilder.reflectionToString(response, ToStringStyle.MULTI_LINE_STYLE));
		mav.addObject("strXML", response);
		mav.addObject("strurl", strurl);
		mav.setViewName("submitOnlineTestWS");
			
		return mav;
	}
	
}
