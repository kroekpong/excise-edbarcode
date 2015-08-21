package th.go.excise.edbarcode.testws.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MenuEbarcodeBackController {

	private static final Logger logger = LogManager.getLogger(MenuEbarcodeBackController.class);
	
	@RequestMapping(value = "/menuEBarcodeBackTestWS.htm", method = RequestMethod.GET)
	public ModelAndView menuEBarcodeBackTestWS(){
		logger.info("Inside menuEBarcodeBackTestWS()");
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("menuEBarcodeBackTestWS");
		
		return mav;
	}
}
