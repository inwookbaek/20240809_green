#!/bin/bash

# 프로젝트 디렉토리 생성
mkdir -p src/main/java/com/lec/board/{config,controller,dto,entity,repository,service,filter}

# application.yml 파일 생성
cat <<EOL > src/main/resources/application.yml
server:
  port: 8090

spring:
  application:
    name: sec98_deepseek
  datasource:
    url: jdbc:mysql://localhost:3306/secStudy?createDatabaseIfNotExist=true
    username: root
    password: 12345
    driver-class-name: com.mysql.cj.jdbc.Driver
  jpa:
    hibernate:
      ddl-auto: update
      naming:
        physical-strategy: org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
  thymeleaf:
    prefix: classpath:/templates/
    suffix: .html
 
jwt:
  secret: DdqOm0p+pbVNqhj12jafRAnzF62IyfOSFbzeIjTWvLY=    # 키생성 : openssl rand -base64 32
  expiration: 86400000                                    # ms : 60*60*60*24 = 24시간

EOL

# UserEntity.java 파일 생성
cat <<EOL > src/main/java/com/lec/board/entity/UserEntity.java
package com.lec.board.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;

@Data
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role; // 사용자 역할 (예: ROLE_USER, ROLE_ADMIN)

    @Column(nullable = false)
    private boolean isAccountNonExpired = true;

    @Column(nullable = false)
    private boolean isAccountNonLocked = true;

    @Column(nullable = false)
    private boolean isCredentialsNonExpired = true;

    @Column(nullable = false)
    private boolean isEnabled = true;

    // 권한 정보를 반환하는 메서드
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }
}
EOL

# UserDto.java 파일 생성
cat <<EOL > src/main/java/com/lec/board/dto/UserDto.java
package com.lec.board.dto;

import lombok.Data;

@Data
public class UserDto {
    private String username;
    private String password;
    private String role;
}
EOL

# JwtRequest.java 파일 생성
cat <<EOL > src/main/java/com/lec/board/dto/JwtRequest.java
package com.lec.board.dto;

import lombok.Data;

@Data
public class JwtRequest {
    private String username;
    private String password;
}
EOL

# JwtResponse.java 파일 생성
cat <<EOL > src/main/java/com/lec/board/dto/JwtResponse.java
package com.lec.board.dto;

import lombok.Data;

@Data
public class JwtResponse {
    private String token;

    public JwtResponse(String token) {
        this.token = token;
    }
}
EOL

# UserRepository.java 파일 생성
cat <<EOL > src/main/java/com/lec/board/repository/UserRepository.java
package com.lec.board.repository;

import com.lec.board.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByUsername(String username);
}
EOL

# UserService.java 파일 생성
cat <<EOL > src/main/java/com/lec/board/service/UserService.java
package com.lec.board.service;

import com.lec.board.dto.UserDto;
import com.lec.board.entity.UserEntity;
import com.lec.board.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void join(UserDto userDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDto.getUsername());
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userEntity.setRole("ROLE_USER");
        userRepository.save(userEntity);
    }
}
EOL

# JwtService.java 파일 생성
cat <<EOL > src/main/java/com/lec/board/service/JwtService.java
package com.lec.board.service;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private final SecretKey secretKey; // SecretKey로 변경
    private final long expiration;

    public JwtService(@Value("${jwt.secret}") String secret, @Value("${jwt.expiration}") long expiration) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes()); // SecretKey 생성
        this.expiration = expiration;
    }

    // JWT 토큰 생성
    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username) // setSubject -> subject
                .issuedAt(new Date()) // setIssuedAt -> issuedAt
                .expiration(new Date(System.currentTimeMillis() + expiration)) // setExpiration -> expiration
                .signWith(secretKey) // signWith에 SecretKey 사용
                .compact();
    }

    // JWT 토큰에서 사용자 아이디 추출
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(secretKey) // setSigningKey -> verifyWith
                .build()
                .parseSignedClaims(token) // parseClaimsJws -> parseSignedClaims
                .getPayload(); // getBody -> getPayload
        return claims.getSubject();
    }

    // JWT 토큰 유효성 검증
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                .verifyWith(secretKey) // setSigningKey -> verifyWith
                .build()
                .parseSignedClaims(token); // parseClaimsJws -> parseSignedClaims
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
EOL

# CustomUserDetailsService.java 파일 생성
cat <<EOL > src/main/java/com/lec/board/service/CustomUserDetailsService.java
package com.lec.board.service;

import com.lec.board.entity.UserEntity;
import com.lec.board.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
        return new org.springframework.security.core.userdetails.User(
                userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.getAuthorities()
        );
    }
}
EOL

# JwtAuthenticationFilter.java 파일 생성
cat <<EOL > src/main/java/com/lec/board/filter/JwtAuthenticationFilter.java
package com.lec.board.filter;

import com.lec.board.service.JwtService;
import com.lec.board.service.CustomUserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtService jwtService, CustomUserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = extractToken(request);
        if (token != null && jwtService.validateToken(token)) {
            String username = jwtService.getUsernameFromToken(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
EOL

# AuthController.java 파일 생성
cat <<EOL > src/main/java/com/lec/board/controller/AuthController.java
package com.lec.board.controller;

import com.lec.board.dto.JwtRequest;
import com.lec.board.dto.JwtResponse;
import com.lec.board.dto.UserDto;
import com.lec.board.service.JwtService;
import com.lec.board.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/join")
    public String join() {
        return "join";
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest jwtRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
        String token = jwtService.generateToken(jwtRequest.getUsername());
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody UserDto userDto) {
        userService.join(userDto);
        return ResponseEntity.ok("회원가입 성공");
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/main")
    public String main() {
        return "main";
    }
}
EOL

# SecurityConfig.java 파일 생성
cat <<EOL > src/main/java/com/lec/board/config/SecurityConfig.java
package com.lec.board.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.lec.board.filter.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/login", "/join").permitAll()
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
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

EOL

# JwtConfig.java 파일 생성
cat <<EOL > src/main/java/com/lec/board/config/JwtConfig.java
package com.lec.board.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtConfig {
    private String secret;
    private long expiration;
}
EOL

echo "Java 파일 생성 완료!"




#!/bin/bash

# 프로젝트 디렉토리 생성
# mkdir -p src/main/resources/templates

# 기본 HTML 파일 생성
cat <<EOL > src/main/resources/templates/login.html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>로그인</title>
</head>
<body>
    <h1>로그인</h1>
    <form th:action="@{/login}" method="post">
        <input type="text" name="username" placeholder="아이디" required>
        <input type="password" name="password" placeholder="비밀번호" required>
        <button type="submit">로그인</button>
    </form>
    <a th:href="@{/join}">회원가입</a>
</body>
</html>
EOL

cat <<EOL > src/main/resources/templates/join.html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>회원가입</title>
</head>
<body>
    <h1>회원가입</h1>
    <form th:action="@{/join}" method="post">
        <input type="text" name="username" placeholder="아이디" required>
        <input type="password" name="password" placeholder="비밀번호" required>
        <button type="submit">가입하기</button>
    </form>
    <a th:href="@{/login}">로그인</a>
</body>
</html>
EOL

cat <<EOL > src/main/resources/templates/home.html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>홈</title>
</head>
<body>
    <h1>홈 페이지</h1>
    <p>로그인에 성공했습니다!</p>
    <a th:href="@{/main}">메인 페이지로 이동</a>
    <form th:action="@{/logout}" method="post">
        <button type="submit">로그아웃</button>
    </form>
</body>
</html>
EOL

cat <<EOL > src/main/resources/templates/main.html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>메인</title>
</head>
<body>
    <h1>메인 페이지</h1>
    <p>환영합니다!</p>
    <a th:href="@{/home}">홈으로 이동</a>
</body>
</html>
EOL

echo "프로젝트 구조 생성 완료!"
