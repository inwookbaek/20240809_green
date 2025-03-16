package com.lec.board.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.lec.board.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class CustomSecurityConfig {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
            	.requestMatchers("/", "/login", "/join", "/joinProc").permitAll()
            	.requestMatchers("/admin").hasRole("ADMIN")
            	.requestMatchers("my/**").hasAnyRole("ADMIN", "USER")
            	.anyRequest().authenticated())
            .formLogin(login -> login
        		.loginPage("/login")
        		.loginProcessingUrl("/loginProc")
        		.permitAll())
            .sessionManagement(sess -> sess
                    .maximumSessions(1)              // 하나의 아이디로 다중 로그인 허용 개수
                    .maxSessionsPreventsLogin(true)) // 세션 초과 시 처리 방법 (true: 차단, false: 기존 세션 삭제)
            // .and()는 deprecated
            .sessionManagement(sess -> sess
                    .sessionFixation().changeSessionId() // 세션 고정 보호 설정 
                     // none()-로그인시세션정보변경안함, newSession()-새로운세션, changeSessionId()_로그인시 동일세션에대한 Id 변경
                );
        
        // http.csrf(csrf -> csrf.disable()) ;
        // 개발시 유지, 라이브는 주석처리후 html or controller에서 처리해야 한다.
        // POST방식은 input태그 token처리, ajax처리방식, GET방식로그아웃처리
        // REST API서버로 동작하는 경우 csrf.disable()설정해도 된다.(위험부담이 없다) 
        // 처리방법 : https://youtu.be/l8xjecnAzMw 

        return http.build();
    }
    
    // inMemory 방식
    @Bean
    public UserDetailsService users() {
    	UserDetails user1 = User.builder()
    			.username("user1")
    			.password(passwordEncoder().encode("12345"))
    			.roles("ADMIN")
    			.build();
    	UserDetails user2 = User.builder()
    			.username("user2")
    			.password(passwordEncoder().encode("12345"))
    			.roles("USER")
    			.build();
    	UserDetails gilbaek = User.builder()
    			.username("gilbaek")
    			.password(passwordEncoder().encode("12345"))
    			.roles("ADMIN", "USER")
    			.build();
    	
    	return new InMemoryUserDetailsManager(user1, user2, gilbaek);
    }    

}