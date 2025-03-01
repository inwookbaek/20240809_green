package com.lec.board.repository.search;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.lec.board.domain.Board;
import com.lec.board.domain.QBoard;
import com.lec.board.domain.QReply;
import com.lec.board.dto.BoardImageDTO;
import com.lec.board.dto.BoardListAllDTO;
import com.lec.board.dto.BoardListReplyCountDTO;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {

	public BoardSearchImpl() {
		super(Board.class);
	}

	@Override
	public Page<Board> searchLike(Pageable pageable) {
		
		QBoard board = QBoard.board;
		JPQLQuery<Board> query = from(board);    // select * from board;
		query.where(board.title.contains("1"));  // where title like '%1%'
		List<Board> list = query.fetch();
		long count = query.fetchCount();
		log.info("검색건수 = " + count);
		return null;
	}

	@Override
	public Page<Board> searchPageable(Pageable pageable) {
		
		QBoard board = QBoard.board;
		JPQLQuery<Board> query = from(board);    // select * from board;
		query.where(board.title.contains("1"))  // where title like '%1%'
		     .limit(1)
		     .offset(2);
		// limit ?, ?
		this.getQuerydsl().applyPagination(pageable, query);
		List<Board> list = query.fetch();
		long count = query.limit(1).offset(2).fetchCount();
		log.info("검색건수 = " + count);
		
		return null;
	}

	@Override
	public Page<Board> searchBooleanBuilder(Pageable pageable) {
		
		QBoard board = QBoard.board;
		JPQLQuery<Board> query = from(board);
		
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		booleanBuilder.or(board.title.contains("11"));
		booleanBuilder.or(board.content.contains("11"));
		
		query.where(booleanBuilder);
		query.where(board.bno.gt(10L)); 
		
		this.getQuerydsl().applyPagination(pageable, query);
		List<Board> list = query.fetch();
		long count = query.fetchCount();
		log.info("검색건수 = " + count);
		
		return null;
	}

	@Override
	public Page<Board> searchAll(String[] types, String keyword, Pageable pageable) {
		
		QBoard board = QBoard.board;
		JPQLQuery<Board> query = from(board);
		
		if ((types != null || types.length > 0) && keyword != null) { // 검색조건과 검색어가 있다면
			BooleanBuilder booleanBuilder = new BooleanBuilder();
			for(String type:types) {
				switch(type) {
				case "t": 
					booleanBuilder.or(board.title.contains(keyword));
					break;
				case "c": 
					booleanBuilder.or(board.content.contains(keyword));
					break;
				case "w": 
					booleanBuilder.or(board.writer.contains(keyword));
				}
			}
			query.where(booleanBuilder);
		}
		
		this.getQuerydsl().applyPagination(pageable, query);
		List<Board> list = query.fetch();
		long count = query.fetchCount();
		log.info("검색건수 = " + count);
		
		return null;
	}

	@Override
	public Page<Board> searchAllImpl(String[] types, String keyword, Pageable pageable) {
		
		QBoard board = QBoard.board;
		JPQLQuery<Board> query = from(board);
		
		if ((types != null || types.length > 0) && keyword != null) { // 검색조건과 검색어가 있다면
			BooleanBuilder booleanBuilder = new BooleanBuilder();
			for(String type:types) {
				switch(type) {
				case "t": 
					booleanBuilder.or(board.title.contains(keyword));
					break;
				case "c": 
					booleanBuilder.or(board.content.contains(keyword));
					break;
				case "w": 
					booleanBuilder.or(board.writer.contains(keyword));
				}
			}
			query.where(booleanBuilder);
		}
		
		this.getQuerydsl().applyPagination(pageable, query);
		List<Board> list = query.fetch();
		long count = query.fetchCount();
		
		return new PageImpl<>(list, pageable, count);
	}

	@Override
	public Page<BoardListReplyCountDTO> searchWithReplyCount(String[] types, String keyword, Pageable pageable) {
        QBoard board = QBoard.board;
        QReply reply = QReply.reply;

        JPQLQuery<Board> query = from(board);
        // on -> join
        query.leftJoin(reply).on(reply.board.eq(board));
        query.groupBy(board);
        
        if( (types != null && types.length > 0) && keyword != null ){

            BooleanBuilder booleanBuilder = new BooleanBuilder(); // (
            for(String type: types){
                switch (type){
                    case "t":
                        booleanBuilder.or(board.title.contains(keyword));
                        break;
                    case "c":
                        booleanBuilder.or(board.content.contains(keyword));
                        break;
                    case "w":
                        booleanBuilder.or(board.writer.contains(keyword));
                        break;
                }
            } //end for
            query.where(booleanBuilder);
        }

        //bno > 0
        query.where(board.bno.gt(0L));        
        
        // JPA에서는 Projections이라고 해서 JPQL의 결과를 바로 DTO로 처리하는 기능을 제공한다.
        // QueryDsl에서도 Projections.bean()을 통해 이런 기능을 제공한다 
        // JPQLQuery 객체의 select를 이용해서 한번에 DTO로 처리할 수 있다. 
        JPQLQuery<BoardListReplyCountDTO> dtoQuery 
        	= query.select(Projections.bean(
        			BoardListReplyCountDTO.class,
        			board.bno,
        			board.title,
        			board.writer,
        			board.regDate,
        			reply.count().as("replyCount")
        ));        
              
        this.getQuerydsl().applyPagination(pageable,dtoQuery);
        List<BoardListReplyCountDTO> dtoList = dtoQuery.fetch();
        long count = dtoQuery.fetchCount();

        return new PageImpl<>(dtoList, pageable, count);
	}

//	@Override
//	public Page<BoardListReplyCountDTO> searchWithAll(String[] types, String keyword, Pageable pageable) {
//        
//		QBoard board = QBoard.board;
//        QReply reply = QReply.reply;
//        
//        JPQLQuery<Board> boardJPQLQuery = from(board);
//        boardJPQLQuery.leftJoin(reply).on(reply.board.eq(board));     
//        
//        getQuerydsl().applyPagination(pageable, boardJPQLQuery);  // 페이징처리
//        
//        List<Board> boardList = boardJPQLQuery.fetch();
//        
//        boardList.forEach(b -> {
//        	System.out.println(b.getBno());
//        	System.out.println(b.getImageSet());
//        	System.out.println("------------------------");
//        });
//        
//        return null;
//	}
	
	@Override
	public Page<BoardListAllDTO> searchWithAll(String[] types, String keyword, Pageable pageable) {
		
		QBoard board = QBoard.board;
		QReply reply = QReply.reply;
		
		JPQLQuery<Board> boardJPQLQuery = from(board);
		boardJPQLQuery.leftJoin(reply).on(reply.board.eq(board));   
		
		boardJPQLQuery.groupBy(board);

		getQuerydsl().applyPagination(pageable, boardJPQLQuery);  // 페이징처리

		JPQLQuery<Tuple> tupleJPLQuery = boardJPQLQuery.select(board, reply.countDistinct());
		List<Tuple> tupleList = tupleJPLQuery.fetch();
		
		List<BoardListAllDTO> dtoList = tupleList.stream().map(tuple -> {
			Board board1 = tuple.get(board);
			long replyCount = tuple.get(1, Long.class);
			
			BoardListAllDTO dto = BoardListAllDTO.builder()
					.bno(board1.getBno())
					.title(board1.getTitle())
					.writer(board1.getWriter())
					.regDate(board1.getRegDate())
					.replyCount(replyCount)
					.build();
			
			// BoardImage처리할 로직
			List<BoardImageDTO> imageDTOs = board1.getImageSet()
					.stream()
					.sorted()
					.map(boardImage -> BoardImageDTO.builder()
							.uuid(boardImage.getUuid())
							.fileName(boardImage.getFileName())
							.ord(boardImage.getOrd())
							.build())
					.collect(Collectors.toList());
			dto.setBoardImages(imageDTOs);
			
			return dto;
		}).collect(Collectors.toList());
		
		long totalCount = boardJPQLQuery.fetchCount();
		
		return new PageImpl<>(dtoList, pageable, totalCount);
	}

}
