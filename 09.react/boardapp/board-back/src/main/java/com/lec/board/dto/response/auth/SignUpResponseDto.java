package com.lec.board.dto.response.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.lec.board.common.ResponseCode;
import com.lec.board.common.ResponseMessage;
import com.lec.board.dto.response.ResponseDto;

public class SignUpResponseDto extends ResponseDto {
	
	private SignUpResponseDto() {
		super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
	}

	public static ResponseEntity<SignUpResponseDto> success(){
		SignUpResponseDto result = new SignUpResponseDto();
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	public static ResponseEntity<ResponseDto> duplicateEmail() {
		ResponseDto result = new ResponseDto(ResponseCode.DUPLICATE_EMAIL, ResponseMessage.DUPLICATE_EMAIL);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
	}
	
	public static ResponseEntity<ResponseDto> duplicateNickname() {
		ResponseDto result = new ResponseDto(ResponseCode.DUPLICATE_NICKNAME, ResponseMessage.DUPLICATE_NICKNAME);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
	}
	
	public static ResponseEntity<ResponseDto> duplicateTelNumber() {
		ResponseDto result = new ResponseDto(ResponseCode.DUPLICATE_TEIL_NUMBER, ResponseMessage.DUPLICATE_TEIL_NUMBER);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
	}
	
}
