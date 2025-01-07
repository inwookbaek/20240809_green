package com.lec.board.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/sample")
public class SampleController {

	@Operation(summary = "샘플 GET Mapping", description = "doA")
	@GetMapping("/doA") 
	public List<String> doA() {
		return Arrays.asList("손흥민","이강인","김민재","홍길동","소향");
	}
}
