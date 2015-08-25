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

import th.go.excise.edbarcode.testws.service.SendFormSR12011Service;

@Controller
public class SendFormSR12011Controller {
	
	private static final Logger logger = LogManager.getLogger(SendFormSR12011Controller.class);
	
	@Autowired
	private SendFormSR12011Service sendFormSR12011Service;
	
	@RequestMapping(value = "/testWsSendFormSR12011.htm", method = RequestMethod.GET)
	public ModelAndView testWsSendFormSR12011(){
		logger.info("Inside testWsSendFormSR12011()");
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("strurl", sendFormSR12011Service.getWsUri());
		mav.setViewName("sendFormSR12011TestWS");
		
		return mav;
	}
	
	@RequestMapping(value = "/doControllerSendFormSR12011.htm", method = RequestMethod.POST)
	public ModelAndView doControllerSendFormSR12011(@RequestParam("strInput") String request,@RequestParam("strurl") String strurl){
		logger.info("Inside doControllerSendFormSR12011()");
		
		ModelAndView mav = new ModelAndView();
		
		String response = sendFormSR12011Service.doService(request, strurl);
		logger.debug(ToStringBuilder.reflectionToString(response, ToStringStyle.MULTI_LINE_STYLE));
		mav.addObject("strXML", response);
		mav.addObject("strurl", strurl);
		mav.setViewName("sendFormSR12011TestWS");
			
		return mav;
	}
}
