package com.lec.axios.repository.search;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.lec.axios.domain.Board;
import com.lec.axios.domain.QBoard;
import com.lec.axios.domain.QReply;
import com.lec.axios.dto.BoardListReplyCountDTO;
import com.querydsl.core.BooleanBuilder;
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

}
