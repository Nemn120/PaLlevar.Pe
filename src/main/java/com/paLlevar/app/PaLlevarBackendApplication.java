package com.paLlevar.app;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.paLlevar.app.controller.CategoryProductController;


@SpringBootApplication
public class PaLlevarBackendApplication   {
	private static final Logger logger = LogManager.getLogger(PaLlevarBackendApplication.class);	

	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}	

	public static void main(String[] args) {

		logger.atLevel(Level.ALL);
		SpringApplication.run(PaLlevarBackendApplication.class, args);
		
		
	}


}
