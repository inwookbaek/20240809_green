package com.lec.board.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lec.board.dto.JoinDTO;
import com.lec.board.entity.UserEntity;
import com.lec.board.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class JoinService {
	
	private final UserRepository userRepository;
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public void joinProcess(JoinDTO joinDTO) {
		
		// 중복확인
		boolean isUser = userRepository.existsByUsername(joinDTO.getUsername());
		if(isUser) {
			log.info("===> Username(" + joinDTO.getUsername() + ") Duplicated");
			return;
		}
	
		UserEntity userEntity = new UserEntity();		
		userEntity.setUsername(joinDTO.getUsername());
		userEntity.setPassword(passwordEncoder.encode(joinDTO.getPassword()));
		userEntity.setRole("ROLE_ADMIN");
		
		userRepository.save(userEntity);
	}
}
