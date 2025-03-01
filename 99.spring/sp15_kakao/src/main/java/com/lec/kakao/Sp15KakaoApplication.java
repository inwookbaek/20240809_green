package com.lec.kakao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Sp15KakaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sp15KakaoApplication.class, args);
	}

}
