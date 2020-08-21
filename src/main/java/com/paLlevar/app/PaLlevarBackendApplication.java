package com.paLlevar.app;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
public class PaLlevarBackendApplication   {
	
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}	

	public static void main(String[] args) {
		SpringApplication.run(PaLlevarBackendApplication.class, args);	
		
	}


}
