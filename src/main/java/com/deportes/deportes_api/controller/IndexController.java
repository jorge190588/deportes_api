package com.deportes.deportes_api.controller;

import org.apache.log4j.Logger; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
	Logger logger = Logger.getLogger(IndexController.class);
	
	@RequestMapping("/")
	public String index() {
		logger.info("access to: / route");	
		return "index route";
	}
	 
}
