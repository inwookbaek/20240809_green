package com.lec.board.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lec.board.dto.response.ResponseDto;
import com.lec.board.dto.response.search.GetPopularListResponseDto;
import com.lec.board.repository.SearchLogRepository;
import com.lec.board.repository.resultSet.GetPopularListResultSet;
import com.lec.board.service.SearchService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {
	
	private final SearchLogRepository searchLogRepository;

	@Override
	public ResponseEntity<? super GetPopularListResponseDto> getPopulaList() {
		
		List<GetPopularListResultSet> resultSets = new ArrayList<>();
		
		try {
			
			resultSets = searchLogRepository.getPopularList();
			 
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseDto.databaseError();
		}			
		return GetPopularListResponseDto.success(resultSets);
	}
}
