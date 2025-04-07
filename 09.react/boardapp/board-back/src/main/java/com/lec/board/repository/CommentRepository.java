package com.lec.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lec.board.entity.CommentEntity;
import com.lec.board.repository.resultSet.GetCommentListResultSet;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer>{

	@Query(
		value = "select U.nickname       as nickname        " +
			    "     , U.profile_image  as profileImage    " +
				"	  , C.write_datetime as writeDatetime   " +
				"	  , C.content        as content         " + 
			    "  from comment   as C                      " + 
				" inner join user as U                      " +
				"    on C.user_email = U.email              " +
			    " where C.board_number = ?1    " +
			    " order by writeDatetime desc              "
	  , nativeQuery = true)
	List<GetCommentListResultSet> getCommentList(Integer boardNumber);
	
	@Transactional
	void deleteByBoardNumber(Integer boardNumber);
}
