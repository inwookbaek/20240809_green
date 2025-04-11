package com.lec.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lec.board.dto.response.search.GetRelationListResponseDto;
import com.lec.board.entity.SearchLogEntity;
import com.lec.board.repository.resultSet.GetPopularListResultSet;
import com.lec.board.repository.resultSet.GetRelationListResultSet;

@Repository
public interface SearchLogRepository extends JpaRepository<SearchLogEntity, Integer> {

	@Query(value= """
		select search_word as searchWord, count(search_word) as count
		  from search_log
		 where relation is false
		 group by search_word
		 order by count desc
		 limit 15 """, nativeQuery = true)
	List<GetPopularListResultSet> getPopularList();	
	
	@Query(value= """
		select relation_word as searchWord, count(relation_word) as count
		  from search_log
		 where search_word = ?1
		   and relation_word is not null
		 group by relation_word
		 order by count desc
		 limit 15 """, nativeQuery = true)
	List<GetRelationListResultSet> getRelationList(String searchWord);	
}
