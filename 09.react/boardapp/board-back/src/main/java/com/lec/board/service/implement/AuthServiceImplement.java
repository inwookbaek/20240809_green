package com.lec.board.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lec.board.dto.request.auth.SignInRequestDto;
import com.lec.board.dto.request.auth.SignUpRequestDto;
import com.lec.board.dto.response.ResponseDto;
import com.lec.board.dto.response.auth.SignInResponseDto;
import com.lec.board.dto.response.auth.SignUpResponseDto;
import com.lec.board.entity.UserEntity;
import com.lec.board.repository.UserRepository;
import com.lec.board.service.AuthService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {
	
	private final UserRepository userRepository;
	// private final JwtProvider jwtProvider;// = new JwtProvider();
	private final JwtServiceImpl jwtService;// = new JwtProvider();
	
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	public ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto) {
			
		try {
			String email = dto.getEmail();
			boolean existedEmail = userRepository.existsByEmail(email);
			if(existedEmail) return SignUpResponseDto.duplicateEmail();
			
			String nickname = dto.getNickname();
			boolean existedNickname = userRepository.existsByNickname(nickname);
			if(existedNickname) return SignUpResponseDto.duplicateNickname();
				
			String telNumber = dto.getTelNumber();
			boolean existedTelNumber = userRepository.existsByTelNumber(telNumber);
			if(existedTelNumber) return SignUpResponseDto.duplicateTelNumber();
			
			String password = dto.getPassword();
			String encodedPassword = passwordEncoder.encode(password);
			dto.setPassword(encodedPassword);
			
			UserEntity userEntity = new UserEntity(dto);			
			userRepository.save(userEntity);
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseDto.databaseError();
		}
		return SignUpResponseDto.success();
	}

	@Override
	public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto) {
		
		String token = null;	
		
		try {
			
			String email = dto.getEmail();
						
			UserEntity userEntity = userRepository.findByEmail(email);
			if(userEntity == null) return SignInResponseDto.signInFailed();
			
			String password = dto.getPassword();
			String encodedPassword = userEntity.getPassword();
			boolean isMached = passwordEncoder.matches(password, encodedPassword);				
			
			if(!isMached) return SignInResponseDto.signInFailed();
			
			// token = jwtProvider.create(email);
			token = jwtService.generateToken(email);
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseDto.databaseError();
		}
		return SignInResponseDto.success(token);
	}


}
