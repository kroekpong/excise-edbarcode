package th.go.excise.edbarcode.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CommonController {  
 
 
	
	@RequestMapping(value = "/welcome.htm", method = RequestMethod.GET)
	public ModelAndView welcome(HttpServletRequest httpRequest) {			
		ModelAndView mav = new ModelAndView();		 
		mav.setViewName("welcome"); 
		 System.out.println(" ####### Welcome ####");
		return mav;	
		 
	}	
 
	 
}
