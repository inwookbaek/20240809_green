
package com.lec.board.dto.response.search;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.lec.board.common.ResponseCode;
import com.lec.board.common.ResponseMessage;
import com.lec.board.dto.response.ResponseDto;
import com.lec.board.repository.resultSet.GetPopularListResultSet;
import com.lec.board.repository.resultSet.GetRelationListResultSet;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Getter
public class GetRelationListResponseDto  extends ResponseDto {

	List<String> relativeWordList;
	 
	private GetRelationListResponseDto(List<GetRelationListResultSet> resultSets) {
		super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
		
		List<String> relativeWordList = new ArrayList<>();	
		
		for(GetRelationListResultSet resultSet : resultSets) {
			String relativeWord = resultSet.getSearchWord();
			relativeWordList.add(relativeWord);
		}
		
		this.relativeWordList = relativeWordList;
	}
	
	public static ResponseEntity<GetRelationListResponseDto> success(List<GetRelationListResultSet> resultSets) {
		GetRelationListResponseDto result = new GetRelationListResponseDto(resultSets);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
}
