package com.lec.board.service;

public interface JwtService {
	
	 String generateToken(String username);
	 String getUsernameFromToken(String token);
	 public boolean validateToken(String token);
}
