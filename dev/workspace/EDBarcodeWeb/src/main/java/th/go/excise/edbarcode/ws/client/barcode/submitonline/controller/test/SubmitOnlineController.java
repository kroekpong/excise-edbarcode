package th.go.excise.edbarcode.ws.client.barcode.submitonline.controller.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import th.go.excise.edbarcode.common.constant.WebServiceConstant;
import th.go.excise.edbarcode.ws.client.barcode.submitonline.service.test.SubmitOnlineService;

@Controller
public class SubmitOnlineController {
	
	@Autowired
	private SubmitOnlineService submitOnlineService;
	
	@RequestMapping(value = "/showTestSubmitWeb.htm", method = RequestMethod.GET)
	public ModelAndView showTest(){
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("SubmitTestWS");
		
		return mav;
	}
	
	@RequestMapping(value = "/doControllerSubmitWeb.htm", method = RequestMethod.POST)
	@PayloadRoot(localPart = "EbarcodeSubmitOnlineRequest", namespace = WebServiceConstant.NAMESPACE_URI)
	@ResponsePayload
	public ModelAndView doController(@RequestParam("strInput") String request){
		ModelAndView mav = new ModelAndView();
		
		String response = submitOnlineService.doService(request);
		System.out.println("!!!!"+response);
		
		mav.addObject("strXML", response);
		mav.setViewName("SubmitTestWS");
			
		return mav;
	}
}
