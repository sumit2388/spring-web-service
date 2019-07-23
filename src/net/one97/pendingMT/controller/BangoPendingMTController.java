package net.one97.pendingMT.controller;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.one97.pendingMT.bean.RequestBean;
import net.one97.pendingMT.service.BangoPendingMTService;

@Controller
@RequestMapping("/onlineRequest.htm")
public class BangoPendingMTController {
	
	private static final Logger logger = Logger.getLogger(BangoPendingMTController.class);
	
	@Autowired
	private BangoPendingMTService bangoPendingMTService;

	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public String bangoPendingMTRequest(@RequestParam Map<String, String> allRequestParams){
		logger.info("inside bangoPendingMTRequest " + allRequestParams.toString());
		RequestBean requestBean = new RequestBean(allRequestParams);
		logger.info("requestBean "+requestBean );
		String response = bangoPendingMTService.processPendingMTRequest(requestBean);
		logger.info("Msisdn = " + requestBean.getMsisdn() + " response : " + response);
		return response;
	}

}
