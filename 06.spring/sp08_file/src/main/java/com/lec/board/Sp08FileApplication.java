package com.lec.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Sp08FileApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sp08FileApplication.class, args);
	}

}
