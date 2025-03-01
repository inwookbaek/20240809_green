package com.lec.file.repository.search;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.lec.file.domain.Board;
import com.lec.file.domain.QBoard;
import com.lec.file.domain.QReply;
import com.lec.file.dto.BoardImageDTO;
import com.lec.file.dto.BoardListAllDTO;
import com.lec.file.dto.BoardListReplyCountDTO;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;

public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {

	public BoardSearchImpl() {
		super(Board.class);
	}

	@Override
	public Page<Board> searchLike(Pageable pageable) {
		QBoard board = QBoard.board;
		JPQLQuery<Board> query = from(board);
		
		query.where(board.title.contains("1"));
		
        List<Board> list = query.fetch();
        long count = query.fetchCount();
        
		return null;
	}

	@Override
	public Page<Board> searchPagable(Pageable pageable) {
		QBoard board = QBoard.board;
		JPQLQuery<Board> query = from(board);
		
		query.where(board.title.contains("1"));
		
		// paging - limit ?, ?
		this.getQuerydsl().applyPagination(pageable, query);
		
        List<Board> list = query.fetch();
        long count = query.fetchCount();
        
        
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
        query.where(board.bno.gt(0L));
 
		// paging - limit ?, ?
		this.getQuerydsl().applyPagination(pageable, query);
		
        List<Board> list = query.fetch();
        long count = query.fetchCount();
        
		return null;
	}

	@Override
	public Page<Board> searchAll(String[] types, String keyword, Pageable pageable) {
		
        QBoard board = QBoard.board;
        JPQLQuery<Board> query = from(board);

        if( (types != null && types.length > 0) && keyword != null ){ //검색 조건과 키워드가 있다면

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
        } //end if

        // bno > 0
        query.where(board.bno.gt(0L));

        // paging
        this.getQuerydsl().applyPagination(pageable, query);

        List<Board> list = query.fetch();
        long count = query.fetchCount();

		return null;
	}

	@Override
	public Page<Board> searchAllPageImpl(String[] types, String keyword, Pageable pageable) {
		
        QBoard board = QBoard.board;
        JPQLQuery<Board> query = from(board);

        if( (types != null && types.length > 0) && keyword != null ){ //검색 조건과 키워드가 있다면

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
        } //end if

        // bno > 0
        query.where(board.bno.gt(0L));

        // paging
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
        query.leftJoin(reply).on(reply.board.eq(board)); // left join

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

    // p629
//	@Override
//    public Page<BoardListReplyCountDTO> searchWithAll(String[] types, String keyword, Pageable pageable) {
//
//        QBoard board = QBoard.board;
//        QReply reply = QReply.reply;
//
//        JPQLQuery<Board> boardJPQLQuery = from(board);
//        boardJPQLQuery.leftJoin(reply).on(reply.board.eq(board)); //left join
//
//        getQuerydsl().applyPagination(pageable, boardJPQLQuery); //paging
//        List<Board> boardList = boardJPQLQuery.fetch();
//
//        boardList.forEach(board1 -> {
//        	System.out.println(board1.getBno());
//        	System.out.println(board1.getImageSet());
//        	System.out.println("-------------------------------");
//        });
//
//        return null;
//    }
	
	// p635
	@Override
	public Page<BoardListAllDTO> searchWithAll(String[] types, String keyword, Pageable pageable) {
		
		QBoard board = QBoard.board;
		QReply reply = QReply.reply;
		
		JPQLQuery<Board> boardJPQLQuery = from(board);
		boardJPQLQuery.leftJoin(reply).on(reply.board.eq(board)); //left join
		
		// p638 start : 검색조건 추가 ===================================================
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
            }//end for
            boardJPQLQuery.where(booleanBuilder);
        }
		// p638 end   ===================================================================

		boardJPQLQuery.groupBy(board);
		getQuerydsl().applyPagination(pageable, boardJPQLQuery); //paging

		JPQLQuery<Tuple> tupleJPQLQuery = boardJPQLQuery.select(board, reply.countDistinct());
		
		// List<Tuple>을 이용하는 방식은 Projections을 이용하는 방식보다 번거롭기는 하지만
		// 코드를 통해서 마음대로 커스터마이징 할 수 있다는 장점이 있다.
		// Projections은 select 대상을 지정하는 것
		List<Tuple> tupleList = tupleJPQLQuery.fetch();
		
        List<BoardListAllDTO> dtoList = tupleList.stream().map(tuple -> {

            Board board1 = (Board) tuple.get(board);
            long replyCount = tuple.get(1,Long.class);

            BoardListAllDTO dto = BoardListAllDTO.builder()
                    .bno(board1.getBno())
                    .title(board1.getTitle())
                    .writer(board1.getWriter())
                    .regDate(board1.getRegDate())
                    .replyCount(replyCount)
                    .build();

            // p637 start ===================================================================
            // BoardImage를 BoardImageDTO 처리할 부분
            List<BoardImageDTO> imageDTOS 
            	= board1.getImageSet()
            			.stream()
            			.sorted()
            			.map(boardImage -> BoardImageDTO.builder()
                            							.uuid(boardImage.getUuid())
                            							.fileName(boardImage.getFileName())
                            							.ord(boardImage.getOrd())
                            							.build())
            			.collect(Collectors.toList());

            dto.setBoardImages(imageDTOS); // 처리된 BoardImageDTO들을 추가
            // p637 end ===================================================================
            
            return dto;
        }).collect(Collectors.toList());

        long totalCount = boardJPQLQuery.fetchCount();

        return new PageImpl<>(dtoList, pageable, totalCount);		
	}
}
