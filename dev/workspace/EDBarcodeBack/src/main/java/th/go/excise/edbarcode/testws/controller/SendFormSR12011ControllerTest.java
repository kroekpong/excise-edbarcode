package th.go.excise.edbarcode.testws.controller;

import java.io.IOException;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import th.go.excise.edbarcode.testws.service.SendFormSR12011ServiceTest;

@Controller
@RequestMapping(value = "/testws/sendFormSR12011Ws.htm")
public class SendFormSR12011ControllerTest {
	
	private static final Logger logger = LogManager.getLogger(SendFormSR12011ControllerTest.class);
	
	@Value("${edBarcodeServiceTest.client.sendFormSR12011ServiceTest.xml}")
	private String strGetURI;
	
	@Autowired
	private SendFormSR12011ServiceTest sendFormSR12011Service;
	
	@RequestMapping( method = RequestMethod.GET)
	public ModelAndView sendFormSR12011Ws() throws IOException{
		logger.info("Inside sendFormSR12011Ws()");
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("struri", strGetURI);
		mav.addObject("strRequestXML", sendFormSR12011Service.getStringRequestXMLInit());
		mav.setViewName("sendFormSR12011TestWs");
		
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView responseSendFormSR12011Ws(@RequestParam("strInput") String request,@RequestParam("struri") String struri){
		logger.info("Inside responseSendFormSR12011Ws()");
		
		ModelAndView mav = new ModelAndView();
		
		String response = sendFormSR12011Service.doService(request);
		logger.debug(ToStringBuilder.reflectionToString(response, ToStringStyle.MULTI_LINE_STYLE));
		mav.addObject("strXML", response);
		mav.addObject("struri", struri);
		mav.addObject("strRequestXML", request);
		mav.setViewName("sendFormSR12011TestWs");
			
		return mav;
	}
}
