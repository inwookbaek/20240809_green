package com.lec.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lec.board.domain.Menu;

public interface MenuRepository extends JpaRepository<Menu, String>{

}
