package com.lec.board.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.lec.board.domain.APIUser;

@SpringBootTest
public class APIUserRepositoryTests {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private APIUserRepository apiUserRepository;
	
	@Test
	public void testInserts() {
		IntStream.rangeClosed(1, 100)
		         .forEach(i -> {
		        	 APIUser apiUser = APIUser.builder()
		        			 .mid("apiuser"+ i)
		        			 .mpw(passwordEncoder.encode("12345"))
		        			 .build();
		        	 apiUserRepository.save(apiUser);
		         });
	}
}