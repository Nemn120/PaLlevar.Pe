package com.paLlevar.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
public class PaLlevarBackendApplication   {

	public static void main(String[] args) {
		SpringApplication.run(PaLlevarBackendApplication.class, args);
		
		
	}


}
