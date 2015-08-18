package th.go.excise.edbarcode.ws.client.sta.controller.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import th.go.excise.edbarcode.common.constant.WebServiceConstant;
import th.go.excise.edbarcode.ws.client.sta.service.test.SendToBackService;

@Controller
public class SendToBackController {
	
	@Autowired
	private SendToBackService sendToBackService;
	
	@RequestMapping(value = "/showTestSendBack.htm", method = RequestMethod.GET)
	public ModelAndView showTest(){
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("SendTestWS");
		
		return mav;
	}
	
	@RequestMapping(value = "/doControllerSendBack.htm", method = RequestMethod.POST)
	@PayloadRoot(localPart = "EbarcodeSendToBackendRequest", namespace = WebServiceConstant.NAMESPACE_URI)
	@ResponsePayload
	public ModelAndView doController(@RequestParam("strInput") String request){
		ModelAndView mav = new ModelAndView();
		
		String response = sendToBackService.doService(request);
		System.out.println("!!!!"+response);
		
		mav.addObject("strXML", response);
		mav.setViewName("SendTestWS");
			
		return mav;
	}
}
