package com.lec.board.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lec.board.BoardBackApplication;
import com.lec.board.dto.request.board.PatchBoardRequestDto;
import com.lec.board.dto.request.board.PostBoardRequestDto;
import com.lec.board.dto.request.board.PostCommentRequestDto;
import com.lec.board.dto.response.board.DeleteBoardResponseDto;
import com.lec.board.dto.response.board.GetBoardResponseDto;
import com.lec.board.dto.response.board.GetCommentListResponseDto;
import com.lec.board.dto.response.board.GetFavoriteListResponseDto;
import com.lec.board.dto.response.board.GetLatestBoardListResponseDto;
import com.lec.board.dto.response.board.GetSearchBoardListResponseDto;
import com.lec.board.dto.response.board.GetTop3BoardListResponseDto;
import com.lec.board.dto.response.board.GetUserBoardListRestponseDto;
import com.lec.board.dto.response.board.IncreaseViewCountResponseDto;
import com.lec.board.dto.response.board.PatchBoardResponseDto;
import com.lec.board.dto.response.board.PostBoardResponseDto;
import com.lec.board.dto.response.board.PostCommentResponseDto;
import com.lec.board.dto.response.board.PutFavoriteResponseDto;
import com.lec.board.service.BoardService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/api/v1/board")
@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;
	
	@GetMapping("/{boardNumber}")
	public ResponseEntity<? super GetBoardResponseDto> getBoard(
			@PathVariable("boardNumber") Integer boardNumber) {
		ResponseEntity<? super GetBoardResponseDto> response = boardService.getBoard(boardNumber);
		return response;
	}
	
	// @AuthenticationPrincipal : 현재 인증(로그인)된 사용자의 정보를 쉽게 접근
	// SecurityContextHolder에서 직접 꺼내는 번거로움을 없애줌
	/* postman 
		{
		    "title": "제목입니다!!",
		    "content": "내용입니다!!!",
		    "boardImageList": [	
		        "http://localhost:8090/file/a43d684c-b593-44aa-9cc7-b61a37e05ae5.jpg",
		        "http://localhost:8090/file/10f19800-5a6b-49e2-a2f1-1cab7e16d8f5.jpg" 
		    ]
		}
	*/
	@PostMapping("")
	public ResponseEntity<? super PostBoardResponseDto> postBoard(
			@RequestBody @Valid PostBoardRequestDto requestBody,
			@AuthenticationPrincipal String email) {  		
		ResponseEntity<? super PostBoardResponseDto> response = boardService.postBoard(requestBody, email);		
		return response;
	}
	
	@PutMapping("/{boardNumber}/favorite")
	public ResponseEntity<? super PutFavoriteResponseDto> putFavorite(
			@PathVariable("boardNumber") Integer boardNumber,
			@AuthenticationPrincipal String email) { 
		ResponseEntity<? super PutFavoriteResponseDto> response = boardService.putFavorite(boardNumber, email);		
		return response;
	}
	
	@GetMapping("/{boardNumber}/favorite-list")
	public ResponseEntity<? super GetFavoriteListResponseDto> getFavoriteList(
			@PathVariable("boardNumber") Integer boardNumber) { 
	
		ResponseEntity<? super GetFavoriteListResponseDto> response = boardService.getFavoritList(boardNumber);	
		return response;
	}
	
	@PostMapping("/{boardNumber}/comment")
	public ResponseEntity<? super PostCommentResponseDto> postComment(
			@RequestBody @Valid PostCommentRequestDto requestBody,
			@PathVariable("boardNumber") Integer boardNumber,
			@AuthenticationPrincipal String email) { 
		
		ResponseEntity<? super PostCommentResponseDto> response = boardService.postComment(requestBody, boardNumber, email);	
		return response;
	}
	
	@GetMapping("/{boardNumber}/comment-list")
	public ResponseEntity<? super GetCommentListResponseDto> getCommentList(
			@PathVariable("boardNumber") Integer boardNumber) { 
	
		ResponseEntity<? super GetCommentListResponseDto> response = boardService.getCommentList(boardNumber);	
		return response;
	}
	
	@GetMapping("/{boardNumber}/increase-view-count")
	public ResponseEntity<? super IncreaseViewCountResponseDto> increaseViewCount(
			@PathVariable("boardNumber") Integer boardNumber) { 
		ResponseEntity<? super IncreaseViewCountResponseDto> response = boardService.increaseViewCount(boardNumber);
		return response;
	}
	
	@DeleteMapping("/{boardNumber}")
	public ResponseEntity<? super DeleteBoardResponseDto> deleteBoard(
			@PathVariable("boardNumber") Integer boardNumber,
			@AuthenticationPrincipal String email) {  	
		ResponseEntity<? super DeleteBoardResponseDto> response = boardService.deleteBoard(boardNumber, email);
		return response;
	}

	@PatchMapping("/{boardNumber}")
	public ResponseEntity<? super PatchBoardResponseDto> patchBoard(
			@RequestBody @Valid PatchBoardRequestDto dto,
			@PathVariable("boardNumber") Integer boardNumber,
			@AuthenticationPrincipal String email) { 
		ResponseEntity<? super PatchBoardResponseDto> response = boardService.patchBoard(dto, boardNumber, email);		
		return response;
	}
	
	@GetMapping("/latest")
	public ResponseEntity<? super GetLatestBoardListResponseDto> getLatestBoardList() { 
		ResponseEntity<? super GetLatestBoardListResponseDto> response = boardService.getLatestBoardList();
		return response;
	}
	
	@GetMapping("/top3")
	public ResponseEntity<? super GetTop3BoardListResponseDto> getTop3BoardList() { 
		ResponseEntity<? super GetTop3BoardListResponseDto> response = boardService.getTop3BoardList();
		return response;
	}
	
	@GetMapping(value={"/search-list/{searchWord}", "/search-list/{searchWord}/{preSearchWord}"})
	public ResponseEntity<? super GetSearchBoardListResponseDto> getSearchBoardList(
			@PathVariable("searchWord") String searchWord,
			@PathVariable(value="preSearchWord", required = false) String preSearchWord) {				
		ResponseEntity<? super GetSearchBoardListResponseDto> response = boardService.getSearchBoardList(searchWord, preSearchWord);
		return response;
	}
	
	
	@GetMapping("/user-board-list/{email}")
	public ResponseEntity<? super GetUserBoardListRestponseDto> getUserBoardList(
			@PathVariable("email") String email) {		
		log.info("email ===> " + email);
		ResponseEntity<? super GetUserBoardListRestponseDto> response = boardService.getUserBoardList(email);
		return response;
	}
	
}
