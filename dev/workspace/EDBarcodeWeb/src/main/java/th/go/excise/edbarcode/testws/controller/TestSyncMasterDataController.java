package th.go.excise.edbarcode.testws.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import th.go.excise.edbarcode.testws.service.TestSyncMasterDataService;

@Controller
public class TestSyncMasterDataController {
	
	@Autowired
	TestSyncMasterDataService testSyncMasterDataService;
	
	@RequestMapping(value = "/testsyncmasterdata.htm", method = RequestMethod.GET)
	public ModelAndView welcome(HttpServletRequest httpRequest) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("testSyncMasterDataWs");
//		mav.addObject("strxml", "Stringngngngngngngngngngngn/n/nStringngngngngngngn/nasdasd");
		mav.addObject("strurl",testSyncMasterDataService.getwsuri());
		System.out.println(" ####### syncmasterdata ####");
		return mav;

	}
	
	@RequestMapping(value = "/testsyncmasterdatasubmit.htm", method = RequestMethod.POST)
	public ModelAndView submit(@RequestParam("strxml") String strxml) {
		System.out.println(strxml);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("testSyncMasterDataWs");
		mav.addObject("strxml", strxml);
		mav.addObject("strxmlrt", testSyncMasterDataService.xmlcallws(strxml));
		System.out.println(" ####### syncmasterdatasubmit ####");
		return mav;

	}

}
