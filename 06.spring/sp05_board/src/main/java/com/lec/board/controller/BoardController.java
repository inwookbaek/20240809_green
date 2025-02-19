package com.lec.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lec.board.domain.Board;
import com.lec.board.dto.BoardDTO;
import com.lec.board.dto.PageRequestDTO;
import com.lec.board.dto.PageResponseDTO;
import com.lec.board.service.BoardService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;
	
	@GetMapping("/list")
	public String list(PageRequestDTO pageRequestDTO, Model model) {
		PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);
		log.info("..........." + responseDTO);
		model.addAttribute("responseDTO", responseDTO);
		return "board/list";
	}
	
	@GetMapping({"/read", "/modify"})
	public void read(@RequestParam("bno") Long bno
			, PageRequestDTO pageRequestDTO, Model model) {
		log.info("read or modify....................");		
		BoardDTO boardDTO = boardService.readOne(bno);
		model.addAttribute("dto", boardDTO);
	}
	
	@GetMapping("/register")
	public void registerGet() {
		log.info("register.GET....................");
	}
	
	@PostMapping("/register")
	public String registerPost(@Valid BoardDTO boardDTO
			, BindingResult bindingResult
			, RedirectAttributes redirectAttributes) {
		log.info("register.Post....................");
		
		if(bindingResult.hasErrors()) {
			log.info("입력된 정보에 에러가 있습니다..........");
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			return "redirect:/board/register";
		}
		
		log.info("register......... " + boardDTO);
		
		Long bno = boardService.register(boardDTO);
		redirectAttributes.addFlashAttribute("result", bno);
		
		return "redirect:/board/list";
	}
	
	@PostMapping("/modify")
	public String modify(PageRequestDTO pageRequestDTO
			, @Valid BoardDTO boardDTO
			, BindingResult bindingResult
			, RedirectAttributes redirectAttributes) {
		log.info("modify.Post : " + boardDTO);
		
		if (bindingResult.hasErrors()) {
			
			log.info("입력된 정보에 에러가 있습니다.....");
			
			String link = pageRequestDTO.getLink();
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			redirectAttributes.addAttribute("bno", boardDTO.getBno());
			
			return "redirect:/board/modify?" + link;
		}
		
		boardService.modify(boardDTO);
		redirectAttributes.addFlashAttribute("result", "게시글수정성공!!!");
		redirectAttributes.addAttribute("bno", boardDTO.getBno());
		
		return "redirect:/board/read"; 
	}

	
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, RedirectAttributes redirectAttributes) {
		log.info("remove.Post....................");
		boardService.remove(bno);
		redirectAttributes.addFlashAttribute("result", "게시글삭제성공!!!");		
		return "redirect:/board/list"; 
	}
	
}
