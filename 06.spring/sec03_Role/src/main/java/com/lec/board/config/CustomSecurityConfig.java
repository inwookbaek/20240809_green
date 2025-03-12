package com.lec.board.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
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

// 6.3.x 이전방식
	@Bean
	public RoleHierarchy roleHierarchy() {

	    RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();

	    hierarchy.setHierarchy("ROLE_C > ROLE_B\n" +
	            "ROLE_B > ROLE_A");

	    return hierarchy;
	}
	
// 변경된 RoleHierarchyImpl() 방식 사용 : fromHierarchy 메소드 활용
//	@Bean
//	public RoleHierarchy roleHierarchy() {
//
//	    return RoleHierarchyImpl.fromHierarchy("""
//	            ROLE_C > ROLE_B
//	            ROLE_B > ROLE_A
//	            """);
//	}	
	
	
//	메소드 형식 : 명시적으로 접두사 작성
//	@Bean
//	public RoleHierarchy roleHierarchy() {
//
//	    return RoleHierarchyImpl.withRolePrefix("접두사_")
//	            .role("C").implies("B")
//	            .role("B").implies("A")
//	            .build();
//	}
	
//	메소드 형식 : 자동으로 ROLE_ 접두사 붙임
//	@Bean
//	public RoleHierarchy roleHierarchy() {
//
//	    return RoleHierarchyImpl.withDefaultRolePrefix()
//	            .role("C").implies("B")
//	            .role("B").implies("A")
//	            .build();
//	}
	
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        
    	http.csrf((auth) -> auth.disable());
    	http.authorizeHttpRequests((auth) -> auth
                .requestMatchers("/login").permitAll()
                .requestMatchers("/").hasAnyRole("A", "B", "C")
                .requestMatchers("/manager").hasAnyRole("B", "C")
                .requestMatchers("/admin").hasAnyRole("C")
                .anyRequest().authenticated());
    	http.formLogin((auth) -> auth.loginPage("/login")
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
    			.roles("C")
    			.build();
    	UserDetails user2 = User.builder()
    			.username("user2")
    			.password(passwordEncoder().encode("12345"))
    			.roles("A")
    			.build();
    	UserDetails gilbaek = User.builder()
    			.username("gilbaek")
    			.password(passwordEncoder().encode("12345"))
    			.roles("C", "B", "A")
    			.build();
    	
    	return new InMemoryUserDetailsManager(user1, user2, gilbaek);
    }    

}