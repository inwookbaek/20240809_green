package com.lec.board.dto.object;

import java.util.ArrayList;
import java.util.List;

import com.lec.board.entity.BoardListViewEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BoardListItem {
	
  private int boardNumber;
  private String title;
  private String content;
  private String boardTitleImage;
  private int favoriteCount;
  private int commentCount;
  private int viewCount;
  private String writeDatetime;
  private String writerNickname;
  private String writerProfileImage;

  public BoardListItem(BoardListViewEntity boardListViewEntity) {
	  this.boardNumber = boardListViewEntity.getBoardNumber();
	  this.title = boardListViewEntity.getTitle();
	  this.content = boardListViewEntity.getContent();
	  this.boardTitleImage = boardListViewEntity.getTitleImage();
	  this.favoriteCount = boardListViewEntity.getFavoriteCount();
	  this.commentCount = boardListViewEntity.getCommentCount();
	  this.viewCount = boardListViewEntity.getViewCount();
	  this.writeDatetime = boardListViewEntity.getWriteDatetime();
	  this.writerNickname = boardListViewEntity.getWriterNickname();
	  this.writerProfileImage = boardListViewEntity.getWriterProfileImage();
  }   
  
  public static List<BoardListItem> getList(List<BoardListViewEntity> boardListViewEntities) {
	  List<BoardListItem> list = new ArrayList<>();
	  for(BoardListViewEntity boardListViewEntity : boardListViewEntities) {
		  BoardListItem boardListItem = new BoardListItem(boardListViewEntity);
		  list.add(boardListItem);
	  }
	  return list;
  }
}
