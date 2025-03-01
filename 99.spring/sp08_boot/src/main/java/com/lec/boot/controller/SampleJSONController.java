package com.lec.boot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
public class SampleJSONController {

	@GetMapping("/helloArr")
	public String[] helloArr() {
		log.info("helloArr ..............");
		return new String[] {"홍길동","손흥민","이강인","김민재", "황희찬"};
	}
}
