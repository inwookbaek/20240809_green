package com.lec.board.service;

import com.lec.board.domain.Menu;

import jakarta.transaction.Transactional;

@Transactional
public interface MenuService {
	
	Long save(Menu menu);
	
}
