package com.lec.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.lec.board.dto.JoinDTO;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class LoginController {
	
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
	
}
