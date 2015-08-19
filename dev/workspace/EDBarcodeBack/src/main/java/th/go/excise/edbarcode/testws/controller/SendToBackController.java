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
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import th.go.excise.edbarcode.common.constant.WebServiceConstant;
import th.go.excise.edbarcode.testws.service.SendToBackService;

@Controller
public class SendToBackController {
	
	private static final Logger logger = LogManager.getLogger(SendToBackController.class);
	
	@Autowired
	private SendToBackService sendToBackService;
	
	@RequestMapping(value = "/showTestSendBack.htm", method = RequestMethod.GET)
	public ModelAndView showTest(){
		logger.info("Inside showTest()");
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("sendToBackendTestWS");
		
		return mav;
	}
	
	@RequestMapping(value = "/doControllerSendBack.htm", method = RequestMethod.POST)
	@PayloadRoot(localPart = "EbarcodeSendToBackendRequest", namespace = WebServiceConstant.NAMESPACE_URI)
	@ResponsePayload
	public ModelAndView doController(@RequestParam("strInput") String request){
		logger.info("Inside doController()");
		
		ModelAndView mav = new ModelAndView();
		
		String response = sendToBackService.doService(request);
		logger.debug(ToStringBuilder.reflectionToString(response, ToStringStyle.MULTI_LINE_STYLE));
		
		mav.addObject("strXML", response);
		mav.setViewName("sendToBackendTestWS");
			
		return mav;
	}
}
