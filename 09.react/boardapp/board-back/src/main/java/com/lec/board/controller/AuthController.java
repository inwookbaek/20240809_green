package com.lec.board.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lec.board.dto.request.auth.SignInRequestDto;
import com.lec.board.dto.request.auth.SignUpRequestDto;
import com.lec.board.dto.response.SignInResponseDto;
import com.lec.board.dto.response.auth.SignUpResponseDto;
import com.lec.board.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;
	
	/*
	{
	    "email": "example@gmail.com",
	    "password": "P!ssw0rd",
	    "nickname": "hong",
	    "telNumber": "01011112222",
	    "address": "경기도 안양시 만안구",
	    "addressDetail": "래미안메가트리아",
	    "agreedPersonal": true
	}
	*/
	
	@PostMapping("/sign-up")
	public ResponseEntity<? super SignUpResponseDto> signUp(
			@RequestBody @Valid SignUpRequestDto requestBody) {
		ResponseEntity<? super SignUpResponseDto> response = authService.signUp(requestBody);
		return response;
	}
	
	
	@PostMapping("/sign-in")
	public ResponseEntity<? super SignInResponseDto> signIn(
			@RequestBody @Valid SignInRequestDto requestBody) {
		
		log.info(requestBody.toString());
		
		ResponseEntity<? super SignInResponseDto> response = authService.signIn(requestBody);
		return response;
	}
	
	@GetMapping("/hello")
	public String hello() {
		return "[GET] Hello World!!!!";
	}	
	
	@PostMapping("/hello")
	public String helloPost() {
		System.out.println("3. ================> [POST] Hello World!!!!");
		return "[POST] Hello World!!!!";
	}	
}
