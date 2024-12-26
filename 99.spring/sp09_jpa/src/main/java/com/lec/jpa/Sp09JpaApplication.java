package com.lec.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Sp09JpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sp09JpaApplication.class, args);
	}

}
