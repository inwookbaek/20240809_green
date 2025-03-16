package com.lec.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class AdminController {

	@GetMapping("/admin")
	public String adminPage() {
		return "admin";
	}
}
