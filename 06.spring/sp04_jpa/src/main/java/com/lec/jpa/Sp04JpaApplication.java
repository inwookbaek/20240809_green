package com.lec.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Sp04JpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sp04JpaApplication.class, args);
	}

}
