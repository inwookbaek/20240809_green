import React, { useEffect, useState } from 'react'
import './style.css'
import { useNavigate, useParams } from 'react-router-dom'
import { BoardListItem } from 'types/interface';
import BoardItem from 'components/BoardItem';
import { latestBoardListMock } from 'mocks';
import { SEARCH_PATH } from 'constant';
import { getSearchBoardListRequest } from 'apis';
import { ResponseDto } from 'apis/response';
import { GetSearchBoardListResponseDto } from 'apis/response/board';
import { usePagination } from 'hooks';
import Pagination from 'components/Pagination';

export default function Search() {
 
  const { searchWord } = useParams();
  const [preSearchWord, setPreSearchWord ] = useState<string | null>(null);

  const {currentPage, currentSection, viewList, viewPageList, totalSection,
    setCurrentPage, setCurrentSection, setTotalList 
  } = usePagination<BoardListItem>(5);

  const [count, setCount ] = useState<number>(0);
  const [searchBoardList, setSearchBoardList ] = useState<BoardListItem[]>([]);
  const [relationList, setRelationList ] = useState<string[]>([]);
  
  const navigate = useNavigate();

  const getSearchBoardListResponse = (responseBody: GetSearchBoardListResponseDto | ResponseDto | null) => {
      if(!responseBody) return;
      const { code } = responseBody;
      if(code === 'DBE') alert('데이터베이스 오류입니다!!');
      if(code !== 'SU') return;
      
      if(!searchWord) return;
      const { searchList } = responseBody as GetSearchBoardListResponseDto;
      setTotalList(searchList);
      setCount(searchList.length);
      setPreSearchWord(searchWord);
  }

  const onPopularWordClickHandler = (word: string) => {
    navigate(SEARCH_PATH(word));
  }

  useEffect(() => {
    // setSearchBoardList(latestBoardListMock);
    if(!searchWord) return;
    getSearchBoardListRequest(searchWord, preSearchWord).then(getSearchBoardListResponse);
  }, [searchWord]);


  return (
    <div id="search-wrapper">
      <div className="search-container">
        <div className="search-title-box">
          <div className="search-title-box">
            <div className="search-title"><span className="emphasis">{searchWord}</span>{'에 대한 검색결과 입니다.'}</div>
            <div className="search-count">[ 검색건수 : {count} ]</div>
          </div>
        </div>
        <div className="search-contents-box">
          {count === 0 
            ? <div className="search-contents-nothing">{'검색결과가 없습니다!!'}</div>
            // : <div className="searh-contents">{searchBoardList.map(boardListItem => <BoardItem boardListItem={boardListItem} />)}</div> 테스트데이터터
            : <div className="searh-contents">{viewList.map(boardListItem => <BoardItem boardListItem={boardListItem} />)}</div>
          }
          <div className="search-relation-box">
            <div className="search-relation-card">
              <div className="search-relation-card-container">
                <div className="search-relation-card-title">{'관련검색어'}</div>
                {relationList.length === 0
                  ? <div className="search-relation-card-contents-noting">{'관련검색어가 없습니다!!'}</div>
                  : <div className="search-relation-card-contents">
                      {relationList.map(word => <div className='word-badge' onClick={() => onPopularWordClickHandler(word)}>{word}</div>)}
                    </div>
                }
              </div> {/* search-relation-card-container*/}
            </div> {/* search-relation-card */}
          </div> {/* search-relation-box */} 
        </div>  {/* search-cintents-box */}
        <div className="search-paginatrion-box">
          {count !== 0 && 
            <Pagination 
              currentPage = {currentPage}
              currentSection = {currentSection}
              setCurrentPage = {setCurrentPage}
              setCurrentSction = {setCurrentSection}
              viewPageList = {viewPageList}
              totalSection = {totalSection} /> }
        </div>
      </div>  {/* search-container */}
    </div>
  )
}
