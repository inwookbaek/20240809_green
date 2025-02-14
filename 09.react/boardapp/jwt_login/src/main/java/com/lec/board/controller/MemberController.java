package com.lec.board.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lec.board.dto.MemberDto;
import com.lec.board.jwt.JwtToken;
import com.lec.board.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
	이전에 SecurityConfig에서 정의한 보안 설정은 다음과 같다.

	"members/sign-in"  ➡︎ 모든 사용자에게 허용
	"members/test"  ➡︎ USER 권한을 가진 사용자에게 허용

	테스트과정

	Postman으로 이전에 DB에 저장했던 회원 정보(username, password)를 body에 담아서 "members/sign-in"으로 요청
	성공적으로 Access Token 발급
	발급받은 Access Token을 header에 넣어 "members/test"로 요청
*/
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
// @CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/login")
    public JwtToken signIn(@RequestBody MemberDto signInDto) {
        String username = signInDto.getUsername();
        String password = signInDto.getPassword();
        JwtToken jwtToken = memberService.signIn(username, password);
        log.info("request username = {}, password = {}", username, password);
        log.info("jwtToken accessToken = {}, refreshToken = {}", jwtToken.getAccessToken(), jwtToken.getRefreshToken());
        return jwtToken;
    }

    @GetMapping("/test")
    public String test() {
    	log.info("4. ========> test");
    	
        return "-----------------------> test success";
    }
}
