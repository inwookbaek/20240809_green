package com.lec.board.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "board")
public class BoardImage implements Comparable<BoardImage>{

	@Id
	private String uuid;
	private String fileName;
	private int ord;
	
	@ManyToOne
	private Board board;

	@Override
	public int compareTo(BoardImage other) {		
		return this.ord - other.ord;
	}
	
	public void changeBoard(Board board) {
		this.board = board;
	}
}
