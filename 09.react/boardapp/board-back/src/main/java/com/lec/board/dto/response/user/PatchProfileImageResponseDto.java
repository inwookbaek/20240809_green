package com.lec.board.dto.response.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.lec.board.common.ResponseCode;
import com.lec.board.common.ResponseMessage;
import com.lec.board.dto.response.ResponseDto;
import com.lec.board.entity.UserEntity;

import lombok.Getter;

@Getter
public class PatchProfileImageResponseDto extends ResponseDto {


	public PatchProfileImageResponseDto() {
		super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
	}
		
	public static ResponseEntity<PatchProfileImageResponseDto> success() {
		PatchProfileImageResponseDto result = new PatchProfileImageResponseDto();
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	public static ResponseEntity<ResponseDto> notExistUser() {
		ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseCode.NOT_EXISTED_USER);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
	}

}
