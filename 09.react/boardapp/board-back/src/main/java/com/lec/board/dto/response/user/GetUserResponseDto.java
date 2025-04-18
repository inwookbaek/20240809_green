package com.lec.board.dto.response.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.lec.board.common.ResponseCode;
import com.lec.board.common.ResponseMessage;
import com.lec.board.dto.response.ResponseDto;
import com.lec.board.entity.UserEntity;

import lombok.Getter;

@Getter
public class GetUserResponseDto extends ResponseDto {

	private String email;
	private String nickname;
	private String profileImage;
	
	public GetUserResponseDto(UserEntity userEntity) {
		super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
		this.email = userEntity.getEmail();
		this.nickname = userEntity.getNickname();
		this.profileImage = userEntity.getProfileImage();
	}
	
	public static ResponseEntity<GetUserResponseDto> success(UserEntity userEntity) {
		GetUserResponseDto result = new GetUserResponseDto(userEntity);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	public static ResponseEntity<ResponseDto> notExistUser() {
		ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseCode.NOT_EXISTED_USER);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
	}
}
