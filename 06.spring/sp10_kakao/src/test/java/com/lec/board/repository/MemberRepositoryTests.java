package com.lec.board.repository;

import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.lec.board.domain.Member;
import com.lec.board.domain.MemberRole;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class MemberRepositoryTests {

	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Test
	public void insertMambers() {
		IntStream.rangeClosed(1, 100).forEach(i -> {
			Member member = Member.builder()
					.mid("member" + i)
					.mpw(passwordEncoder.encode("12345"))
					.email(String.format("email%d@gmail.com", i))
					.build();
			
			member.addRole(MemberRole.USER);
			
			if(i >=90) {
				member.addRole(MemberRole.ADMIN);
			}
			
			memberRepository.save(member);
		});
	}
	
	@Test
	public void testRead() {
		
		Optional<Member> result = memberRepository.getWithRoles("member100");
		Member member = result.orElseThrow();
		
		log.info(member);
		log.info(member.getRoleSet());
		
		member.getRoleSet().forEach(memberRole -> log.info(memberRole.name()));
	}
	
	@Test
	public void testUpdate() {
		String mid = "hong@hotmail.com"; // 소셜로그인으로 추가된 사용자 이메일(DB)
		String mpw = passwordEncoder.encode("54321");
		memberRepository.updatePassword(mpw, mid);
	}
	
}














