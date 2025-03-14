package com.lec.board.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "board")
@Table(name = "board")
@NoArgsConstructor
@AllArgsConstructor
public class BoardEntity {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int boardNumber;
	private String title;
	private String content;
	private String writeDatetime;
	private int favoriteCount;
	private int commentCount;
	private int viewCount;
	private String writerEmail;
}
