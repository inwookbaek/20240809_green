package com.lec.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lec.board.repository.MemberRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class MemberTests {

	@Autowired
	private MemberRepository memberRepository;
	
	@Test
	public void getMembers() {
		log.info(memberRepository.findAll());
	}
}
