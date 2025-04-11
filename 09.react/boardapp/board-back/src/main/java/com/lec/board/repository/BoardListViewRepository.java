package com.lec.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lec.board.entity.BoardListViewEntity;

@Repository
public interface BoardListViewRepository extends JpaRepository<BoardListViewEntity, Integer> {

	List<BoardListViewEntity> findByOrderByWriteDatetimeDesc();
	
	// Top3 = limit 3, write_datetime > ?writeDatetime, favorite_count desc, comment_count desc, view_count desc, write_datetiem desc
	List<BoardListViewEntity> findTop3ByWriteDatetimeGreaterThanOrderByFavoriteCountDescCommentCountDescViewCountDescWriteDatetimeDesc(String writeDatetime);
	
	// 검색어 : where title like %% or content like %% order by writedatetime desc
	List<BoardListViewEntity> findByTitleContainsOrContentContainsOrderByWriteDatetimeDesc(String title, String content);
}
