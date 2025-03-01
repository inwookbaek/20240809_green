package com.lec.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lec.board.domain.Member;

public interface MemberRepository extends JpaRepository<Member, String> {

}
