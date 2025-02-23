import React from "react";
import "./style.css";
import defaultProfileImage from "assets/image/defaultProfileImage.jpg";
import defaultBoardItemImage from "assets/image/defaultBoardItemImage.jpg";
import type { BoardListItem } from "types/interface";
import { useNavigate } from "react-router-dom";

interface Props {
  boardListItem: BoardListItem;
}

// component : Board List Item 컴퍼넌트
export default function BoardItem({ boardListItem }: Props) {

  // properites
  const {boardNumber, title, content, boardTitleImage} = boardListItem;
  const {favoriteCount, commentCount, viewCount} = boardListItem;
  const {writeDatetime, writeNickname, writeProfileImage} = boardListItem;

  // function : 내비게이트함수
  const navigator = useNavigate();

  // eventHandler : 게시글 아이템 클릭 이벤트
  const onClickHandler = ()=> {
    navigator(boardNumber);
  }

  // retnder : Board List Item 렌더링
  return (
    <div className='board-list-item'>
      <div className='board-list-item-main-box'>
        <div className='board-list-item-top'>
          <div className='board-list-item-profile-box'>
            <div
              className='board-list-item-profile-image'
              style={{
                backgroundImage: `url(${writeProfileImage ? writeProfileImage : defaultProfileImage})`,
              }}
            ></div>
          </div>
          <div className='board-list-item-write-box'>
            <div className='board-list-item-nickname'>
              {writeNickname}
            </div>
            <div className='board-list-item-write-datetime'>{writeDatetime}</div>
          </div>
        </div>
        <div className='board-list-item-middle'>
          <div className='board-list-item-title'>{title}</div>
          <div className='board-list-item-content'>{content}</div>
        </div>
        <div className='board-list-item-bottom'>
          <div className='board-list-item-counts'>
          {` 댓글 :  ${commentCount} · 좋아요 : ${favoriteCount} · 조회수 : ${viewCount}`}
          </div>
        </div>
      </div>
      {boardTitleImage !== null && (
        <div className='board-list-item-image-box'>
          <div
            className='board-list-item-image'
            style={{
              backgroundImage: `url(${boardTitleImage ? boardTitleImage : defaultBoardItemImage})`,
            }}
          ></div>
        </div>
      )}
    </div>
  );
}
