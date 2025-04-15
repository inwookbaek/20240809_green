package com.lec.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lec.board.entity.BoardEntity;
import com.lec.board.repository.resultSet.GetBoardResultSet;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Integer>{

	@Query(
		value = """ 
			select B.board_number as boardNumber     		
		         , B.title as title                  		
				  , B.content as content              		
				  , B.write_datetime as writeDatetime 		
				  , B.writer_email as writerEmail     		
				  , U.nickname as writerNickname      		
				  , U.profile_image as writerProfileImage   
		      from board as B                        		
		     inner join user as U                    		
		        on B.writer_email = U.email          		
		     where board_number = ?1 """,  nativeQuery =  true)
	GetBoardResultSet getBoard(Integer boardNumber);

	BoardEntity findByBoardNumber(Integer boardNumber);

	boolean existsByBoardNumber(Integer boardNumber);
}
