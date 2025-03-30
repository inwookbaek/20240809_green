package com.lec.board.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lec.board.dto.response.user.GetSignInUserResponseDto;
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
			if(userEntity ==null) return GetSignInUserResponseDto.notExistUser();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GetSignInUserResponseDto.success(userEntity);
	}

}
