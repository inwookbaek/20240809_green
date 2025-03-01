package com.lec.board.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lec.board.dto.PageRequestDTO;
import com.lec.board.dto.PageResponseDTO;
import com.lec.board.dto.ReplyDTO;
import com.lec.board.service.ReplyService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/replies")
@RequiredArgsConstructor
public class ReplyController {

	private final ReplyService replyService;
    
//	@Operation(summary = "Replies POST", description = "POST방식으로 댓글 등록하기!!")
//	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Map<String, Long>> register(@RequestBody ReplyDTO replyDTO) throws BindException {
//		
//		log.info(replyDTO);
//		
//		Map<String, Long> resultMap = new HashMap<>();
//		resultMap.put("rno", 111L);
//		
//		return ResponseEntity.ok(resultMap);
//	}
	

	@Operation(summary = "Replies POST", description = "POST방식으로 댓글 등록하기!!")
	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Map<String,Long> register(
	      @Valid @RequestBody ReplyDTO replyDTO,
	      BindingResult bindingResult) throws BindException{
	
	  log.info(replyDTO);
	
	  if(bindingResult.hasErrors()){
	      throw new BindException(bindingResult);
	  }
	
	  Map<String, Long> resultMap = new HashMap<>();
	  Long rno = replyService.register(replyDTO);
	  resultMap.put("rno", rno);
	
	  return resultMap;
	} 
	
	@Operation(summary = "댓글읽기", description = "GET방식으로 댓글 조회하기!!")
	@GetMapping("/{rno}")
	public ReplyDTO getReplyDTO(@PathVariable("rno") Long rno) {
		ReplyDTO replyDTO = replyService.read(rno);
		return replyDTO;
	}
	
	@Operation(summary = "댓글수정하기", description = "PUT방식으로 댓글 수정하기!!")
	@PutMapping(value = "/{rno}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Long> modify(@PathVariable("rno") Long rno
			, @RequestBody ReplyDTO replyDTO) {
		replyDTO.setRno(rno);
		replyService.modify(replyDTO);
		Map<String, Long> resultMap = new HashMap<>();
		resultMap.put("rno", rno);
		return resultMap;
	}
	
	@Operation(summary = "댓글삭제하기", description = "DELETE방식으로 댓글 삭제하기!!")
	@DeleteMapping("/{rno}")
	public Map<String, Long> remove(@PathVariable("rno") Long rno) {
		
		replyService.remove(rno);
		Map<String, Long> resultMap = new HashMap<>();
		resultMap.put("rno", rno);
		return resultMap;
	}
	
	@Operation(summary = "게시글/댓글목록조회", description = "GET방식으로 특정게시글의 댓글 조회하기!!")
	@GetMapping(value = "/list/{bno}")
	public PageResponseDTO<ReplyDTO> getList(@PathVariable("bno") Long bno
			, PageRequestDTO pageRequestDTO) {

		PageResponseDTO<ReplyDTO> responseDTO = replyService.getListOfBoard(bno, pageRequestDTO);
		return responseDTO;
		
	}
}
