package com.lec.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lec.jwt.domain.Todo;
import com.lec.jwt.repository.search.TodoSearch;

public interface TodoRepository extends JpaRepository<Todo, Long>, TodoSearch {
}