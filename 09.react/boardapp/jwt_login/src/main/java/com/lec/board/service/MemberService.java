package com.lec.board.service;

import com.lec.board.jwt.JwtToken;

public interface MemberService {

	public JwtToken signIn(String username, String password);
	
}
