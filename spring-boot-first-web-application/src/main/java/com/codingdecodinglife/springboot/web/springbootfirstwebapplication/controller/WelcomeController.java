package com.codingdecodinglife.springboot.web.springbootfirstwebapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

//import com.codingdecodinglife.springboot.web.springbootfirstwebapplication.service.LoginService;

@Controller
//@SessionAttributes("name")
public class WelcomeController {
	
	/*@Autowired
	LoginService service;
	
	@GetMapping("/login")
	public String showLoginPage(ModelMap model) {
		//model.put("name", name);
	
		return "login";
	}*/
	
	@GetMapping("/")  
	public String showWelcomePage(ModelMap model) {
		
		/*boolean isvalid = service.validateCredentials(name, password);
		if(!isvalid) {
			model.put("errorMessage", "Invalid Credentials");
			return "login";
		}*/
		model.put("name", getLoggedinUserName());
		//model.put("password", password);
	
		return "welcome";
	}
	private String getLoggedinUserName() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof UserDetails) {
			return ((UserDetails)principal).getUsername();
		}
		return principal.toString();
	}

}
