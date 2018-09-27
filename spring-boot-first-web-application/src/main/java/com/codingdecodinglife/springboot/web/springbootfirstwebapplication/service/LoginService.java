package com.codingdecodinglife.springboot.web.springbootfirstwebapplication.service;

import org.springframework.stereotype.Component;

@Component
public class LoginService {
	
	public boolean validateCredentials(String userId, String password) {
		return userId.equalsIgnoreCase("anand") && password.equalsIgnoreCase("password");
	}

}
