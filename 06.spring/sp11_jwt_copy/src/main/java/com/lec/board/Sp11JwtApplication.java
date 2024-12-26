package com.lec.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Sp11JwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sp11JwtApplication.class, args);
	}

}
