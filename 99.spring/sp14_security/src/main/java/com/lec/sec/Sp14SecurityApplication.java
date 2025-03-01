package com.lec.sec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Sp14SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sp14SecurityApplication.class, args);
	}

}
