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
        		.permitAll());
        
        return http.build();
    }
    
    // inMemory 방식
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
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