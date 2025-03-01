package com.lec.board.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplyDTO {
	
	private Long rno;
	
	@NotNull
	private Long bno;
	
	@NotEmpty
	private String replyText;
	
	@NotEmpty
	private String replyer;
	

	private LocalDateTime regDate, modDate;
	
}
