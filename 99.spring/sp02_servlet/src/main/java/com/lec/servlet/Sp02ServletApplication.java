package com.lec.servlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class Sp02ServletApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sp02ServletApplication.class, args);
	}

}
