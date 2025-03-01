package com.lec.spmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lec.spmvc.dto.TodoDTO;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class SampleContoller {

	// 일반적으로 화면이 따로 있는 경우는 리턴타입이 void나 String
	// JSON타입을 활용할 때 객체나 ResponseEntity타입을 주로 사용
	// 특히, void는 @RequestMapping값과 @GetMapping등 메서드에서 선언된 값을
	// 그대로 view이름으로 사용한다.
	// void는 동일한 이름의 화면뷰, String은 다른화면의 뷰를 보여준다.
	@GetMapping("/")
	public String index() {
		log.info("index.............");
		return "index";
	}
	
	@GetMapping("/hello")
	public String hello() {
		log.info("hello.............");
		return "hello";
	}
	
	
	@GetMapping(value = "/ex1")
	public void ex1(String name, String age) {
		log.info(String.format("name=%s, age=%d", name, Integer.parseInt(age)));
	}
	
	@GetMapping(value = "/ex2")
	public void ex2(@RequestParam(name = "name", defaultValue = "홍길동") String name
			, @RequestParam(name = "age", defaultValue = "1000") int age) {
		log.info(String.format("name=%s, age=%d", name, age));
	}	
	
	@GetMapping(value = "/ex3")
	public void ex3(Model model) {
		log.info("model.addAttribute ...............");
		model.addAttribute("message", "Hello World!!!");
	}	
	
	@GetMapping(value = "/ex4")
	public void ex4(TodoDTO todoDTO, Model model){
		todoDTO.setTno(2L);
		todoDTO.setTitle("제목....");
		todoDTO.setWriter("홍길동....");
		log.info(todoDTO);
		// model.addAttribute(todoDTO);
	}	
	
	@GetMapping(value = "/ex5")
	public void ex5(@ModelAttribute("dto") TodoDTO todoDTO, Model model){
		todoDTO.setTno(2L);
		todoDTO.setTitle("제목....");
		todoDTO.setWriter("홍길동....");
		log.info(todoDTO);
		// model.addAttribute(todoDTO);
	}	
	
	@GetMapping(value = "/ex6")
	public String ex6(RedirectAttributes redirectAttributes){
		redirectAttributes.addAttribute("name", "홍길동");
		redirectAttributes.addAttribute("result", "success");
		redirectAttributes.addFlashAttribute("onetime", "1회용데이터");
		// redirect는 다른화면으로 이동
		// forward는 브라우저의 URL은 고정하고 내부적으로 다른 URL을 처리하는 경우
		return "redirect:/ex7";
	}	
	
	@GetMapping(value = "/ex7")
	public void ex7(){

	}	
	
	@GetMapping(value = "/ex8") // 기본매개변수타입 String, 타입에러발생
	public void ex8(@RequestParam(name = "p1") String p1
			      , @RequestParam(name = "p2") int p2) {
		log.info("p1........ " + p1);
		log.info("p2........ " + p2);
	}	
}
