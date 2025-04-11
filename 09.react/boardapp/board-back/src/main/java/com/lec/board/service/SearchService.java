package com.lec.board.service;

import org.springframework.http.ResponseEntity;

import com.lec.board.dto.response.search.GetPopularListResponseDto;
import com.lec.board.dto.response.search.GetRelationListResponseDto;


public interface SearchService {

	ResponseEntity<? super GetPopularListResponseDto> getPopulaList();
	ResponseEntity<? super GetRelationListResponseDto> getRelationList(String searchWord);
	
}
