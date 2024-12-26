package com.lec.board.controller;

import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lec.board.dto.TodoDTO;
import com.lec.board.service.TodoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
public class TodoController {

	private final TodoService todoService;
	
	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Long> register(@RequestBody TodoDTO todoDTO) {
		log.info("todoDTO ====> " + todoDTO);
		Long tno = todoService.register(todoDTO);
		return Map.of("tno", tno);
	}
}
