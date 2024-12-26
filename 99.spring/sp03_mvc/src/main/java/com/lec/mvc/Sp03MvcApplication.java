package com.lec.mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class Sp03MvcApplication {

	public static void main(String[] args) {		
		SpringApplication.run(Sp03MvcApplication.class, args);
	}

}
