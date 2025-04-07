package com.lec.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lec.board.entity.ImageEntity;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, Integer>{

	List<ImageEntity> findByBoardNumber(Integer boardNumber);
	
	@Transactional
	void deleteByBoardNumber(Integer boardNumber);
}
