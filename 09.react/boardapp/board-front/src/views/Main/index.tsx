import React, { useEffect, useState } from 'react'
import './style.css'
import Top3Item from 'components/Top3Item';
import { latestBoardListMock, top3BoardListMock } from 'mocks';
import { BoardListItem } from 'types/interface';
import BoardItem from 'components/BoardItem';
import Pagination from 'components/Pagination';
import { useNavigate } from 'react-router-dom';
import { SEARCH_PATH } from 'constant';
import { GetLatestBoardListResponseDto, GetTop3BoardListResponseDto } from 'apis/response/board';
import { ResponseDto } from 'apis/response';
import { getLatestBoardListRequest, getPopularListRequest, getTop3BoardListRequest } from 'apis';
import { usePagination } from 'hooks';
import { GetPopularListResponseDto } from 'apis/response/search';

export default function Main() {

  const navigate = useNavigate();
  
  const MainTop = () => {

    const [top3BoardList, setTop3BoardList] = useState<BoardListItem[] | null>([]);

    const getTop3BoardListResponse = (responseBody: GetTop3BoardListResponseDto | ResponseDto | null ) => {
      if(!responseBody) return;
      const { code } = responseBody;
      if(code === 'DBE') alert('데이터베이스 오류입니다!!');
      if(code !== 'SU') return;
      
      const { top3List } = responseBody as GetTop3BoardListResponseDto;
      console.log("top3List ==> ", top3List);
      setTop3BoardList(top3List);
    }

    useEffect(() => {
      // setTop3BoardList(top3BoardListMock); // 테스트, 아래는 싷데이터
      getTop3BoardListRequest().then(getTop3BoardListResponse);
    }, []);

    return (
      <div id='main-top-wrapper'>
        <div className="main-top-container">
          <div className="main-top-title">{'GILCNS 게시판에서\n다양한 이야기를 나눠요!!!'}</div>
          <div className="main-top-contents-box">
            <div className="main-top-contents-title">{'주간 TOP 3 게시글'}</div>
            <div className="main-top-contents">
              {top3BoardList?.map(top3ListItem => <Top3Item  top3ListItem={top3ListItem} />)}   
            </div>
          </div>
        </div>
      </div>
    );
  }

  const MainBottom = () => {
    const {
      currentPage, currentSection, viewList, viewPageList, totalSection,
      setCurrentPage, setCurrentSection, setTotalList 
    } = usePagination<BoardListItem>(5);

    const [currentBoardList, setCurrentBoardList] = useState<BoardListItem[]>([]);
    const [popularWordList,  setPopularWordList] = useState<string[]>([]);

    const getLatestBoardListResponse = (responseBody: GetLatestBoardListResponseDto | ResponseDto | null) => {
      if(!responseBody) return;
      const { code } = responseBody;
      if(code === 'DBE') alert('데이터베이스 오류입니다!!');
      if(code !== 'SU') return;
      
      const { latestList } = responseBody as GetLatestBoardListResponseDto;
      setTotalList(latestList);
    }

    const getPopularListResponse = (responseBody: GetPopularListResponseDto | ResponseDto | null) => {
      if(!responseBody) return;
      const { code } = responseBody;
      if(code === 'DBE') alert('데이터베이스 오류입니다!!');
      if(code !== 'SU') return;
      
      const { popularWordList } = responseBody as GetPopularListResponseDto;
      setPopularWordList(popularWordList);
    }

    const onPapularWordClickHandler = (word: string) => {
      navigate(SEARCH_PATH(word));
    }

    useEffect(() => {
      // setCurrentBoardList(latestBoardListMock);
      // setPopularWordList(['게시글','파스타','헬리녹스','맛집','브롬톤','헬리녹스',])
      getLatestBoardListRequest().then(getLatestBoardListResponse);
      getPopularListRequest().then(getPopularListResponse);
    }, []);
  
    return (
      <div id='main-bottom-wrapper'>
        <div className="main-bottom-container">
          <div className='main-bottom-title'>{'최신 게시글'}</div>
          <div className='main-bottom-latest-contents-box'>
            <div className='main-bottom-current-contents'>
              {/* {currentBoardList?.map(boardListItem => <BoardItem boardListItem={boardListItem} />)} 테스트데이터터*/} 
              {viewList?.map(boardListItem => <BoardItem boardListItem={boardListItem} />)}
            </div>
            <div className="main-bottom-popular-box">
              <div className="main-bottom-pouplar-card">
                <div className="main-bottom-popular-card-container">
                  <div className="main-bottom-popular-card-title">{'인기 검색어'}</div>
                  <div className="main-bottom-popular-card-contents">
                    {popularWordList.map(word => <div className="word-badge" onClick={() => onPapularWordClickHandler(word)}>{word}</div>)}
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div className='main-bottom-pagination-box'>
            <Pagination 
                  currentPage = {currentPage}
                  currentSection = {currentSection}
                  setCurrentPage = {setCurrentPage}
                  setCurrentSction = {setCurrentSection}
                  viewPageList = {viewPageList}
                  totalSection = {totalSection} />
          </div> {/* -pagination-box */}
        </div>
      </div>
    );
  }

  return (
    <>
      <MainTop />
      <MainBottom />  
    </>
  )
}
