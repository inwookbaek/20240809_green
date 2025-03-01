package com.lec.board.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.extern.log4j.Log4j2;

/*
	✅ CORS (Cross-Origin Resource Sharing)란?
	웹 애플리케이션이 다른 도메인(Origin)에 있는 리소스를 요청할 때 발생하는 보안 정책.
	기본적으로 웹 브라우저는 보안상의 이유로 교차 출처 요청을 차단한다.
	CORS 설정을 하면 특정 출처에서 요청을 허용할 수 있다.
	
	✅ WebMvcConfigurer 인터페이스란?
	Spring MVC에서 설정을 커스터마이징할 수 있도록 도와주는 인터페이스이다.
	WebMvcConfigurer를 구현하면 Spring의 기본 MVC 설정을 변경할 수 있다.
	
	🔹 CORS (Cross-Origin Resource Sharing) 설정을 담당하는 클래스
	🔹 클라이언트(프론트엔드)와 서버(Spring Boot)가 다른 도메인에 있을 경우,
	   기본적으로 브라우저는 보안 정책에 의해 요청을 차단한다.
	🔹 이 클래스는 특정 도메인에서 서버로 요청을 허용하도록 설정한다.	
*/
@Log4j2
@Configuration
public class CorsConfig implements WebMvcConfigurer { // WebMvcConfigurer를 구현하여 CORS 설정을 커스터마이징

    /**
     * 📌 CORS 설정을 추가하는 메서드
     * @param corsRegistry CORS 규칙을 설정하는 객체
     */
    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
    	
        corsRegistry
            .addMapping("/**")        // 1️⃣ 모든 엔드포인트(API 경로)에 대해 CORS 허용
            .allowedMethods("*")      // 2️⃣ 모든 HTTP 메서드(GET, POST, PUT, DELETE 등) 허용
            .allowedOrigins("*");     // 3️⃣ 모든 도메인에서 요청 허용 (보안이 필요하면 특정 도메인만 허용 가능)

    }
}
