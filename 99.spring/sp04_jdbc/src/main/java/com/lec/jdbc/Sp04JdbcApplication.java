package com.lec.jdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class Sp04JdbcApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sp04JdbcApplication.class, args);
	}

}
