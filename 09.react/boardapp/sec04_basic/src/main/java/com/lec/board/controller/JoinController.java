package com.lec.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.lec.board.dto.JoinDTO;
import com.lec.board.service.JoinService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequiredArgsConstructor
public class JoinController {
	
	private final JoinService joinService;
	
	@GetMapping("join")
	public String joinPage() {
		return "join";
	}

	@PostMapping("/joinProc") 
	public String joinProcess(JoinDTO joinDTO) {
		log.info("===> JoinDTO : " + joinDTO.toString());
		joinService.joinProcess(joinDTO);
		return "redirect:/login";
	}
}
