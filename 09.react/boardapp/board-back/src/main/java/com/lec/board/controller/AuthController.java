package com.lec.board.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lec.board.dto.request.auth.SignInRequestDto;
import com.lec.board.dto.request.auth.SignUpRequestDto;
import com.lec.board.dto.response.auth.SignInResponseDto;
import com.lec.board.dto.response.auth.SignUpResponseDto;
import com.lec.board.service.AuthService;
import com.lec.board.service.JwtService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;
    private final JwtService jwtService;
    
	/*
	postman등에서 실행할 경우 Authorization탭에서 Auth Type 기본값(Inherit auth from parent)로
	진행하면 정상적으로 DB에 추가 된다!!!!!!!
	{
	    "email": "iwbaek@gmail.com",
	    "password": "P!ssw0rd",
	    "nickname": "이누기",
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
	
	/*
	{
	    "email": "iwbaek@gmail.com",
	    "password": "P!ssw0rd"
	}
	*/
	@PostMapping("/sign-in")
	public ResponseEntity<? super SignInResponseDto> signIn(
			@RequestBody @Valid SignInRequestDto requestBody) {
		ResponseEntity<? super SignInResponseDto> response = authService.signIn(requestBody);
		
		log.info("email ==========> " + requestBody.getEmail());
		log.info("password ==========> " + requestBody.getPassword());		
		
		return response;
	}
	
}
