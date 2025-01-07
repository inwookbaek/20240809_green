package com.lec.board.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BoardListReplyCountDTO {

	private Long bno;
	private String title;
	private String writer;
	private LocalDateTime regDate;
	private LocalDateTime modDate;
	private Long replyCount;
}
