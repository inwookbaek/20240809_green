import React, { ChangeEvent, useRef, useState, KeyboardEvent, useEffect } from 'react';
import "./style.css";
import { useLocation, useNavigate, useParams } from 'react-router-dom';
import { AUTH_PATH, BOARD_DETAIL_PATH, BOARD_PATH, BOARD_UPDATE_PATH, BOARD_WRITE_PATH, MAIN_PATH, SEARCH_PATH, USER_PATH } from 'constant';
import { useCookies } from 'react-cookie';
import { useBoardStore, useLoginUserStore } from 'stores';

/*
📌 코드 주요 기능
  Header 컴포넌트는 웹 애플리케이션의 상단 헤더 영역을 담당합니다.
  로그인 여부에 따라 마이페이지 버튼 또는 로그인 버튼을 표시합니다.
  검색 버튼을 클릭하면 입력 필드가 나타나고, 입력한 검색어로 이동합니다.
  게시판 작성 페이지에서는 파일 업로드 버튼이 활성화됩니다.

  Header에서 필요한 라이브러리
  npm i react-cookie
  npm i zustand -> redux를 보다 간편하게 사용하기 위한 라이브러리
*/

// Header 컴포넌트 정의
export default function Header() {
  
  // ✅ 로그인 사용자 정보와 관련된 상태 및 함수
  const { loginUser, setLoginUser, resetLogiUser } = useLoginUserStore();

  // ✅ 현재 URL 정보를 가져오기 위한 React Router Hook
  const { pathname } = useLocation();
  const { userEmail, searchWord } = useParams();
  
  // ✅ 쿠키 사용을 위한 Hook
  const [cookie, setCookie] = useCookies();

  // ✅ 로그인 상태 관리 (초기값 false)
  const [isLogin, setLogin] = useState<boolean>(false);

  // ✅ 페이지 이동을 위한 Hook
  const navigate = useNavigate();
  
  // ✅ 현재 사용자가 위치한 페이지에 대한 상태 체크
  const isAuthPage = pathname.startsWith(AUTH_PATH());  // 로그인 관련 페이지인지 확인
  const isMainPage = pathname === MAIN_PATH();  // 메인 페이지인지 확인
  const isSearchPage = pathname.startsWith(SEARCH_PATH(''));  // 검색 페이지인지 확인
  const isBoardDetailPage = pathname.startsWith(BOARD_PATH() + '/' + BOARD_DETAIL_PATH(''));  // 게시글 상세 페이지인지 확인
  const isBoardWritePage = pathname.startsWith(BOARD_PATH() + '/' + BOARD_WRITE_PATH());  // 게시글 작성 페이지인지 확인
  const isBoardUpdatePage = pathname.startsWith(BOARD_PATH() + '/' + BOARD_UPDATE_PATH(''));  // 게시글 수정 페이지인지 확인
  const isUserPage = pathname.startsWith(USER_PATH(''));  // 사용자 페이지인지 확인

  // ✅ 로고 클릭 시 메인 페이지로 이동하는 함수
  const onLogClickHandler = () => navigate(MAIN_PATH());

  // ✅ 검색 버튼 컴포넌트
  const SearchButton = () => {
    const searchButtonRef = useRef<HTMLDivElement | null>(null);  // 버튼을 참조할 Ref 생성
    const [status, setStatus] = useState<boolean>(false);  // 검색 창 표시 여부 상태
    const [word, setWord] = useState<string>('');  // 검색어 상태
    const { searchWord } = useParams();  // URL에서 검색어 가져오기

    // 🔹 검색어 입력 필드 변경 이벤트 핸들러
    const onSearchWordChangeHandler = (e: ChangeEvent<HTMLInputElement>) => {
      setWord(e.target.value);  // 입력된 값을 상태에 저장
    };

    // 🔹 키보드 이벤트 핸들러 (Enter 키 입력 시 검색 실행)
    const searchWordKeyDownHandler = (e: KeyboardEvent<HTMLInputElement>) => {
      if (e.key !== 'Enter') return;  // Enter 키가 아니면 무시
      if (!searchButtonRef.current) return;  // 버튼이 없으면 실행 중지
      searchButtonRef.current.click();  // 버튼 클릭 실행
    };

    // 🔹 검색 버튼 클릭 이벤트 핸들러
    const onSearchButtonClickHandler = () => {
      if (!status) {
        setStatus(true);  // 검색창 활성화
        return;
      }
      navigate(SEARCH_PATH(word));  // 검색 페이지로 이동
    };

    // 🔹 검색어가 변경될 때 실행되는 useEffect (URL에서 검색어를 가져와 상태 설정)
    useEffect(() => {
      if (searchWord) {
        setWord(searchWord);
        setStatus(true);
      }
    }, [searchWord]);

    // 🔹 검색창이 비활성화된 경우 (아이콘만 표시)
    if (!status) 
      return (
        <div className='icon-button' onClick={onSearchButtonClickHandler}>
          <div className="icon search-light-icon"></div>
        </div>
      );

    // 🔹 검색창이 활성화된 경우 (입력 필드와 버튼 표시)
    return (
      <div className='header-search-input-box'>
        <input className='header-search-input' type='text' placeholder='검색어를 입력하세요!!' value={word}
          onChange={onSearchWordChangeHandler} onKeyDown={searchWordKeyDownHandler}/>
        <div ref={searchButtonRef} className='icon-button' onClick={onSearchButtonClickHandler}>
          <div className="icon search-light-icon"></div>
        </div>
      </div>
    );
  };

  // ✅ 마이페이지 버튼 컴포넌트
  const MyPageButton = () => {
    const onMyPageButtonClickHandler = () => {
      if (loginUser) navigate(USER_PATH(loginUser.email));
    };

    const onLogoutButtonClickHandler = () => {
      resetLogiUser();  // 로그아웃 처리
      navigate(MAIN_PATH());  // 메인 페이지로 이동
    };

    const onLoginButtonClickHandler = () => navigate(AUTH_PATH());  // 로그인 페이지로 이동

    // 🔹 로그인한 경우
    if (isLogin) {
      return userEmail === loginUser?.email ? (
        <div className='white-button' onClick={onLogoutButtonClickHandler}>로그아웃</div>
      ) : (
        <div className='white-button' onClick={onMyPageButtonClickHandler}>마이페이지</div>
      );
    }

    // 🔹 로그인하지 않은 경우
    return <div className='black-button' onClick={onLoginButtonClickHandler}>로그인</div>;
  };

  // ✅ 파일 업로드 버튼 컴포넌트
  const UploadButton = () => {
    const { title, content, boardImageFileList } = useBoardStore();

    const onUploadButtonClickHandler = () => {
      // 파일 업로드 처리 로직 (현재는 비어 있음)
    };

    // 🔹 제목과 내용이 있는 경우 (업로드 가능)
    if (title && content) 
      return <div className='black-button' onClick={onUploadButtonClickHandler}>파일 업로드</div>;

    // 🔹 제목 또는 내용이 없는 경우 (업로드 불가)
    return <div className='disable-button'>파일 업로드</div>;
  };

  // ✅ 최종적으로 렌더링되는 JSX
  return (
    <div id="header">
      <div className="header-container">
        <div className="header-left-box" onClick={onLogClickHandler}>
          <div className="icon-box">
            <div className="icon logo-dark-icon"></div>
          </div>
          <div className="header-logo">GILCNS Application</div>
        </div>
        <div className="header-right-box">
          {(isAuthPage || isMainPage || isSearchPage || isBoardDetailPage) && <SearchButton />}
          {(isMainPage || isSearchPage || isBoardDetailPage || isUserPage) && <MyPageButton />}
          {(isBoardWritePage || isBoardUpdatePage) && <UploadButton />}
        </div>
      </div>
    </div>
  );
}
