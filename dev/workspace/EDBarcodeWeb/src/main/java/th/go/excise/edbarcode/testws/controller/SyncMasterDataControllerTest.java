package th.go.excise.edbarcode.testws.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import th.go.excise.edbarcode.testws.service.SyncMasterDataServiceTest;

@Controller
@RequestMapping(value = "/testws/syncmasterdata.htm")
public class SyncMasterDataControllerTest {
	
	private static final Logger logger = LogManager.getLogger(SyncMasterDataControllerTest.class);
	
	@Autowired
	SyncMasterDataServiceTest syncMasterDataServiceTest;
	
	@RequestMapping( method = RequestMethod.GET)
	public ModelAndView welcome(HttpServletRequest httpRequest) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("testSyncMasterDataWs");
//		mav.addObject("strxml", "Stringngngngngngngngngngngn/n/nStringngngngngngngn/nasdasd");
		mav.addObject("strurl",syncMasterDataServiceTest.getwsuri());
		logger.info(" ####### syncmasterdata ####");
		return mav;

	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView submit(@RequestParam("strxml") String strxml,@RequestParam("strurl") String uri) {
		System.out.println(strxml);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("testSyncMasterDataWs");
		mav.addObject("strxml", strxml);
		mav.addObject("strurl",uri);
		mav.addObject("strxmlrt", syncMasterDataServiceTest.xmlcallws(strxml,uri));
		logger.info(" ####### syncmasterdatasubmit ####");
		return mav;

	}

}
