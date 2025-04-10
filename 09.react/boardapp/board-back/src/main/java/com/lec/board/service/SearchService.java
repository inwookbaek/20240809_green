package com.lec.board.service;

import org.springframework.http.ResponseEntity;

import com.lec.board.dto.response.search.GetPopularListResponseDto;


public interface SearchService {

	ResponseEntity<? super GetPopularListResponseDto> getPopulaList();
	
}
