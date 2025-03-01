import React from 'react'
import './style.css'

import top3BoardItemImage from "assets/image/top3BoardItemImage.jpg";
import { BoardListItem } from 'types/interface';
import { useNavigate } from 'react-router-dom';

interface Props {
  top3ListItem: BoardListItem;
}

// component: Top 3 List Item
export default function Top3Item({ top3ListItem }: Props) {
  
  // properties
  const {boardNumber, title, content, boardTitleImage} = top3ListItem;
  const {favoriteCount, commentCount, viewCount} = top3ListItem;
  const {writeDatetime, writeNickname, writeProfileImage} = top3ListItem;

  // function : 내비게이트함수
  const navigator = useNavigate();

  // eventHandler : 게시글 아이템 클릭 이벤트
  const onClickHandler = ()=> {
    navigator(boardNumber);
  }

  // render : Top 3 List Item component rendering
  return (
    <div className='top-3-list-item' style={{ backgroundImage: `url(${boardTitleImage})` }} onClick={onClickHandler}>
      <div className='top-3-list-item-main-box'>
        <div className='top-3-list-item-top'>
          <div className='top-3-list-item-profile-box'>
            <div className='top-3-list-item-profile-image' style={{backgroundImage: `url(${writeProfileImage ? writeProfileImage : top3BoardItemImage})`}}></div>
          </div>
          <div className='top-3-list-item-write-box'>
            <div className='top-3-list-item-nickname'>{writeNickname}</div>
            <div className='top-3-list-item-write-date'>{writeDatetime}</div>
          </div>
        </div>
        <div className='top-3-list-item-middle'>
          <div className='top-3-list-item-title'>{title}</div>
          <div className='top-3-list-item-content'>{content}</div>
        </div>
        <div className='top-3-list-item-bottom'>
          <div className='top-3-list-item-counts'>
            {` 댓글 :  ${commentCount} · 좋아요 : ${favoriteCount} · 조회수 : ${viewCount}`}
          </div>
        </div>
      </div>
    </div>
  )
}
