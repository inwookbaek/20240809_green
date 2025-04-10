package com.lec.board.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lec.board.dto.response.board.GetTop3BoardListResponseDto;
import com.lec.board.dto.response.search.GetPopularListResponseDto;
import com.lec.board.service.SearchService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value="/api/v1/search")
@RequiredArgsConstructor
public class SearchController {

	private final SearchService searchService;
	
	@GetMapping("/popular")
	public ResponseEntity<? super GetPopularListResponseDto> getPopularList() {
		ResponseEntity<? super GetPopularListResponseDto> response = searchService.getPopulaList();
		return response;
	}
}
