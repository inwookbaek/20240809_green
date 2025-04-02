package com.lec.board.entity;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import com.lec.board.dto.request.board.PostCommentRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "comment")
@Table(name = "comment")
@NoArgsConstructor
@AllArgsConstructor
public class CommentEntity {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int commentNumber;
	private String content;
	private String writeDatetime;
	private String userEmail;
	private int boardNumber;
	
	public CommentEntity(PostCommentRequestDto dto, Integer boardNumber, String email) {
		
		Date now = Date.from(Instant.now());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String writeDatetime = sdf.format(now);
		
		this.content = dto.getContent();
		this.writeDatetime = writeDatetime;
		this.userEmail = email;
		this.boardNumber = boardNumber;
			
	}
}
