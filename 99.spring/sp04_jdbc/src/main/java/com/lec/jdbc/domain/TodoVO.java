package com.lec.jdbc.domain;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TodoVO {

	private Long tno;
	private String title;
	private LocalDate dueDate;
	private boolean finished;
	
}
