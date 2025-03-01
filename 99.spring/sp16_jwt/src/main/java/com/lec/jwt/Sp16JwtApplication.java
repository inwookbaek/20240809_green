package com.lec.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Sp16JwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sp16JwtApplication.class, args);
	}

}
