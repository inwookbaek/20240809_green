package com.lec.spmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lec.spmvc.dto.TodoDTO;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/todo")
@Log4j2
public class TodoController {
	
	@RequestMapping("/list")
	public void list() {
		log.info("todo list...........");
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registerGet() {
		log.info("GET : todo register...........  /todo/register");
	}
	
	// @PostMapping(value = "/register")
	public void registerPost() {
		log.info("POST : todo register...........");
	}
	
	@PostMapping(value = "/register")
	public void registerPost(TodoDTO todoDTO) {
		log.info("POST : todo register...........");
		log.info(todoDTO);
	}
	
}
