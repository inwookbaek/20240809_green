package com.lec.board.dto;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
	
	private Long bno;
	
	@NotEmpty
	@Size(min = 3, max = 100)
	private String title;
	
	@NotEmpty
	private String content;	
	
	@NotEmpty
	private String writer;
	
	private LocalDateTime regDate;
	
	private LocalDateTime modDate;
	
	// 첨부파일의 이름
	private List<String> fileNames;
}