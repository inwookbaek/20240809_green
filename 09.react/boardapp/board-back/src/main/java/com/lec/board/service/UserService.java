package com.lec.board.service;

import org.springframework.http.ResponseEntity;

import com.lec.board.dto.response.user.GetSignInUserResponseDto;

public interface UserService {
	                       
	ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String email);
}
