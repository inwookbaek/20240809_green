package com.lec.board.service;

import java.util.List;

import com.lec.board.domain.Member;

import jakarta.transaction.Transactional;

@Transactional
public interface MemberService {
	List<Member> getAllMembers();
}
