package com.lec.board.config;

import java.sql.Array;
import java.util.Arrays;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.lec.board.security.APIUserDetailsService;
import com.lec.board.security.filter.APILoginFilter;
import com.lec.board.security.filter.RefreshTokenFilter;
import com.lec.board.security.filter.TokenCheckFilter;
import com.lec.board.security.handler.APILoginSuccessHandler;
import com.lec.board.util.JWTUtil;

import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Configuration 
@RequiredArgsConstructor
@EnableMethodSecurity(prePostEnabled = true)
public class CustomSecurityConfig {
	
	private final APIUserDetailsService apiUserDetailsService;
	private final JWTUtil jwtUtil;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		log.info("---------------- wer configuration ----------------------");
		return web -> web.ignoring()
				         .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		log.info("---------------- filterChain -------------------");
		
		// AuthenticationManager설정
		AuthenticationManagerBuilder authenticationManagerBuilder =
				http.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder
			.userDetailsService(apiUserDetailsService)
			.passwordEncoder(passwordEncoder());
		
		// Get AuthenticationManager
		AuthenticationManager authenticationManager = authenticationManagerBuilder.build();
		
		// http에 AuthenticationManager를 설정 - 반드시 필요
		http.authenticationManager(authenticationManager);
		
		// APILoginFilter
		APILoginFilter apiLoginFilter = new APILoginFilter("/generateToken");
		apiLoginFilter.setAuthenticationManager(authenticationManager);

		// APILogin Success
		APILoginSuccessHandler successHandler = new APILoginSuccessHandler(jwtUtil);
		apiLoginFilter.setAuthenticationSuccessHandler(successHandler);
		
		// APILoginFilter의 위치조정
		// APILoginFilter의 경로는 /generateToken로 지정되었고
		// 스프링시큐리티에서 username, password를 처리하는 UsernamePasswordAuthenticationFilter
		// 클래스 앞으로 위치 처리
		http.addFilterBefore(apiLoginFilter, UsernamePasswordAuthenticationFilter.class);
			
		// api시작하는 모든 경로는 TokenCheckFilter를 동작
		http.addFilterBefore(
				tokenCheckFilter(jwtUtil),
				UsernamePasswordAuthenticationFilter.class);
		
		// refresh Token 호출처리
		http.addFilterBefore(new RefreshTokenFilter("/refreshToken", jwtUtil), 
			TokenCheckFilter.class);
				
		// CSRF 토큰의 비활성화
		http.csrf(csrf -> csrf.disable()); 
		
		// session을 사용하지 않게 설정
		http.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); 
		
		// CORS문제해결
		http.cors(httpSecurityCorsConfigurer -> {
			httpSecurityCorsConfigurer.configurationSource(configurationSource());
		});
		
		return http.build();
	}

	@Bean
	public CorsConfigurationSource configurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOriginPatterns(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE"));
		configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
		configuration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(); 
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	private TokenCheckFilter tokenCheckFilter(JWTUtil jwtUtil) {
		return new TokenCheckFilter(jwtUtil);
	}


}
