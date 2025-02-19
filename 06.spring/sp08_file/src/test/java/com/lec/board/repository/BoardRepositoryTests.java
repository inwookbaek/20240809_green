package com.lec.board.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import com.lec.board.domain.Board;
import com.lec.board.domain.BoardImage;
import com.lec.board.dto.BoardListAllDTO;
import com.lec.board.dto.BoardListReplyCountDTO;
import com.lec.board.service.BoardService;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class BoardRepositoryTests {

	@Autowired
	BoardRepository boardRepository;
	
	@Autowired
	ReplyRepository replyRepository;
	
	@Autowired
	BoardService boardService;

	@Test
	@DisplayName("검색(like)")
	public void testSearchLike() {
		Pageable pageable = PageRequest.of(1, 10, Sort.by("bno").descending());
		boardRepository.searchLike(pageable);
	}
	
	@Test
	@DisplayName("페이징처리")
	public void testPaging() {
		Pageable pageable =  PageRequest.of(0, 10, Sort.by("bno").descending());
		Page<Board> result = boardRepository.findAll(pageable);
		log.info("총건수 : " + result.getTotalElements());
		log.info("총페이지 : " + result.getTotalPages());
		log.info("현페이지 : " + result.getNumber());
		log.info("페이지크기 : " + result.getSize());
		
		List<Board> boardList = result.getContent();
		boardList.forEach(board -> log.info(board));

	}
	
	@Test
	@DisplayName("검색(limit)")
	public void testSearchPagable() {
		Pageable pageable = PageRequest.of(1, 10, Sort.by("bno").descending());
		boardRepository.searchPageable(pageable);
	}	

	@Test
	@DisplayName("or/and 조건검색")
	public void searchBooleanBuilder() {
		Pageable pageable = PageRequest.of(1, 10, Sort.by("bno").descending());
		boardRepository.searchBooleanBuilder(pageable);
		
	}
	
	@Test
	@DisplayName("검색조건 - all")
	public void searchAll() {
		
		String[] types = {"t", "c", "w"};
		String keyword = "1";
		
		Pageable pageable = PageRequest.of(1, 10, Sort.by("bno").descending());
		boardRepository.searchAll(types, keyword, pageable);
		
	}
	
	@Test
	@DisplayName("검색조건 - PageImpl")
	public void searchAllImpl() {
		
		String[] types = {"t", "c", "w"};
		String keyword = "1";
		
		Pageable pageable = PageRequest.of(1, 10, Sort.by("bno").descending());
		Page<Board> result = boardRepository.searchAllImpl(types, keyword, pageable);
		
		log.info("총페이지   = " + result.getTotalPages());
		log.info("페이지크기 = " + result.getSize());
		log.info("현재페이지 = " + result.getNumber());
		log.info("이전페이지 = " + result.hasPrevious());
		log.info("다음페이지 = " + result.hasNext());
		
		result.getContent().forEach(board -> log.info(board));
		
	}
	
	@Test
	public void testSearchReplyCount() {
		String[] types = {"t","c","w"};
		String keyword = "1";
		Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
		Page<BoardListReplyCountDTO> result = boardRepository.searchWithReplyCount(types, keyword, pageable);
		
		// total pages
		log.info(result.getTotalPages());
		
		// page size
		log.info(result.getSize());
		
		// page number
		log.info(result.getNumber());
		
		// prev / next number
		log.info(result.hasPrevious() + ", " + result.hasNext());
		
		// content
		result.getContent().forEach(board -> log.info(board));
	}
	
	@Test
	public void testInsertWithImages() {
		
		Board board = Board.builder()
				.title("이미지테스트")
				.content("첨부파일테스트")
				.writer("tester1")
				.build();
		
		for(int i=0;i<3;i++) {
			board.addImage(UUID.randomUUID().toString(), "file" + i +".jpg");
		}
		
		boardRepository.save(board);
	}
	
	@Test
	public void testReadWithImages() {
		
		Optional<Board> result = boardRepository.findById(1L);
		Board board = result.orElseThrow();
		
		log.info(board);
		log.info("------------------------------");
		log.info(board.getImageSet());
	}
	
	@Test
	public void testReadWithImagesByEntityGraph() {
		
		Optional<Board> result = boardRepository.findByIdWithImages(1L);
		Board board = result.orElseThrow();
		
		log.info(board);
		log.info("------------------------------");
		// log.info(board.getImageSet());
		for(BoardImage boardImage : board.getImageSet()) {
			log.info(boardImage);
		}
	}
	
	@Test
	@Commit
	@Transactional
	public void testModifyImages() {

		Optional<Board> result = boardRepository.findByIdWithImages(1L);
		Board board = result.orElseThrow();
		
		// 기존이미지 삭제
		board.clearImage();
		
		// 신규이미지 추가
		for(int i=0;i<3;i++) {
			board.addImage(UUID.randomUUID().toString(), "updatefile" + i +".jpg");
		}
		
		boardRepository.save(board);
	}
	
	@Test
	@Transactional
	@Commit
	public void testRemoveAll() {
		Long bno = 2L;
		replyRepository.deleteByBoard_Bno(bno);
		boardRepository.deleteById(bno);
	}
	
	// N+1 문제를 @BatchSize로 해결방법
	// 1. test data generate
	@Test
	public void testInsertAll() {
		for(int i=1;i<=100;i++) {
			Board board = Board.builder()
					.title("N+1문제 title.... " + i)
					.content("@BatchSize로 간단한 해결방법 " + i)
					.writer("writer"+i)
					.build();
			
			for(int j=0;j<=3;j++) {
				if(i%5 == 0) {
					continue;
				}
				board.addImage(UUID.randomUUID().toString(), i+"file"+j+".jpg");
			}
			
			boardRepository.save(board);
		}
	}

	// 2. @BatchSize적용 전/후(Board엔티티의 BoardImage에 설정)
	@Test
	@Transactional
	public void testSearchImageRplyCount() {
		// N(게시글마다 각각실행되는 쿼리) + 1(목록을 가져오는 쿼리)
		// 문제 발생 즉, 게시글하나당 해당이미지를 각각조회하는 쿼리가 실행되는 문제
		Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
		// boardRepository.searchWithAll(null, null, pageable);
		
		Page<BoardListAllDTO> result = boardRepository.searchWithAll(null, null, pageable);
		
		log.info("---------------------------------");
		log.info(result.getTotalElements());
		
		result.getContent().forEach(boardListAllDTO -> log.info(boardListAllDTO));
	}
		
}












