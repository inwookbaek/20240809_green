package com.lec.board.dto.response.board;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.lec.board.common.ResponseCode;
import com.lec.board.common.ResponseMessage;
import com.lec.board.dto.object.FavoriteListItem;
import com.lec.board.dto.response.ResponseDto;
import com.lec.board.repository.resultSet.GetFavoriteListResultSet;

import lombok.Getter;

@Getter
public class GetFavoriteListResponseDto extends ResponseDto {

	private List<FavoriteListItem> favoriteList;
	
	private GetFavoriteListResponseDto(List<GetFavoriteListResultSet> resultSets) {
		super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
		this.favoriteList = FavoriteListItem.copyList(resultSets);
	}
	
	public static ResponseEntity<GetFavoriteListResponseDto> success(List<GetFavoriteListResultSet> resultSets) { 
		GetFavoriteListResponseDto result = new GetFavoriteListResponseDto(resultSets);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	public static ResponseEntity<ResponseDto> noExistBoard() {
		ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_BOARD, ResponseMessage.NOT_EXISTED_BOARD);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
	}	
}
