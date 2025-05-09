package com.lec.board.service;

import org.springframework.http.ResponseEntity;

import com.lec.board.dto.request.board.*;
import com.lec.board.dto.response.board.*;

public interface BoardService {

	ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String email);
	ResponseEntity<? super GetBoardResponseDto> getBoard(Integer boardNumber);
	ResponseEntity<? super PutFavoriteResponseDto> putFavorite(Integer boardNumber, String email);
	ResponseEntity<? super GetFavoriteListResponseDto> getFavoritList(Integer boardNumber);
	ResponseEntity<? super PostCommentResponseDto> postComment(PostCommentRequestDto dto, Integer boardNumber, String email);
	ResponseEntity<? super GetCommentListResponseDto> getCommentList(Integer boardNumber);
	ResponseEntity<? super IncreaseViewCountResponseDto> increaseViewCount(Integer boardNumber);
	ResponseEntity<? super DeleteBoardResponseDto> deleteBoard(Integer boardNumber, String email);
	ResponseEntity<? super PatchBoardResponseDto> patchBoard(PatchBoardRequestDto dto, Integer boardNumber, String email);
	ResponseEntity<? super GetLatestBoardListResponseDto> getLatestBoardList();
	ResponseEntity<? super GetTop3BoardListResponseDto> getTop3BoardList();
	ResponseEntity<? super GetSearchBoardListResponseDto> getSearchBoardList(String searchWord, String preSearchWord);
	ResponseEntity<? super GetUserBoardListRestponseDto> getUserBoardList(String email);
	
}
