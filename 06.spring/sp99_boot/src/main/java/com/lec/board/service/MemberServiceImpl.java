package com.lec.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lec.board.domain.Member;
import com.lec.board.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

	private final MemberRepository memberRepository;

	@Override
	public List<Member> getAllMembers() {
		return memberRepository.findAll();
	}
	
}
