package com.lec.kakao.repository;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;

import com.lec.kakao.domain.Member;
import com.lec.kakao.domain.MemberRole;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class MemberepositoryTests {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insertMembers(){

        IntStream.rangeClosed(1,100).forEach(i -> {

            Member member = Member.builder()
                    .mid("member"+i)
                    .mpw(passwordEncoder.encode("1111"))
                    .email("email"+i+"@aaa.bbb")
                    .build();

            member.addRole(MemberRole.USER);

            if(i >= 90){
                member.addRole(MemberRole.ADMIN);
            }
            memberRepository.save(member);

        });

    }
    
    // p725 - 회원조회테스트
    // testRead 테스트할 때 
    // OpenJDK 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended
    // 에러가 발생할 경우 bundle.gradle에 아래 내용을 추가
	//    tasks.named('test') {
	//        useJUnitPlatform()
	//        jvmArgs '-Xshare:off' // JVM 아규먼트 설정
	//    }    
    @Test
    public void testRead() {

        Optional<Member> result = memberRepository.getWithRoles("member100");

        Member member = result.orElseThrow();

        log.info(member);
        log.info(member.getRoleSet());

        member.getRoleSet().forEach(memberRole -> log.info(memberRole.name()));
        
        log.info("///////////////////////////////////////////////");
        result = memberRepository.getWithRoles("member1");
        member = result.orElseThrow();
        log.info(member);
        log.info(member.getRoleSet());
        member.getRoleSet().forEach(memberRole -> log.info(memberRole.name()));

    }
    
    // p762
    @Commit
    @Test
    public void testUpdaate() {
    	String mid = "email1@aaa.bbb"; // 소셜로그인으로 추가된 사용자로 현재 DB에 존재하는 email
    	String mpw = passwordEncoder.encode("12345"); // 1111 -> 12345 변경
    	memberRepository.updatePassword(mpw, mid);
    }
    
    
}
