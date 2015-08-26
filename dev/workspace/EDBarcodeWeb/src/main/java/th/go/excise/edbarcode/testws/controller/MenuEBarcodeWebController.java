package th.go.excise.edbarcode.testws.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MenuEBarcodeWebController {

	private static final Logger logger = LogManager.getLogger(MenuEBarcodeWebController.class);
	
	@RequestMapping(value = "/testws/main.htm", method = RequestMethod.GET)
	public ModelAndView menuTestWS(){
		logger.info("Inside menuTestWS()");
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("menuTestWS");
		
		return mav;
	}
}
