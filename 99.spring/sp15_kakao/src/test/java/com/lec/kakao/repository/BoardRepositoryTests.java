package com.lec.kakao.repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import com.lec.kakao.domain.Board;
import com.lec.kakao.domain.BoardImage;
import com.lec.kakao.dto.BoardDTO;
import com.lec.kakao.dto.BoardListAllDTO;
import com.lec.kakao.dto.BoardListReplyCountDTO;
import com.lec.kakao.service.BoardService;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class BoardRepositoryTests {

	@Autowired
	BoardRepository boardRepository;
	
	@Autowired
	BoardService boardService;
	
    @Autowired
    private ReplyRepository replyRepository;	
	
	@Test
	@DisplayName("게시판 샘플 데이타 생성")
	public void testInsert() {
		IntStream.rangeClosed(1, 100).forEach(i -> {
			Board board = Board.builder()
					.title("title..." + i)
					.content("content..." + i)
					.writer("writer..." + (i%10))
					.build();
			Board result = boardRepository.save(board);
			log.info("BNO: " + result.getBno());
		});
		
	}
	
	@Test
	@DisplayName("게시판 샘플 데이타 한 건 생성")
	public void testInsertOne() {
		Board board = Board.builder()
				.title("title999")
				.content("content999")
				.writer("writer999")
				.build();
		Board result = boardRepository.save(board);
		log.info("BNO: " + result.getBno());
		
	}	
	
	@Test
	@DisplayName("게시판 한 건 조회")
	public void testSelect() {
		
		Long bno = 100L;
		Optional<Board> result = boardRepository.findById(bno);
		Board board = result.orElseThrow();
		log.info(board);		
	}

	@Test
	@DisplayName("게시판 수정")
	public void testUpdate() {
		
		Long bno = 100L;
		Optional<Board> result = boardRepository.findById(bno);
		Board board = result.orElseThrow();
		board.change("제목100", "글 상세내용 100");
		boardRepository.save(board);
		log.info(board);		
	}
	
	
	@Test
	@DisplayName("게시판 삭제")
	public void testDelete() {
		
		Long bno = 99L;
		Optional<Board> result = boardRepository.findById(bno);
		Board board = result.orElseThrow();
		boardRepository.deleteById(bno);
		log.info(board);		
	}
	
	@Test
	@DisplayName("페이징 처리")
	public void testPaging() {
		
		Pageable pageable = PageRequest.of(0,10,Sort.by("bno").descending());
		Page<Board> result = boardRepository.findAll(pageable);
		log.info("총건수: " + result.getTotalElements());
		log.info("총페이지: " + result.getTotalPages());
		log.info("현페이지: " + result.getNumber());
		log.info("페이지크기: " + result.getSize());
		
		List<Board> boardList = result.getContent();
		boardList.forEach(board -> log.info(board));
	}
	
    @Test
    @DisplayName("검색 like")
    public void testSearchLike() {
        // 2 page order by bno desc
        Pageable pageable = PageRequest.of(1,10, Sort.by("bno").descending());
        boardRepository.searchLike(pageable);

    }	
    
    @Test
    @DisplayName("검색 limit")
    public void testSearchPagable() {
    	// 2 page order by bno desc
    	Pageable pageable = PageRequest.of(1,10, Sort.by("bno").descending());
    	boardRepository.searchPagable(pageable);
    	
    }	
    
    @Test
    @DisplayName("검색 where and or")
    public void testSearchBooleanBuilder() {
    	// 2 page order by bno desc
    	Pageable pageable = PageRequest.of(1,10, Sort.by("bno").descending());
    	boardRepository.searchBooleanBuilder(pageable);
    	
    }	
    
    @Test
    @DisplayName("검색 all")
    public void testSearchAll() {

    	String[] types = {"t","c","w"};
        String keyword = "1";
        Pageable pageable = PageRequest.of(0,10, Sort.by("bno").descending());
        Page<Board> result = boardRepository.searchAll(types, keyword, pageable );
    	
    }	
    
    @Test
    @DisplayName("검색 PageImpl")
    public void testSearchAllPageImpl() {
    	
    	String[] types = {"t","c","w"};
    	String keyword = "1";
    	Pageable pageable = PageRequest.of(0,10, Sort.by("bno").descending());
    	Page<Board> result = boardRepository.searchAllPageImpl(types, keyword, pageable );
 
        // total pages
        log.info(result.getTotalPages());

        // pag size
        log.info(result.getSize());

        // pageNumber
        log.info(result.getNumber());

        // prev next
        log.info(result.hasPrevious() +": " + result.hasNext());

        result.getContent().forEach(board -> log.info(board));
    }	
    
    @Test
    public void testSearchReplyCount() {

        String[] types = {"t","c","w"};
        String keyword = "1";
        Pageable pageable = PageRequest.of(0,10, Sort.by("bno").descending());
        Page<BoardListReplyCountDTO> result = boardRepository.searchWithReplyCount(types, keyword, pageable );

        //total pages
        log.info(result.getTotalPages());
        //pag size
        log.info(result.getSize());
        //pageNumber
        log.info(result.getNumber());
        //prev next
        log.info(result.hasPrevious() +": " + result.hasNext());

        result.getContent().forEach(board -> log.info(board));
    }    
    
    @Test
    public void testInsertWithImages() {

        Board board = Board.builder()
                .title("Image Test")
                .content("첨부파일 테스트")
                .writer("tester")
                .build();

        for (int i = 0; i < 3; i++) {

            board.addImage(UUID.randomUUID().toString(), "file"+i+".jpg");

        }//end for

        boardRepository.save(board);
    }   
    
//  @Test
//  public void testReadWithImages() {
//
//      //반드시 존재하는 bno로 확인
//      Optional<Board> result = boardRepository.findById(1L);
//
//      Board board = result.orElseThrow();
//
//      log.info(board);
//      log.info("--------------------");
//      log.info(board.getImageSet());
//  }    
  
  @Test
  public void testReadWithImages() {

      //반드시 존재하는 bno로 확인
      Optional<Board> result = boardRepository.findByIdWithImages(1L);

      Board board = result.orElseThrow();

      log.info(board);
      log.info("--------------------");
      for (BoardImage boardImage : board.getImageSet()) {
          log.info(boardImage);
      }
  }  
  
  @Transactional
  @Commit
  @Test
  public void testModifyImages() {

      Optional<Board> result = boardRepository.findByIdWithImages(1L);

      Board board = result.orElseThrow();

      //기존의 첨부파일들은 삭제
      board.clearImages();

      //새로운 첨부파일들
      for (int i = 0; i < 2; i++) {

          board.addImage(UUID.randomUUID().toString(), "updatefile"+i+".jpg");
      }

      boardRepository.save(board);

  }  
  
  @Test
  @Transactional
  @Commit
  public void testRemoveAll() {

      Long bno = 1L;
      replyRepository.deleteByBoard_Bno(bno);
      boardRepository.deleteById(bno);

  } 
  
  @Test
  public void testInsertAll() {

      for (int i = 1; i <= 100; i++) {

          Board board  = Board.builder()
                  .title("Title.."+i)
                  .content("Content.." + i)
                  .writer("writer.." + i)
                  .build();

          for (int j = 0; j < 3; j++) {

              if(i % 5 == 0){
                  continue;
              }
              board.addImage(UUID.randomUUID().toString(),i+"file"+j+".jpg");
          }
          boardRepository.save(board);

      }//end for   
  }
  
  @Transactional
  @Test
  public void testSearchImageReplyCount() {
	  // N+1의 문제 : 게시글 하나당 해당 이미지를 각각 조회하는 쿼리 실행하는 문제
	  // 이 문제를 해결하는 방법은 @BachSize를 이용  
	  // Board.imageSet에 @BachSize를 적용
      Pageable pageable = PageRequest.of(0,10,Sort.by("bno").descending());
      boardRepository.searchWithAll(null, null,pageable);
  }  
  
  @Transactional
  @Test
  public void testSearchImageReplyCount_01() {
      Pageable pageable = PageRequest.of(0,10,Sort.by("bno").descending());
      
      // p629
      // Page<BoardListReplyCountDTO> result = boardRepository.searchWithAll(null,null,pageable);
      
      // p636
      Page<BoardListAllDTO> result = boardRepository.searchWithAll(null,null,pageable);
      
      log.info("---------------------------");
      log.info(result.getTotalElements());

      result.getContent().forEach(boardListAllDTO -> log.info(boardListAllDTO));
  }   
  
  // p642
  @Test
  public void testRegisterWithImages() {

      log.info(boardService.getClass().getName());

      BoardDTO boardDTO = BoardDTO.builder()
              .title("File...Sample Title...")
              .content("Sample Content...")
              .writer("user00")
              .build();

      boardDTO.setFileNames(
              Arrays.asList(
                      UUID.randomUUID()+"_aaa.jpg",
                      UUID.randomUUID()+"_bbb.jpg",
                      UUID.randomUUID()+"_bbb.jpg"
              ));

      Long bno = boardService.register(boardDTO);

      log.info("bno: " + bno);
  } 
  
}