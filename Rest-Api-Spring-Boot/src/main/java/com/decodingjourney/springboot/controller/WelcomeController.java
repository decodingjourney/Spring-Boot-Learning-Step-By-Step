package com.decodingjourney.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.decodingjourney.springboot.service.WelcomeService;

@RestController
public class WelcomeController {
	
	@Autowired
	WelcomeService service;
	
	@RequestMapping("/welcome")
	public String welcome() {
		return service.welcomeMessage();
	}
	

}
