package com.lec.board.dto.response.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.lec.board.common.ResponseCode;
import com.lec.board.common.ResponseMessage;
import com.lec.board.dto.response.ResponseDto;
import com.lec.board.entity.UserEntity;

import lombok.Getter;

@Getter
public class PatchNicknameResponseDto extends ResponseDto {


	public PatchNicknameResponseDto() {
		super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
	}
		
	public static ResponseEntity<PatchNicknameResponseDto> success() {
		PatchNicknameResponseDto result = new PatchNicknameResponseDto();
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	public static ResponseEntity<ResponseDto> notExistUser() {
		ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseCode.NOT_EXISTED_USER);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
	}
	
	public static ResponseEntity<ResponseDto> duplicateNickame() {
		ResponseDto result = new ResponseDto(ResponseCode.DUPLICATE_NICKNAME, ResponseCode.DUPLICATE_NICKNAME);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
	}

}
