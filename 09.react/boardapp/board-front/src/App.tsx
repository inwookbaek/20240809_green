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
import Main from "views/Main";
import Authentication from "views/Authentication";
import Search from "views/Search";
import User from "views/User";
import BoardWrite from "views/Board/Write";
import BoardDetail from "views/Board/Detail";
import BoardUpdate from "views/Board/Update";
import Container from "layouts/Container";
import { 
  MAIN_PATH,
  AUTH_PATH ,
  SEARCH_PATH,
  USER_PATH,
  BOARD_PATH,
  BOARD_WRITE_PATH,
  BOARD_DETAIL_PATH,
  BOARD_UPDATE_PATH} from "constant";

function App() {

  const [value, setValue] = useState<string>('');
  /*
    /                           : Main
    /auth                       : 로그인, 회원가입
    /search/searchword          : 검색화면(Search)
    /user/userEmail             : 사용자(User)
    /board/deatail/:boardNumber : 게시판 상세보기(BoardDetail)  
    /board/write                : 게시글 쓰기(BoardWrite)
    /board/update/:boardNumber  : 게시글 수정(BoardUpdate)
  */
  return (
    <Routes>
      <Route element={<Container />}>
        <Route path={MAIN_PATH()}element={<Main />} />
        <Route path={AUTH_PATH()} element={<Authentication />} />
        <Route path={SEARCH_PATH(':searchWord')} element={<Search />} />
        <Route path={USER_PATH(':userEmail')} element={<User />} />
        <Route path={BOARD_PATH()} >
          <Route path={BOARD_WRITE_PATH()} element={<BoardWrite />} />
          <Route path={BOARD_DETAIL_PATH(':boardNumber')} element={<BoardDetail />} />
          <Route path={BOARD_UPDATE_PATH(':boardNumber')} element={<BoardUpdate />} />
        </Route>
        <Route path="*" element={<h1 style={{ textAlign: 'center', color: 'red' }}>404 Not Found!!!</h1>} />
      </Route>
    </Routes>
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

export default App;
