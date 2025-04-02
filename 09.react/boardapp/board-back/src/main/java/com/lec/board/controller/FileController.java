package com.lec.board.controller;

import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lec.board.service.FileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {

	private final FileService fileService;
	
	@PostMapping("/upload")
	public String upload(@RequestParam("file") MultipartFile file) {
		
		String url = fileService.upload(file);
		return url;
	}
	
	// @GetMapping 옵션
	// value : path
	// params : 특정 요청 매개변수 조건 설정
	// ... @GetMapping(params = "action=edit")  // ?action=edit 일 때만 매핑
	// ... @GetMapping(params = {"id", "!secret"})  // id는 있되 secret은 없어야 함
	// headers 도: 특정 HTTP 헤더 조건 설정
	// ... @GetMapping(headers = "X-API-VERSION=1")  // 특정 API 버전 헤더
	// ... @GetMapping(headers = {"Host=example.com", "Accept-Language=en-US"})
	// consumes : 요청의 Content-Type 제한
	// ... @GetMapping(consumes = "application/json")  // JSON만 허용
	// ... @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	// produces : 응답의 Content-Type 지정 및 Accept 헤더 기반 매핑, 미디어 타입 문자열
	// ... @GetMapping(produces = "text/html")  // HTML 응답
	// ... @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@GetMapping(value="{fileName}", produces={MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
	public Resource getImage(@PathVariable("fileName") String fileName) {
		
		Resource resouce = fileService.getImage(fileName);
		return resouce;
		
	}
}
