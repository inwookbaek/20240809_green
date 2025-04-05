package com.lec.board.service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lec.board.config.WebSecurityConfig;
import com.lec.board.dto.request.board.PostBoardRequestDto;
import com.lec.board.dto.request.board.PostCommentRequestDto;
import com.lec.board.dto.response.ResponseDto;
import com.lec.board.dto.response.board.GetBoardResponseDto;
import com.lec.board.dto.response.board.GetCommentListResponseDto;
import com.lec.board.dto.response.board.GetFavoriteListResponseDto;
import com.lec.board.dto.response.board.IncreaseViewCountResponseDto;
import com.lec.board.dto.response.board.PostBoardResponseDto;
import com.lec.board.dto.response.board.PostCommentResponseDto;
import com.lec.board.dto.response.board.PutFavoriteResponseDto;
import com.lec.board.entity.BoardEntity;
import com.lec.board.entity.CommentEntity;
import com.lec.board.entity.FavoriteEntity;
import com.lec.board.entity.ImageEntity;
import com.lec.board.repository.BoardRepository;
import com.lec.board.repository.CommentRepository;
import com.lec.board.repository.FavoriteRepository;
import com.lec.board.repository.ImageRepository;
import com.lec.board.repository.UserRepository;
import com.lec.board.repository.resultSet.GetBoardResultSet;
import com.lec.board.repository.resultSet.GetCommentListResultSet;
import com.lec.board.repository.resultSet.GetFavoriteListResultSet;
import com.lec.board.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final WebSecurityConfig webSecurityConfig;

	private final UserRepository userRepository;
	private final BoardRepository boardRepository;
	private final ImageRepository imageRepository;
	private final CommentRepository commentRepository;
	private final FavoriteRepository favoriteRepository;
	
	@Override
	public ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String email) {
		
		try {
			boolean existsEmail = userRepository.existsByEmail(email);
			if(!existsEmail) return PostBoardResponseDto.notExistUser();
			
			BoardEntity boardEntity = new BoardEntity(dto, email);
			boardRepository.save(boardEntity);
			
			int boardNumber = boardEntity.getBoardNumber();

//			lambda식 미 적용
//			List<String> boardImageList = dto.getBoardImageList();
//			List<ImageEntity> imageEntities = new ArrayList<>();
//			
//			for(String image: boardImageList) {
//				ImageEntity imageEntity = new ImageEntity(boardNumber, image);
//				imageEntities.add(imageEntity);
//			}		
			
			// lambda식
			List<ImageEntity> imageEntities = dto.getBoardImageList().stream()
				    .map(image -> new ImageEntity(boardNumber, image))
				    .collect(Collectors.toList());

			imageRepository.saveAll(imageEntities);
				
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseDto.databaseError();
		}
		
		
		return PostBoardResponseDto.success();
	}

	@Override
	public ResponseEntity<? super GetBoardResponseDto> getBoard(Integer boardNumber) {
		
		GetBoardResultSet resultSet = null;
		List<ImageEntity> imageEntities = new ArrayList<>();
		
		try {
			
			resultSet = boardRepository.getBoard(boardNumber);
			if(resultSet == null) return GetBoardResponseDto.notExistBoard();
			imageEntities = imageRepository.findByBoardNumber(boardNumber);
			
			// front에서 viewcount증가가 4번 돌아가는 것을 주석처리 하고 별도 로직(increaseViewCount 메서드) 작성
			// BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
			// boardEntity.increaseViewCount(); 
			// boardRepository.save(boardEntity);
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseDto.databaseError();
		}
		return GetBoardResponseDto.success(resultSet, imageEntities);
	}

	@Override
	public ResponseEntity<? super PutFavoriteResponseDto> putFavorite(Integer boardNumber, String email) {
		
		try {
			
			boolean existUser = userRepository.existsByEmail(email);
			if(!existUser) return PutFavoriteResponseDto.noExistUser();
			
			BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
			if(boardEntity == null) return PutFavoriteResponseDto.noExistBoard();		
			
			FavoriteEntity favoriteEntity = favoriteRepository.findByBoardNumberAndUserEmail(boardNumber, email);
			if(favoriteEntity == null) {
				favoriteEntity = new FavoriteEntity(email, boardNumber);
				favoriteRepository.save(favoriteEntity);
				boardEntity.increaseFavoriteCount();
			} else {
				favoriteRepository.delete(favoriteEntity);
				boardEntity.decreaseFavoriteCount();
			}
			
			boardRepository.save(boardEntity);
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseDto.databaseError();
		}
		
		return PutFavoriteResponseDto.success();
	}

	@Override
	public ResponseEntity<? super GetFavoriteListResponseDto> getFavoritList(Integer boardNumber) {
		
		List<GetFavoriteListResultSet> resultSets = new ArrayList<>();
		
		try {
			
			boolean existedBoad = boardRepository.existsByBoardNumber(boardNumber);
			if(!existedBoad) return GetFavoriteListResponseDto.noExistBoard();
			
			resultSets = favoriteRepository.getFavoriteList(boardNumber);
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseDto.databaseError();
		}
		
		return GetFavoriteListResponseDto.success(resultSets);
	}

	@Override
	public ResponseEntity<? super PostCommentResponseDto> postComment(PostCommentRequestDto dto, Integer boardNumber, String email) {
		
		try {
			
			BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
			if(boardEntity == null) return PostCommentResponseDto.notExistBoard();
			
			
			boolean existUser = userRepository.existsByEmail(email);
			if(!existUser) return PutFavoriteResponseDto.noExistUser();
			
			CommentEntity commentEntity = new CommentEntity(dto, boardNumber, email);
			commentRepository.save(commentEntity);
			
			boardEntity.increaseCommentCount();
			boardRepository.save(boardEntity);
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseDto.databaseError();
		}
		
		return PostCommentResponseDto.success();
	}

	@Override
	public ResponseEntity<? super GetCommentListResponseDto> getCommentList(Integer boardNumber) {

		List<GetCommentListResultSet> resultSets = new ArrayList<>();
		
		try {
			
			boolean existedBoad = boardRepository.existsByBoardNumber(boardNumber);
			if(!existedBoad) return GetCommentListResponseDto.noExistBoard();	
			
			resultSets = commentRepository.getCommentList(boardNumber);
			
			// log.info("========> ", resultSets.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseDto.databaseError();
		}
		
		return GetCommentListResponseDto.success(resultSets);
	}

	@Override
	public ResponseEntity<? super IncreaseViewCountResponseDto> increaseViewCount(Integer boardNumber) {
		try {
			 BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
			 
			 if(boardEntity == null) return IncreaseViewCountResponseDto.notExistBoard();
			 
			 boardEntity.increaseViewCount(); 
			 boardRepository.save(boardEntity);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseDto.databaseError();
		}
		return IncreaseViewCountResponseDto.success();
	}

}
