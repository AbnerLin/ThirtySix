package com.thirtySix.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

	// import org.apache.log4j.Logger;
	// private Logger logger = Logger.getLogger(this.getClass());

	/**
	 * Requesting index page.
	 * 
	 * @return
	 */
	@RequestMapping(value = { "/index" }, method = RequestMethod.GET)
	public String index() {
		return "index";
	}

}
