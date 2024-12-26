package com.lec.board.security;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lec.board.domain.APIUser;
import com.lec.board.dto.APIUserDTO;
import com.lec.board.repository.APIUserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class APIUserDetailsService implements UserDetailsService {
	
	private final APIUserRepository apiUserRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {
		
		Optional<APIUser> result = apiUserRepository.findById(username);
		APIUser apiUser = result.orElseThrow(
				() -> new UsernameNotFoundException("회원아이디를 찾지 못했습니다!!"));
		
		log.info("----------- APIUserDetailsService apiUser -----------------");
		
		APIUserDTO dto = new APIUserDTO(
				apiUser.getMid(), 
				apiUser.getMpw(), 
				List.of(new SimpleGrantedAuthority("ROLE_USER")));
		
		log.info("apiUserDTO ==> " + dto);
		
		return dto;
	}

}
