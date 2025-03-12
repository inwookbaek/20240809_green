package com.lec.board.service;

import org.springframework.http.ResponseEntity;

import com.lec.board.dto.request.auth.SignInRequestDto;
import com.lec.board.dto.request.auth.SignUpRequestDto;
import com.lec.board.dto.response.SignInResponseDto;
import com.lec.board.dto.response.auth.SignUpResponseDto;

public interface AuthService {
	
	ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto);
	ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto);
	
}
