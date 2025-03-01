package com.lec.boot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
public class SampleRestController {

	public SampleRestController() {
		log.info("SampleRestController 객체 생성....");
	}
	
	@GetMapping("/helloRest")
	public String[] helloRest() {
		return new String[] {"손흥민","홍길동","이강인","김민재","화희찬"};
	}
}
