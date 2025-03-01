package com.lec.board.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@Getter
@Setter
@ToString
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private final String mid;
	private final String mpw;
}
