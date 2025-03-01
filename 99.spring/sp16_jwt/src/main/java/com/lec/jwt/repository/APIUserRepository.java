package com.lec.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lec.jwt.domain.APIUser;

public interface APIUserRepository extends JpaRepository<APIUser, String> {
}
