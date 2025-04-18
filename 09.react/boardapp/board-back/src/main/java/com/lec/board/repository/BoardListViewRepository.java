package com.lec.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lec.board.entity.BoardListViewEntity;

@Repository
public interface BoardListViewRepository extends JpaRepository<BoardListViewEntity, Integer> {

	List<BoardListViewEntity> findByOrderByWriteDatetimeDesc();
	
	//	select * from  board_list_view
	//	 where write_datetime > '2025-04-08 19:17:28'
	//order by favorite_count    desc
	//       , comment_count     desc
	//       , view_count        desc
	// 		 , write_datetime    desc
	//	 limit 3;
    List<BoardListViewEntity> findTop3ByWriteDatetimeGreaterThanOrderByFavoriteCountDescCommentCountDescViewCountDescWriteDatetimeDesc(String writeDatetime);
    
    @Query(value = """
		select * from  board_list_view
		 where write_datetime > ?1
      order by write_datetime    desc
             , favorite_count    desc
	         , comment_count     desc
	         , view_count        desc
		 limit 3 """, nativeQuery = true)
    List<BoardListViewEntity> getTop3BoardList(String sevenDaysAgo);
	
	// 검색어 : where title like %% or content like %% order by writedatetime desc
    List<BoardListViewEntity> findByTitleContainsOrContentContainsOrderByWriteDatetimeDesc(String title, String content);
    
    // %?1% 에러표시는 되지만 실제는 에러가 아님(에러표시가 눈에 거슬려 주석처리함)
//    @Query(value = """
//		select * from  board_list_view
//		 where title   like %?1%                 
//		    or content like %?1%
//      order by write_datetime    desc """, nativeQuery = true)     
//	List<BoardListViewEntity> getBoardTitleContentContains(String title);

    List<BoardListViewEntity> findByWriterEmailOrderByWriteDatetimeDesc(String writerEmail);
	
}
