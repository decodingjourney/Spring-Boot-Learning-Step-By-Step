package com.codingdecodinglife.springboot.web.springbootfirstwebapplication.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Controller("error")
public class ErrorController {
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(HttpServletRequest req, Exception ex) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("Exception", ex.getStackTrace());
		mv.addObject("Url", req.getRequestURL());
		mv.setViewName("error");
		return mv;
		
	}

}
