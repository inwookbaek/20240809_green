import React from "react";
import "./style.css"; // 스타일 시트 임포트
import defaultProfileImage from "assets/image/defaultProfileImage.jpg"; // 기본 프로필 이미지
import defaultBoardItemImage from "assets/image/defaultBoardItemImage.jpg"; // 기본 게시글 대표 이미지
import type { BoardListItem } from "types/interface"; // 게시글 타입 정의 임포트
import { useNavigate } from "react-router-dom"; // 페이지 이동을 위한 useNavigate 훅 임포트

// 🔹 Props 인터페이스 정의
interface Props {
  boardListItem: BoardListItem; // 게시글 정보를 담고 있는 객체
}

// 🔹 Board List Item 컴포넌트 (게시글 목록에서 개별 게시글 아이템을 렌더링)
export default function BoardItem({ boardListItem }: Props) {
  // 🔹 Destructuring: boardListItem에서 필요한 값 추출
  const { boardNumber, title, content, boardTitleImage } = boardListItem; // 게시글 정보
  const { favoriteCount, commentCount, viewCount } = boardListItem; // 게시글 통계 정보
  const { writeDatetime, writeNickname, writeProfileImage } = boardListItem; // 작성자 정보

  // 🔹 useNavigate 훅을 사용하여 페이지 이동 기능 설정
  const navigator = useNavigate();

  // 🔹 게시글 아이템 클릭 이벤트 핸들러
  const onClickHandler = () => {
    navigator(`/board/${boardNumber}`); // 클릭 시 해당 게시글 상세 페이지로 이동
  };

  // 🔹 Board List Item 컴포넌트 렌더링
  return (
    <div className="board-list-item" onClick={onClickHandler}> {/* 클릭 시 게시글 상세 페이지 이동 */}
      {/* 🔹 게시글 내용 영역 */}
      <div className="board-list-item-main-box">
        {/* 🔹 게시글 상단: 작성자 정보 */}
        <div className="board-list-item-top">
          {/* 작성자 프로필 이미지 */}
          <div className="board-list-item-profile-box">
            <div
              className="board-list-item-profile-image"
              style={{
                backgroundImage: `url(${writeProfileImage ? writeProfileImage : defaultProfileImage})`,
              }}
            ></div>
          </div>
          {/* 작성자 닉네임 및 작성일 */}
          <div className="board-list-item-write-box">
            <div className="board-list-item-nickname">{writeNickname}</div>
            <div className="board-list-item-write-datetime">{writeDatetime}</div>
          </div>
        </div>

        {/* 🔹 게시글 본문 영역 */}
        <div className="board-list-item-middle">
          {/* 게시글 제목 */}
          <div className="board-list-item-title">{title}</div>
          {/* 게시글 내용 */}
          <div className="board-list-item-content">{content}</div>
        </div>

        {/* 🔹 게시글 하단: 댓글, 좋아요, 조회수 */}
        <div className="board-list-item-bottom">
          <div className="board-list-item-counts">
            {`댓글 : ${commentCount} · 좋아요 : ${favoriteCount} · 조회수 : ${viewCount}`}
          </div>
        </div>
      </div>

      {/* 🔹 게시글 이미지가 있을 경우 렌더링 */}
      {boardTitleImage !== null && (
        <div className="board-list-item-image-box">
          <div
            className="board-list-item-image"
            style={{
              backgroundImage: `url(${boardTitleImage ? boardTitleImage : defaultBoardItemImage})`,
            }}
          ></div>
        </div>
      )}
    </div>
  );
}
