package com.lec.kakao.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lec.kakao.dto.MemberJoinDTO;
import com.lec.kakao.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/member")
@Log4j2
@RequiredArgsConstructor
public class MemberController {

	// p694
    @GetMapping({"/login", "/login/{error}/{logout}", "/login/{logout}"})
    public void loginGet(
    		@RequestParam(name = "error", defaultValue = "") @PathVariable Optional<String> error, 
    		@RequestParam(name = "logout", defaultValue = "") @PathVariable Optional<String> logout) {
        log.info("login get ............");
        log.info("logout : " + logout);
        
        // p701
        if(logout != null) {
        	log.info("user logout ...................");        	
        }
    }

    // p731
    @GetMapping("/join")
    public void joinGET(){
        log.info("join get...");
    }
    
    // p736 주석처리
//  @PostMapping("/join")
//  public String joinPOST(MemberJoinDTO memberJoinDTO){
//
//      log.info("join post...");
//      log.info(memberJoinDTO);
//
//      return "redirect:/board/list";
//  }
 
    // p736 joinPOST수정
    // 의존성주입
    private final MemberService memberService;
  
    @PostMapping("/join")
    public String joinPOST(MemberJoinDTO memberJoinDTO, RedirectAttributes redirectAttributes){

    	log.info("join post...");
    	log.info(memberJoinDTO);

    	try {
    		memberService.join(memberJoinDTO);
    	} catch (MemberService.MidExistException e) {

    		redirectAttributes.addFlashAttribute("error", "mid");
    		return "redirect:/member/join";
    	}

    	redirectAttributes.addFlashAttribute("result", "success");

    	return "redirect:/member/login"; //회원 가입 후 로그인
  }   
}
