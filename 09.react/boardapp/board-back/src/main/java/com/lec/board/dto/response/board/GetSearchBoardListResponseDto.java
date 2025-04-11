package com.lec.board.dto.response.board;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.lec.board.common.ResponseCode;
import com.lec.board.common.ResponseMessage;
import com.lec.board.dto.object.BoardListItem;
import com.lec.board.dto.response.ResponseDto;
import com.lec.board.entity.BoardListViewEntity;

import lombok.Getter;

@Getter
public class GetSearchBoardListResponseDto extends ResponseDto {
	
	private List<BoardListItem> searchList;

	private GetSearchBoardListResponseDto(List<BoardListViewEntity> boardListViewEntities) {
		super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
		this.searchList = BoardListItem.getList(boardListViewEntities);
	}
	
	public static ResponseEntity<GetSearchBoardListResponseDto> success(List<BoardListViewEntity> boardListViewEntities) {
		GetSearchBoardListResponseDto result = new GetSearchBoardListResponseDto(boardListViewEntities);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
}
