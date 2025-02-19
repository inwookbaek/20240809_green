package com.lec.mybatis.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {

    private long id;	// 게시물의 ID
    private String title;	// 제목
    private String content;	// 내용
    private String writer;	// 작성자
    private long views;	// 조회수
    private LocalDateTime createdAt;	// 작성일자
    private LocalDateTime modifiedAt;	// 수정일자
    
}
