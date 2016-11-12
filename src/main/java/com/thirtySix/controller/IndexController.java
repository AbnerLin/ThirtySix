package com.thirtySix.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

	@RequestMapping(value = { "/1" })
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("test");
	}

	@RequestMapping(value = { "/test" })
	@ResponseBody
	public String test(HttpServletRequest request, HttpServletResponse response) {
		return "test";
	}

}
