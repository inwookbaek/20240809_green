package com.lec.board.entity;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import com.lec.board.dto.request.board.PatchBoardRequestDto;
import com.lec.board.dto.request.board.PostBoardRequestDto;
import com.lec.board.dto.response.board.PatchBoardResponseDto;

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
	
	public BoardEntity(PostBoardRequestDto dto, String email) {
		
		Date now = Date.from(Instant.now());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd H:mm:ss");
		String writeDateTime = sdf.format(now);
		
		this.title = dto.getTitle();
		this.content = dto.getContent();
		this.writeDatetime = writeDateTime;
		this.favoriteCount = 0;
		this.commentCount = 0;
		this.viewCount = 0;
		this.writerEmail = email;

	}
	
	public void increaseViewCount() {
		this.viewCount++;
	}
	
	public void increaseFavoriteCount() {
		this.favoriteCount++;
	}
	
	public void decreaseFavoriteCount() {
		this.favoriteCount--;
	}
	
	public void increaseCommentCount() {
		this.commentCount++;
	}
	
	public void decreaseCommentCount() {
		this.commentCount--;
	}
	
	public void patchBoard(PatchBoardRequestDto dto) {
		this.title = dto.getTitle();
		this.content = dto.getContent();
	}
	
}
