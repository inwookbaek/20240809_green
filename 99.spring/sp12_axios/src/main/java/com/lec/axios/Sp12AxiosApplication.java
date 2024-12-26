package com.lec.axios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Sp12AxiosApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sp12AxiosApplication.class, args);
	}

}
