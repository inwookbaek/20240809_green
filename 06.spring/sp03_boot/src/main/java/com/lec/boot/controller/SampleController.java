package com.lec.boot.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class SampleController {

	public SampleController() {
		System.out.println("SampleController 객체 생성!!!");
	}
	
	@GetMapping("/hello")
	public void hello(Model model) {
		log.info("Hello......................");
		model.addAttribute("msg", "Hello World!!!");
	}
	
	@GetMapping("/ex01")
	public void ex01(Model model) {
		log.info("ex01......................");
		List<String> names = Arrays.asList("손흥민","홍길동","이강인","김민재","황희찬");
		model.addAttribute("names", names);
	}
	
	@GetMapping("/ex02")
	public void ex02(Model model) {
		log.info("ex02......................");
		List<String> strList = IntStream.range(1, 11)     // 1~10
									.mapToObj(i -> "Data_" + i)
									.collect(Collectors.toList());
		model.addAttribute("list", strList);
		
		Map<String, String> map = new HashMap<>();
		map.put("A", "AAAA");
		map.put("B", "BBBB");
		model.addAttribute("map", map);
		
		BoardDTO dto = new BoardDTO();
		dto.bno = 10;
		dto.writer = "홍길동";
		dto.title = "글제목.......";
		model.addAttribute("dto", dto);	
	}
	
	@GetMapping("/ex03")
	public void ex03(Model model) {
		log.info("ex03......................");
		model.addAttribute("arr", new String[] {"손흥민","이강인","김민재"});	
		
	}
}

class BoardDTO {
	public int bno;
	public String writer;
	public String title;
	public int getBno() {
		return bno;
	}
	public String getWriter() {
		return writer;
	}
	public String getTitle() {
		return title;
	}
	@Override
	public String toString() {
		return "BoardDTO [bno=" + bno + ", writer=" + writer + ", title=" + title + "]";
	}
}














