package com.lec.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Sp09SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sp09SecurityApplication.class, args);
	}

}
