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

	@GetMapping("/hello")
	public void hello(Model model) {
		log.info("hello.............");
		model.addAttribute("msg", "HELLO WORLD!!!");
	}
	
	@GetMapping("/ex01") 
	public void thymeleaf(Model model) 	{
		List<String> names = Arrays.asList("홍길동","손흥민","이강인","김민재", "황희찬");
		model.addAttribute("names", names);
	}
	
    @GetMapping("/ex02")
    public void ex2(Model model) {

        log.info("ex02................");

        List<String> strList = IntStream.range(1,10)
                .mapToObj(i -> "Data"+i)
                .collect(Collectors.toList());

        model.addAttribute("list", strList);

        Map<String, String> map = new HashMap<>();
        map.put("A","AAAA");
        map.put("B","BBBB");

        model.addAttribute("map", map);

        SampleDTO sampleDTO = new SampleDTO();
        sampleDTO.p1 = "Value -- p1";
        sampleDTO.p2 = "Value -- p2";
        sampleDTO.p3 = "Value -- p3";

        model.addAttribute("dto", sampleDTO);
    }

    @GetMapping("/ex03")
    public void ex3(Model model) {

        model.addAttribute("arr", new String[]{"AAA","BBB","CCC"});

    }
}

class SampleDTO {
	
	public String p1, p2, p3;

	public String getP1() {
		return p1;
	}

	public String getP2() {
		return p2;
	}

	public String getP3() {
		return p3;
	}
	
}
