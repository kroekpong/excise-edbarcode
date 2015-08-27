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
import th.go.excise.edbarcode.testws.service.SendFormSR12011Service;

@Controller
@RequestMapping(value = "/testws/sendFormSR12011Ws.htm")
public class SendFormSR12011ControllerTest {
	
	private static final Logger logger = LogManager.getLogger(SendFormSR12011ControllerTest.class);
	
	@Autowired
	private SendFormSR12011Service sendFormSR12011Service;
	
	@Autowired
	private ReadFileXMLServiceTest readFileXMLServiceTest;
	
	@RequestMapping( method = RequestMethod.GET)
	public ModelAndView sendFormSR12011Ws() throws IOException{
		logger.info("Inside sendFormSR12011Ws()");
		
		String strPathRequest = "C:/Users/Hashimoto Ai/Documents/Project_EDBarcode v4/trunk/dev/workspace/EDBarcodeBack/src/test/resources/xml/send_form12011_request.xml";
//		String strPathRequest = "../submit_online_request.xml";
		StringBuilder strRequestXML = readFileXMLServiceTest.callRequest(strPathRequest);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("strurl", sendFormSR12011Service.getWsUri());
		mav.addObject("strRequestXML", strRequestXML);
		mav.setViewName("sendFormSR12011TestWs");
		
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView responseSendFormSR12011Ws(@RequestParam("strInput") String request,@RequestParam("strurl") String strurl){
		logger.info("Inside responseSendFormSR12011Ws()");
		
		ModelAndView mav = new ModelAndView();
		
		String response = sendFormSR12011Service.doService(request, strurl);
		logger.debug(ToStringBuilder.reflectionToString(response, ToStringStyle.MULTI_LINE_STYLE));
		mav.addObject("strXML", response);
		mav.addObject("strurl", strurl);
		mav.addObject("strRequestXML", request);
		mav.setViewName("sendFormSR12011TestWs");
			
		return mav;
	}
}
