package com.lec.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Sp10BoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sp10BoardApplication.class, args);
	}

}
