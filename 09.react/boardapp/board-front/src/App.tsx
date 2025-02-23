import React from "react";
import { BrowserRouter, Routes, Route, Link } from "react-router-dom";

import { latestBoardListMock, top3BoardListMock, commentListMock, favoriteListMock } from "mocks";
import BoardItem from "components/BoardItem";
import Top3Item from "components/Top3Item";
import CommentItem from "components/CommentItem";
import FavoriteItem from "components/FavoriteItem";

function App() {
  return (
    <BrowserRouter>
      <nav>
        <ul style={{ display: "flex", gap: "16px", justifyContent: "center", listStyle: "none", padding: 0 }}>
          <li><Link to="/" style={{ textDecoration: "none", color: "black" }}>게시글 목록</Link></li>
          <li><Link to="/top3" style={{ textDecoration: "none", color: "black" }}>Top 3 게시글</Link></li>
          <li><Link to="/comment" style={{ textDecoration: "none", color: "black" }}>댓글목록</Link></li>
          <li><Link to="/favorite" style={{ textDecoration: "none", color: "black" }}>좋아요목록</Link></li>
        </ul>
      </nav>
      <Routes>
        <Route path="/" element={<BoardList />} />
        <Route path="/top3" element={<Top3List />} />
        <Route path="/comment" element={<CommentList />} />
        <Route path="/favorite" element={<FavoriteList />} />
        <Route path="/board/:boardNumber" element={<BoardDetail />} />
      </Routes>
    </BrowserRouter>
  );
}

// 🔹 게시글 목록 페이지
function BoardList() {
  return (
    <>
      {latestBoardListMock.map(boardListItem => (
        <BoardItem key={boardListItem.boardNumber} boardListItem={boardListItem} />
      ))}
    </>
  );
}

// 🔹 Top 3 목록 페이지
function Top3List() {
  return (
    <div style={{ display: "flex", justifyContent: "center", gap: '24px'}}>
      {top3BoardListMock.map(top3ListItem => (
        <Top3Item key={top3ListItem.boardNumber} top3ListItem={top3ListItem} />
      ))}
    </div>
  );
}

// 🔹 Comment 목록 페이지
function CommentList() {
  return (
    <div style={{ padding: '0 20px', display: "flex", flexDirection: "column", gap: '30px'}}>
      {commentListMock.map(commentListItem => (
        <CommentItem commentListItem={commentListItem} />
      ))}
    </div>
  );
}

// 🔹 Favorite 목록 페이지
function FavoriteList() {
  return (
    <div style={{ display: "flex", flexDirection: "row", columnGap: '30px', rowGap: '20px'}}>
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
