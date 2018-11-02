package com.decodingjourney.springboot.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
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
		
		log.info("-------------------------------");
        log.info("Finding all users");
        log.info("-------------------------------");
        for (User user : repository.findAll()) {
            log.info(user.toString());
        }
        /*log.info("-------------------------------");
        log.info("deleting all users");
        log.info("-------------------------------");
        repository.deleteAll();
        for (User user : repository.findAll()) {
            log.info(user.toString());
        }*/
        
        log.info("-------------------------------");
        log.info("Finding Admin users");
        log.info("-------------------------------");
        for (User user : repository.findByRole("Admin")) {
            log.info(user.toString());
        }
	}
	
	

}