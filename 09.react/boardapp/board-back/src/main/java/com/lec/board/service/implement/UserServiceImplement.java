package com.lec.board.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lec.board.dto.request.user.PatchNicknameRequestDto;
import com.lec.board.dto.request.user.PatchProfileImageRequestDto;
import com.lec.board.dto.response.ResponseDto;
import com.lec.board.dto.response.user.GetSignInUserResponseDto;
import com.lec.board.dto.response.user.GetUserResponseDto;
import com.lec.board.dto.response.user.PatchNicknameResponseDto;
import com.lec.board.dto.response.user.PatchProfileImageResponseDto;
import com.lec.board.entity.UserEntity;
import com.lec.board.repository.UserRepository;
import com.lec.board.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImplement  implements UserService {
	
	private final UserRepository userRepository;

	@Override
	public ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String email) {
		
		UserEntity userEntity = null;
		
		try {
			userEntity = userRepository.findByEmail(email);
			if(userEntity == null) return GetSignInUserResponseDto.notExistUser();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseDto.databaseError();
		}
		return GetSignInUserResponseDto.success(userEntity);
	}

	@Override
	public ResponseEntity<? super GetUserResponseDto> getUser(String email) {

		UserEntity userEntity = null;

		try {
			userEntity = userRepository.findByEmail(email);
			if(userEntity == null) return GetUserResponseDto.notExistUser();		
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseDto.databaseError();
		}
		return GetUserResponseDto.success(userEntity);
	}

	@Override
	public ResponseEntity<? super PatchNicknameResponseDto> patchNickname(PatchNicknameRequestDto dto, String email) {
			
		try {
			UserEntity userEntity = userRepository.findByEmail(email);
			if(userEntity == null) return PatchNicknameResponseDto.notExistUser();
			
			String nickname = dto.getNickname();
			boolean existedNickname = userRepository.existsByNickname(nickname);
			if(existedNickname) return PatchNicknameResponseDto.duplicateNickame();			
			userEntity.setNickname(nickname);
			userRepository.save(userEntity);
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseDto.databaseError();
		}
		return PatchNicknameResponseDto.success();
	}

	@Override
	public ResponseEntity<? super PatchProfileImageResponseDto> patchProfileImage(PatchProfileImageRequestDto dto, String email) {
		
		try {
			UserEntity userEntity = userRepository.findByEmail(email);
			if(userEntity == null) return PatchProfileImageResponseDto.notExistUser();	
			
			String profieImage = dto.getProfileImage();
			userEntity.setProfileImage(profieImage);
			userRepository.save(userEntity);			
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseDto.databaseError();
		}
		return PatchProfileImageResponseDto.success();
	}

}
