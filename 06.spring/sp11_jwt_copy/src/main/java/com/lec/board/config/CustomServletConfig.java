package com.lec.board.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.extern.log4j.Log4j2;

@Log4j2
@EnableWebMvc
@Configuration
public class CustomServletConfig implements WebMvcConfigurer {

	// localhost:8090/files/** -> templates 
	// classpath: -> /src/main/resources
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/files/**")
		        .addResourceLocations("classpath:/templates");  
	}
	
}
