import React, { useEffect, useState } from 'react'
import './style.css'
import Top3Item from 'components/Top3Item';
import { latestBoardListMock, top3BoardListMock } from 'mocks';
import { BoardListItem } from 'types/interface';
import BoardItem from 'components/BoardItem';
import Pagination from 'components/Pagination';
import { useNavigate } from 'react-router-dom';
import { SEARCH_PATH } from 'constant';

export default function Main() {

  const navigate = useNavigate();
  
  const MainTop = () => {

    const [top3BoardList, setTop3BoardList] = useState<BoardListItem[] | null>([]);

    useEffect(() => {
      setTop3BoardList(top3BoardListMock);
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
    
    const [currentBoardList, setCurrentBoardList] = useState<BoardListItem[]>([]);
    const [popularWordList,  setPopularWordList] = useState<string[]>([]);

    const onPapularWordClickHandler = (word: string) => {
      navigate(SEARCH_PATH(word));
    }

    useEffect(() => {
      setCurrentBoardList(latestBoardListMock);
      setPopularWordList(['맛집','브롬톤','헬리녹스','맛집','브롬톤','헬리녹스',])
    }, []);
  
    return (
      <div id='main-bottom-wrapper'>
        <div className="main-bottom-container">
          <div className='main-bottom-title'>{'최신 게시글'}</div>
          <div className='man-bottom-latest-contents-box'>
            <div className='man-bottom-current-contents'>
              {currentBoardList?.map(boardListItem => <BoardItem boardListItem={boardListItem} />)}
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
            <div>1 2 3 4 5 6 7 8 9 10</div>
            {/* <Pagination/> */}
          </div>
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
