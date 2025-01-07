package com.lec.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lec.board.domain.APIUser;

public interface APIUserRepository extends JpaRepository<APIUser, String> {
}
