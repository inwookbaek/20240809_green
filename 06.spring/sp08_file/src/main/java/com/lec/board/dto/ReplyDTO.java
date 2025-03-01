package com.lec.board.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lec.board.domain.BaseEntity;

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

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") 
	private LocalDateTime regDate;
	
	@JsonIgnore   // 화면에 출력하지 않기 때문에 변환할 때 제외
	private LocalDateTime modDate;
	
}
