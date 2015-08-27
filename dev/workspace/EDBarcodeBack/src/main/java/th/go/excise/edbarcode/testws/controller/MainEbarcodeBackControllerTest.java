package th.go.excise.edbarcode.testws.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/testws")
public class MainEbarcodeBackControllerTest {

	private static final Logger logger = LogManager.getLogger(MainEbarcodeBackControllerTest.class);
	
	@RequestMapping(value = "/main.htm", method = RequestMethod.GET)
	public ModelAndView mainTestWs(){
		logger.info("Inside mainTestWs()");
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("mainTestWs");
		
		return mav;
	}
}
