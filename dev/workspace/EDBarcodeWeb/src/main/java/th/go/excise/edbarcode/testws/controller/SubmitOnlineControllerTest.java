package th.go.excise.edbarcode.testws.controller;

import java.io.IOException;

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

import th.go.excise.edbarcode.testws.service.ReadFileXMLServiceTest;
import th.go.excise.edbarcode.testws.service.SubmitOnlineServiceTest;

@Controller
@RequestMapping(value = "/testws/submitOnlineWs.htm")
public class SubmitOnlineControllerTest {
	
	private static final Logger logger = LogManager.getLogger(SubmitOnlineControllerTest.class);
	
	@Autowired
	private SubmitOnlineServiceTest submitOnlineServiceTest;
	
	@Autowired
	private ReadFileXMLServiceTest readFileXMLServiceTest;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView submitOnlineWs() throws IOException{
		logger.info("Inside submitOnlineWs()");
		
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("strurl", submitOnlineServiceTest.getWsuri());
		mav.addObject("strRequestXML", "");
		mav.setViewName("submitOnlineTestWs");
		
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView responseSubmitOnlineWs(@RequestParam("strInput") String request, @RequestParam("strurl") String strurl){
		logger.info("Inside responseSubmitOnlineWs()");
		
		ModelAndView mav = new ModelAndView();
		
		String response = submitOnlineServiceTest.doService(request, strurl);
		logger.debug(ToStringBuilder.reflectionToString(response, ToStringStyle.MULTI_LINE_STYLE));
		mav.addObject("strXML", response);
		mav.addObject("strurl", strurl);
		mav.addObject("strRequestXML", request);
		mav.setViewName("submitOnlineTestWs");
			
		return mav;
	}
	
}
