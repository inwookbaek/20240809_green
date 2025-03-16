package com.lec.board.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.lec.board.filter.JwtAuthenticationFilter;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    
    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }   

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	
    	log.info("SecurityFilterChain ===> " + http.toString());
    	
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/login", "/logout", "/join").permitAll()
                .requestMatchers("/main").authenticated()
                .anyRequest().authenticated()
            )
            .formLogin(login -> login
                    .loginPage("/login") // 커스텀 로그인 페이지
                    .loginProcessingUrl("/home")
                    .failureUrl("/login?error=true") // 로그인 실패 시 /login으로 리다이렉트
                    .permitAll()
                )
            .logout(logout -> logout
                    .logoutUrl("/logout") // 로그아웃 URL 설정
                    .logoutSuccessUrl("/main") // 로그아웃 성공 시 메인 페이지로 리다이렉트
                    .invalidateHttpSession(true) // HTTP 세션 무효화
                    .deleteCookies("JSESSIONID") // 세션 쿠키 삭제
                    .permitAll() // 로그아웃은 모두 허용
                )
            // jwtAuthenticationFilter: JWT를 사용하여 사용자 인증을 처리하는 커스텀 필터
            // UsernamePasswordAuthenticationFilter.class: Spring Security의 기본 로그인 필터
            // JWT 필터를 기본 로그인 필터 앞에 추가하여, JWT 인증을 먼저 처리하도록 한다.
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
	        .exceptionHandling(exception -> exception
	                .accessDeniedPage("/login") // 인증되지 않은 사용자는 로그인 페이지로 리다이렉트
	            );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // AuthenticationManager 빈 등록
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }    
}
