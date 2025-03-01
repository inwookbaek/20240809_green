package com.lec.ajax;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Sp11AjaxApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sp11AjaxApplication.class, args);
	}

}
