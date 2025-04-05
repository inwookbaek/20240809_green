package com.lec.board.service;

import org.springframework.http.ResponseEntity;

import com.lec.board.dto.request.board.PostBoardRequestDto;
import com.lec.board.dto.request.board.PostCommentRequestDto;
import com.lec.board.dto.response.board.GetBoardResponseDto;
import com.lec.board.dto.response.board.GetCommentListResponseDto;
import com.lec.board.dto.response.board.GetFavoriteListResponseDto;
import com.lec.board.dto.response.board.IncreaseViewCountResponseDto;
import com.lec.board.dto.response.board.PostBoardResponseDto;
import com.lec.board.dto.response.board.PostCommentResponseDto;
import com.lec.board.dto.response.board.PutFavoriteResponseDto;

public interface BoardService {

	ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String email);
	ResponseEntity<? super GetBoardResponseDto> getBoard(Integer boardNumber);
	ResponseEntity<? super PutFavoriteResponseDto> putFavorite(Integer boardNumber, String email);
	ResponseEntity<? super GetFavoriteListResponseDto> getFavoritList(Integer boardNumber);
	ResponseEntity<? super PostCommentResponseDto> postComment(PostCommentRequestDto dto, Integer boardNumber, String email);
	ResponseEntity<? super GetCommentListResponseDto> getCommentList(Integer boardNumber);
	ResponseEntity<? super IncreaseViewCountResponseDto> increaseViewCount(Integer boardNumber);
	
}
