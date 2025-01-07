package com.lec.board.repository;

import java.time.LocalDate;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lec.board.domain.Todo;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class TodoRepositoryTests {

	@Autowired
	private TodoRepository todoRepository;
	
	@Test
	public void testInsert() {
		IntStream.rangeClosed(1, 100).forEach(i -> {
			Todo todo = Todo.builder()
					.title("Todo.... " + i)
					.dueDate(LocalDate.of(2024, (i%12)+1, (i%30)+1))
					.writer("user"+(i%10))
					.complete(false)
					.build();
			todoRepository.save(todo);
		});
	}
}
