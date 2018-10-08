package com.decodingjourney.springboot.service;

import org.springframework.stereotype.Component;

@Component
public class WelcomeService {
	public String welcomeMessage() {
		return "Good morning Everyone, Please don't waste your time";
	}

}
