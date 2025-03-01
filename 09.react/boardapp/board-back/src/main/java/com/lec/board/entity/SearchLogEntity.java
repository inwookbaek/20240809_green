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
@Entity(name = "search_log")
@Table(name = "search_log")
@NoArgsConstructor
@AllArgsConstructor
public class SearchLogEntity {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sequence;
	private String search_word;
	private String relation_word;
	private boolean relation;

}
