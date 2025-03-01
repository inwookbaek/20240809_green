package com.lec.board.service;

import org.springframework.stereotype.Service;

import com.lec.board.domain.Menu;
import com.lec.board.repository.MenuRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

	private final MenuRepository menuRepository;

	@Override
	public Long save(Menu menu) {
		Long menuId = menuRepository.save(menu).getMenuId();
		return menuId;
	}
	
}
