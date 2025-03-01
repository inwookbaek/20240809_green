import React, { useState } from "react";
import './App.css'
/*
  React Router 사용
  BrowserRouter: 애플리케이션에 라우팅 기능을 제공
  Routes: 여러 개의 Route를 감싸고, URL에 따라 적절한 컴포넌트를 렌더링
  Route: path 속성을 기준으로 특정 URL에 접근하면 해당 element가 화면에 표시됨
  Link: a 태그와 유사하지만, 페이지 새로고침 없이 이동 가능
*/
import { BrowserRouter, Routes, Route, Link } from "react-router-dom";

// 🔹 Mock 데이터 임포트 (실제 API 연동 전 가상의 데이터)
import { latestBoardListMock, top3BoardListMock, commentListMock, favoriteListMock } from "mocks";

// 🔹 각 페이지에서 사용할 개별 아이템 컴포넌트 임포트
import BoardItem from "components/BoardItem";
import Top3Item from "components/Top3Item";
import CommentItem from "components/CommentItem";
import FavoriteItem from "components/FavoriteItem";
import LoginBox from "components/LoginBox";
import Footer from "layouts/Footer";

function App() {

  const [value, setValue] = useState<string>('');

  return (
    <BrowserRouter>
      {/* 🔹 네비게이션 바: 사용자 페이지 이동을 위한 메뉴 */}
      <nav>
        <ul style={{ display: "flex", gap: "16px", justifyContent: "center", listStyle: "none", padding: 0 }}>
          {/* 🔹 Link 컴포넌트를 사용하여 페이지 이동 */}
          <li><Link to="/" style={{ textDecoration: "none", color: "black" }}>게시글 목록</Link></li>
          <li><Link to="/top3" style={{ textDecoration: "none", color: "black" }}>Top 3 게시글</Link></li>
          <li><Link to="/comment" style={{ textDecoration: "none", color: "black" }}>댓글 목록</Link></li>
          <li><Link to="/favorite" style={{ textDecoration: "none", color: "black" }}>좋아요 목록</Link></li>
          <li><Link to="/login" style={{ textDecoration: "none", color: "black" }}>로그인</Link></li>
        </ul>
      </nav>

      {/* 🔹 Routes: URL에 따라 렌더링할 페이지를 결정하는 영역 */}
      <Routes>
        <Route path="/" element={<BoardList />} />        {/* 🔹 게시글 목록 페이지 */}
        <Route path="/top3" element={<Top3List />} />     {/* 🔹 Top 3 게시글 페이지 */}
        <Route path="/comment" element={<CommentList />} /> {/* 🔹 댓글 목록 페이지 */}
        <Route path="/favorite" element={<FavoriteList />} /> {/* 🔹 좋아요 목록 페이지 */}
        <Route path="/login" element={<LoginBox label='이메일' type='text' placeholder='이메일 주소를 입력하세요!'
          value={value} setValue={setValue} error={false} message={'에러'}/>} /> {/* 🔹 로그인 페이지 */}
        <Route path="/board/:boardNumber" element={<BoardDetail />} /> {/* 🔹 특정 게시글 상세 페이지 */}
      </Routes>
      <Footer />
    </BrowserRouter>
  );
}

// 🔹 게시글 목록 페이지
function BoardList() {
  return (
    <>
      {/* 🔹 latestBoardListMock 배열을 순회하면서 개별 BoardItem 컴포넌트 렌더링 */}
      {latestBoardListMock.map(boardListItem => (
        <BoardItem key={boardListItem.boardNumber} boardListItem={boardListItem} />
      ))}
    </>
  );
}

// 🔹 Top 3 게시글 목록 페이지
function Top3List() {
  return (
    <div style={{ display: "flex", justifyContent: "center", gap: '24px' }}>
      {/* 🔹 top3BoardListMock 배열을 순회하면서 개별 Top3Item 컴포넌트 렌더링 */}
      {top3BoardListMock.map(top3ListItem => (
        <Top3Item key={top3ListItem.boardNumber} top3ListItem={top3ListItem} />
      ))}
    </div>
  );
}

// 🔹 댓글 목록 페이지
function CommentList() {
  return (
    <div style={{ padding: '0 20px', display: "flex", flexDirection: "column", gap: '30px' }}>
      {/* 🔹 commentListMock 배열을 순회하면서 개별 CommentItem 컴포넌트 렌더링 */}
      {commentListMock.map(commentListItem => (
        <CommentItem commentListItem={commentListItem} />
      ))}
    </div>
  );
}

// 🔹 좋아요 목록 페이지
function FavoriteList() {
  return (
    <div style={{ display: "flex", flexDirection: "row", columnGap: '30px', rowGap: '20px' }}>
      {/* 🔹 favoriteListMock 배열을 순회하면서 개별 FavoriteItem 컴포넌트 렌더링 */}
      {favoriteListMock.map(favoriteListItem => (
        <FavoriteItem favoriteListItem={favoriteListItem} />
      ))}
    </div>
  );
}

// 🔹 게시글 상세 페이지 (임시)
function BoardDetail() {
  return <h1>게시글 상세 페이지</h1>;
}

export default App;
