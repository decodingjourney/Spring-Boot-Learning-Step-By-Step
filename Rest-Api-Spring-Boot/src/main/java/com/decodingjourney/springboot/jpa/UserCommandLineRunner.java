package com.decodingjourney.springboot.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

public class UserCommandLineRunner implements CommandLineRunner{
	
	private static final Logger log = LoggerFactory
            .getLogger(UserCommandLineRunner.class);
	
	@Autowired
	UserRepository repository;

	@Override
	public void run(String... arg0) throws Exception {
		
		repository.save(new User("Anand", "Admin"));
		repository.save(new User("Hem", "User"));
		repository.save(new User("Munna", "User"));
		repository.save(new User("Sonam", "Admin"));
		
		for(User user: repository.findAll()) {
			log.info(user.toString());
		}
	}
	
	

}
