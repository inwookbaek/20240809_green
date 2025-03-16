package com.lec.board.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class CustomSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // CSRF 보호 비활성화
            .httpBasic(basic -> basic.disable()) // HTTP 기본 인증 비활성화
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/login").permitAll() // 루트 경로와 로그인 페이지는 모두 허용
                .requestMatchers("/main").authenticated() // /main은 인증된 사용자만 접근 가능
                .anyRequest().authenticated() // 그 외 모든 요청은 인증 필요
            )
            .formLogin(login -> login
                .loginPage("/login") // 커스텀 로그인 페이지
                .loginProcessingUrl("/loginProc") // 로그인 처리 URL
                .defaultSuccessUrl("/main", true) // 로그인 성공 시 /main으로 리다이렉트
                .failureUrl("/login?error=true") // 로그인 실패 시 /login으로 리다이렉트
                .permitAll()
            )
            .exceptionHandling(exception -> exception
                .accessDeniedPage("/login") // 인증되지 않은 사용자는 로그인 페이지로 리다이렉트
            );

        return http.build();
    }
    
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.withUsername("admin")
            .password("{noop}12345") // NoOp 비밀번호 인코딩 (실제 환경에서는 Bcrypt 사용)
            .roles("admin")
            .build();
        
        UserDetails user = User.withUsername("user")
        		.password("{noop}12345") // NoOp 비밀번호 인코딩 (실제 환경에서는 Bcrypt 사용)
        		.roles("USER")
        		.build();
        return new InMemoryUserDetailsManager(admin, user);
    }   
}