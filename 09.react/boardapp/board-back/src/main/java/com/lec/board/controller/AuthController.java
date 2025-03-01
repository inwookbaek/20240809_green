package com.lec.board.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lec.board.dto.request.auth.SignUpRequestDto;
import com.lec.board.dto.response.auth.SignUpResponseDto;
import com.lec.board.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

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
		return authService.signUp(requestBody);
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
