package com.lec.board.dto.response.board;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.lec.board.common.ResponseCode;
import com.lec.board.common.ResponseMessage;
import com.lec.board.dto.object.BoardListItem;
import com.lec.board.dto.response.ResponseDto;
import com.lec.board.entity.BoardEntity;
import com.lec.board.entity.BoardListViewEntity;

import lombok.Getter;

@Getter
public class GetUserBoardListRestponseDto extends ResponseDto {

	private List<BoardListItem> userBoardList;
	
	private GetUserBoardListRestponseDto(List<BoardListViewEntity> boardListViewEntities) {
		super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
		this.userBoardList = BoardListItem.getList(boardListViewEntities);
	}
	
	public static ResponseEntity<GetUserBoardListRestponseDto> success(List<BoardListViewEntity> boardListViewEntities) {
		GetUserBoardListRestponseDto result = new GetUserBoardListRestponseDto(boardListViewEntities);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	public static ResponseEntity<ResponseDto> noExistUser() {
		ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
	}
	
}
