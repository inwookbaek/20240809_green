package com.lec.board.service.implement;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lec.board.config.WebSecurityConfig;
import com.lec.board.dto.request.board.*;
import com.lec.board.dto.response.ResponseDto;
import com.lec.board.dto.response.board.*;
import com.lec.board.entity.*;
import com.lec.board.repository.*;
import com.lec.board.repository.resultSet.*;
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
	private final SearchLogRepository searchLogRepository;
	private final BoardListViewRepository boardListViewRepository;
	
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

	@Override
	public ResponseEntity<? super DeleteBoardResponseDto> deleteBoard(Integer boardNumber, String email) {
		try {
			boolean existUser = userRepository.existsByEmail(email);
			if(!existUser) return PutFavoriteResponseDto.noExistUser();
			
			 BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
			 if(boardEntity == null) return DeleteBoardResponseDto.noExistBoard();
			 
			 String writerEamil = boardEntity.getWriterEmail();
			 boolean isWriter = writerEamil.equals(email);
			 if(!isWriter) return DeleteBoardResponseDto.noPermission();
			 
			 imageRepository.deleteByBoardNumber(boardNumber);
			 commentRepository.deleteByBoardNumber(boardNumber);
			 favoriteRepository.deleteByBoardNumber(boardNumber);
			 boardRepository.delete(boardEntity);
			 
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseDto.databaseError();
		}			
		return DeleteBoardResponseDto.success();
	}

	@Override
	public ResponseEntity<? super PatchBoardResponseDto> patchBoard(PatchBoardRequestDto dto, Integer boardNumber,
			String email) {
		try {
			
			BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
			
			if(boardEntity == null) return PatchBoardResponseDto.noExistBoard();				
						
			boolean existUser = userRepository.existsByEmail(email);
			if(!existUser) return PatchBoardResponseDto.noExistUser();	 
			
			String writerEamil = boardEntity.getWriterEmail();
			boolean isWriter = writerEamil.equals(email);
			if(!isWriter) return PatchBoardResponseDto.noPermission();
			
			boardEntity.patchBoard(dto);
			boardRepository.save(boardEntity);
			
			imageRepository.deleteByBoardNumber(boardNumber);						
			List<ImageEntity> imageEntities = 
				 dto.getBoardImageList()
					.stream()
					.map(image -> new ImageEntity(boardNumber, image))
					.collect(Collectors.toList());
			imageRepository.saveAll(imageEntities);
			 
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseDto.databaseError();
		}			
		return PatchBoardResponseDto.success();
	}

	@Override
	public ResponseEntity<? super GetLatestBoardListResponseDto> getLatestBoardList() {
		
		List<BoardListViewEntity> boardListViewEntities = new ArrayList<>();
		
		try {
			
			boardListViewEntities = boardListViewRepository.findByOrderByWriteDatetimeDesc();
			 
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseDto.databaseError();
		}			
		return GetLatestBoardListResponseDto.success(boardListViewEntities);
	}

	@Override
	public ResponseEntity<? super GetTop3BoardListResponseDto> getTop3BoardList() {
		
		List<BoardListViewEntity> boardListViewEntities = new ArrayList<>();
		
		try {
			
			Date beforeOneWeek = Date.from(Instant.now().minus(7, ChronoUnit.DAYS));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String sevenDaysAgo = sdf.format(beforeOneWeek);
			
			boardListViewEntities = boardListViewRepository			
					//.findTop3ByWriteDatetimeGreaterThanOrderByWriteDatetimeDescFavoriteCountDescCommentCountDescViewCountDesc(sevenDaysAgo);
					.getTop3BoardList(sevenDaysAgo);
			 
			// log.info("top3 ======> " + boardListViewEntities.toString() + " / " + sevenDaysAgo);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseDto.databaseError();
		}		
		return GetTop3BoardListResponseDto.success(boardListViewEntities);
	}

	@Override
	public ResponseEntity<? super GetSearchBoardListResponseDto> getSearchBoardList(String searchWord,
			String preSearchWord) {
		
		
		log.info("ServiceImpl - searchWord ===> ", searchWord);
		
		List<BoardListViewEntity> boardListViewEntities = new ArrayList<>();
		
		try {
			
			boardListViewEntities = boardListViewRepository.findByTitleContainsOrContentContainsOrderByWriteDatetimeDesc(searchWord, preSearchWord);
			
			SearchLogEntity searchLogEntity = new SearchLogEntity(searchWord, preSearchWord, false);
			searchLogRepository.save(searchLogEntity);

			boolean relation = preSearchWord != null;
			if(relation) {
				searchLogEntity = new SearchLogEntity(preSearchWord, searchWord, relation);
				searchLogRepository.save(searchLogEntity);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseDto.databaseError();
		}			
		return GetSearchBoardListResponseDto.success(boardListViewEntities);
	}

}
