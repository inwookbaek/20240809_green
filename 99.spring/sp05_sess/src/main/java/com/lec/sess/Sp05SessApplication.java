package com.lec.sess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class Sp05SessApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sp05SessApplication.class, args);
	}

}
