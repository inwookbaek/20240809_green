package com.lec.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lec.board.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

	boolean existsByEmail(String email);
	boolean existsByNickname(String nickname);
	boolean existsByTelNumber(String telNumber);
	
	UserEntity findByEmail(String email);

}
