package com.lec.board.config;

import java.lang.annotation.Annotation;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;


@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		// Security Scheme를 정의(Bearer token)
        SecurityScheme securityScheme = new SecurityScheme()
                .name("Authorization")
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER);

        // 모든 endpoint에서 sequrity 요청을 추가
        SecurityRequirement securityRequirement = new SecurityRequirement().addList("Authorization");
        
        return new OpenAPI().components(new Components().addSecuritySchemes("Authorization", securityScheme))
        					.addSecurityItem(securityRequirement)
        					.info(new Info().title("스프링부트 JWT프로젝트 Swagger").version("1.0"));
	}
	
	@Bean
	public GroupedOpenApi publicApi() {
		
		return GroupedOpenApi.builder()
				.group("public")
				.pathsToMatch("/api/**")   // 요청경로
				.packagesToScan("com.lec.board.controller") // controller 경로
				.build();
				
	}
}
