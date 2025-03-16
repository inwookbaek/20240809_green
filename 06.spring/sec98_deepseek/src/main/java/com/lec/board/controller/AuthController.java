package com.lec.board.controller;

import com.lec.board.dto.JwtRequest;
import com.lec.board.dto.JwtResponse;
import com.lec.board.dto.UserDto;
import com.lec.board.service.JwtService;
import com.lec.board.service.UserService;

import lombok.extern.log4j.Log4j2;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Log4j2
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

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/")
    public String main() {
        return "main";
    }  

    @PostMapping("/login")
    public String login(
        @RequestParam("username") String username, @RequestParam("password") String password) {  // 폼 데이터에서 username, password 받기
        log.info("[POST] login ===> " + username + ", " + password);

        // 사용자 인증
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(username, password)
        );

        // JWT 토큰 생성
        String token = jwtService.generateToken(username);
        log.info("Generated Token: " + token);

        // 홈 페이지로 리다이렉트
        return "redirect:/home";
    }
//    
//    @PostMapping("/login")
//    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest jwtRequest) {
//    	
//    	log.info("[POST] login ===> " + jwtRequest.getUsername() + ", " + jwtRequest.getPassword());
//    	
//    	authenticationManager.authenticate(
//    			new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
//    	String token = jwtService.generateToken(jwtRequest.getUsername());
//    	return ResponseEntity.ok(new JwtResponse(token));
//    }
//    
//    @PostMapping("/login")
//    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest jwtRequest) {
//    	
//    	log.info("[POST] login ===> " + jwtRequest.getUsername() + ", " + jwtRequest.getPassword());
//    	
//    	authenticationManager.authenticate(
//    			new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
//    	String token = jwtService.generateToken(jwtRequest.getUsername());
//    	return ResponseEntity.ok(new JwtResponse(token));
//    }
   
    @GetMapping("/join")
    public String join() {
        return "join";
    }
    
    // @RequestParam("username") 명시적으로 선언하지 않으면 에러
    // public String join(@RequestParam String username, @RequestParam String password) {
    // 명시적으로 선언하지 않으려면 bundle.gradle에 아래와 같이 정의
    // tasks.withType(JavaCompile) {
    //   options.compilerArgs << "-parameters"
	// }
    // 이렿게 해도 동일 에러발생 --> 나중에 확인해 볼 것
    @PostMapping("/join")
    public String join(@RequestParam("username") String username, @RequestParam("password") String password) {
        // 회원가입 로직 처리
    	log.info("회원가입 요청: username={}, password={}", username, password);
    	
    	UserDto userDto = new UserDto();
    	userDto.setUsername(username);
    	userDto.setPassword(password);
    	userDto.setRole("USER");
    	
    	userService.join(userDto);
        
        return "redirect:/login"; // 회원가입 후 로그인 페이지로 리다이렉트
    }
//    
//    @PostMapping("/join")
//    public ResponseEntity<String> join(@RequestBody UserDto userDto) {
//    	userService.join(userDto);
//    	return ResponseEntity.ok("회원가입 성공");
//    }

}
