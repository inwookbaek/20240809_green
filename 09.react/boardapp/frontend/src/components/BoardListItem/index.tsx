import React from "react";
import "./style.css";

// component : Board List Item 컴퍼넌트
export default function BoardListItem() {
  // retnder : Board List Item 렌더링
  return (
    <div className='board-list-item'>
      <div className='board-list-item-main-box'>
        <div className='board-list-item-top'>
          <div className='board-list-item-profile-box'>
            <div
              className='board-list-item-profile-image'
              style={{
                backgroundImage: `url(https://www.flaticon.com/kr/free-icons/)`,
              }}
            ></div>
          </div>
          <div className='board-list-item-write-box'>
            <div className='board-list-item-nickname'>
              {"안녕하세요? 나는 홍길동"}
            </div>
            <div className='board-list-item-write-datetime'>{"2025.02.18"}</div>
          </div>
        </div>
        <div className='board-list-item-middle'>
          <div className='board-list-item-title'>
            {"오늘 점심 뭐 먹지? 맜있는 거 먹고 싶은 데!!"}
          </div>
          <div className='board-list-item-content'>
            {
              "🍛 한식 \
  제육볶음 + 달걀프라이 + 김치찌개 \
  뚝배기불고기 (국물 자작하게~) \
  김치찜 (밥에 슥슥 비벼서!)"
            }
          </div>
        </div>
        <div className='board-list-item-bottom'>
          <div className='board-list-item-counts'>
            {" 댓글 : 0  /  좋아요 : 0  /  조회수 : 0"}
          </div>
        </div>
      </div>
      <div className='board-list-item-image-box'>
        <div
          className='board-list-item-image'
          style={{
            backgroundImage: `url(https://www.flaticon.com/kr/free-icons/)`,
          }}
        ></div>
      </div>
    </div>
  );
}
