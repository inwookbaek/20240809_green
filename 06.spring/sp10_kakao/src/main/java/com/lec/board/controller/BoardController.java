package com.lec.board.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.lec.board.dto.BoardListAllDTO;
import com.lec.board.dto.BoardListReplyCountDTO;
import com.lec.board.dto.PageRequestDTO;
import com.lec.board.dto.PageResponseDTO;
import com.lec.board.service.BoardService;
import com.lec.board.service.ReplyService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

	@Value("{com.lec.upload.path}")
	private String uploadPath;
	
	private final BoardService boardService;
	private final ReplyService replyService;
	
	@GetMapping("/list")
	// public String list(PageRequestDTO pageRequestDTO, Model model) {
	public void list(PageRequestDTO pageRequestDTO, Model model) {
		// 댓글카운트처리전
		//PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);
		
		// 댓글카운트처리
		// PageResponseDTO<BoardListReplyCountDTO> responseDTO = boardService.list(pageRequestDTO);
		
		// 첨부파일로직
		PageResponseDTO<BoardListAllDTO> responseDTO = boardService.listWithAll(pageRequestDTO);
		log.info("..........." + responseDTO);
		model.addAttribute("responseDTO", responseDTO);
		// return "board/list";
	}
	
	// 게시판 상세내역 조회는 로그인한 사용자만 조회하게 설정
	@PreAuthorize("isAuthenticated")
	@GetMapping({"/read", "/modify"})
	public void read(@RequestParam("bno") Long bno
			, PageRequestDTO pageRequestDTO, Model model) {
		log.info("read or modify....................");		
		BoardDTO boardDTO = boardService.readOne(bno);
		model.addAttribute("dto", boardDTO);
	}
	
	/*
		@PreAuthorize / @PostAuthorize 의 접근제한 표현식
		
		authenticated()    : 인증된 사용자들만 허용
		permitAll()        : 모두 허용
		anonymous()        : 익명의 사용자 허용
		hasRole(표현식)    : 특정한 권한이 있는 사용자만 허용
		hasAnyRole(표현식) : 여러 권한중 하나만 존재해도 허용ㄹ 
	*/
	@PreAuthorize("hasRole('USER')") // or ROLE_USER
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
	
	// principal.username 현재로그인된 사용자
	// #boardDTO.writer 현재 게시글(boardDTO)작성자
	@PreAuthorize("principal.username == #writer")
	@PostMapping("/modify")
	public String modify(
			  PageRequestDTO pageRequestDTO
			, @RequestParam(name = "writer", defaultValue = "") String writer
			, @Valid BoardDTO boardDTO
			, BindingResult bindingResult
			, RedirectAttributes redirectAttributes) {
		log.info("modify.Post : ---------------------> " + boardDTO);
		
		if (bindingResult.hasErrors()) {
			
			log.info("입력된 정보에 에러가 있습니다.....");
			
			String link = pageRequestDTO.getLink();
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			redirectAttributes.addAttribute("bno", boardDTO.getBno());
			
			return "redirect:/board/modify?bno=" 
				+ boardDTO.getBno() 
				+ "&writer=" + boardDTO.getWriter() 
				+ "&" + link ;
		}
		
		boardService.modify(boardDTO);
		redirectAttributes.addFlashAttribute("result", "게시글수정성공!!!");
		redirectAttributes.addAttribute("bno", boardDTO.getBno());
		
		return "redirect:/board/read"; 
	}
	
//	@PostMapping("/remove")
//	public String remove(@RequestParam("bno") Long bno, RedirectAttributes redirectAttributes) {
//		log.info("remove.Post....................");
//		boardService.remove(bno);
//		redirectAttributes.addFlashAttribute("result", "게시글삭제성공!!!");		
//		return "redirect:/board/list"; 
//	}
	
	@PreAuthorize("principal.username == #writer")
	@PostMapping("/remove")
	public String remove(
			  @RequestParam(name = "writer", defaultValue = "") String writer
			, @Valid BoardDTO boardDTO
			, RedirectAttributes redirectAttributes) {
		
		Long bno = boardDTO.getBno();
		log.info("remove.Post....................");
		
		replyService.remove(bno);
		boardService.remove(bno);
		
		// 원본게시물삭제시 첨부파일도 삭제처리
		log.info(boardDTO.getFileNames());
		List<String> fileNames = boardDTO.getFileNames();
		if(fileNames != null && fileNames.size() > 0) {
			removeFiles(fileNames);
		}
		
		redirectAttributes.addFlashAttribute("result", "게시글삭제성공!!!");		
		return "redirect:/board/list"; 
	}	
	
	public void removeFiles(List<String> fileNames) {
		for(String fileName : fileNames) {
			Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);
			String resourceName = resource.getFilename();
			
			try {
				String contentType = Files.probeContentType(resource.getFile().toPath());
				resource.getFile().delete();
				
				// 썸네일이 존재한다면
				if(contentType.startsWith("image")) {
					File thumbnailFile = new File(uploadPath + File.separator + fileName);
					thumbnailFile.delete();
				}
				
			} catch (IOException e) {
				log.error(e.getMessage());
			}
		}
	}
}
