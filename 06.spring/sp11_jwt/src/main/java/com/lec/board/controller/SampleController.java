package com.lec.board.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/sample")
public class SampleController {

	@Operation(summary = "Sample GET Mappint", description = "doA")
	@GetMapping("doA")
	// p874
	@PreAuthorize("hasRole('ROLE_USER')")   
	public List<String> doA() {
		return Arrays.asList("손흥민","이강인","김민재","황희찬");
	}
	
	// ps74 start
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/doB")
    public List<String> doB() {
        return Arrays.asList("AdminAAA","AdminBBB","AdminCCC");
    }	
    // ps74 end
}
