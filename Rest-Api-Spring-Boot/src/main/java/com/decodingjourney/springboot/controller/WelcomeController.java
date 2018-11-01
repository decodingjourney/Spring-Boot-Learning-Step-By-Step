package com.decodingjourney.springboot.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.decodingjourney.springboot.configuration.DynamicConfiguration;
import com.decodingjourney.springboot.service.WelcomeService;

@RestController
public class WelcomeController {
	
	@Autowired
	WelcomeService service;
	
	@Autowired
	DynamicConfiguration configuration;
	
	@RequestMapping("/welcome")
	public String welcome() {
		return service.welcomeMessage();
	}
	
	@RequestMapping("/dynamic-configuration")
	public Map configuration() {
		Map<String, Comparable> map = new HashMap<String, Comparable>();
		
		map.put("value", configuration.isValue());
		map.put("message", configuration.getMessage());
		map.put("number", configuration.getNumber());
		
		return map;
		
	}
	

}
