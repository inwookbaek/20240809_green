import React, { Dispatch, SetStateAction } from 'react';
import './style.css';

// pagination properties
// Dispatch는 React에서 상태 업데이트 함수의 타입을 나타내는 제네릭 타입입니다. 
// SetStateAction과 함께 사용되어 상태 업데이트 함수의 타입을 정의합니다.
// Dispatch와 SetStateAction의 활용 이유
// 1. 타입 안정성: 컴포넌트에 전달되는 setter 함수가 특정 타입(number)만 처리하도록 보장
// 2. 일관성: React의 useState 훅과 동일한 타입 시스템 사용
// 3. 유연성: 직접 값 또는 함수형 업데이트 모두 처리 가능
// 4. 재사용성: 상태 로직을 자식 컴포넌트로 위임할 수 있음
interface Props {
  currentPage: number;
  currentSection: number;
  setCurrentPage: Dispatch<SetStateAction<number>>;
  setCurrentSction: Dispatch<SetStateAction<number>>;

  viewPageList: number[];
  totalSection: number;

}

export default function Pagination(props: Props) {

  // Properties 상태
  const { currentPage, currentSection, viewPageList, totalSection } = props;
  const { setCurrentPage, setCurrentSction } = props;


  /* event handler -----------------------------------------------------------*/
  const onPageClickHandler = (page: number) => {
    setCurrentPage(page);
  }

  const onPreviousClickHandler = () => {
    if(currentSection === 1) return;
    setCurrentPage((currentSection - 1) * 10);
    setCurrentSction(currentSection - 1);
  }

  const onNextClickHandler = () => {
    if(currentSection === totalSection) return;
    setCurrentPage(currentSection * 10 + 1);
    setCurrentSction(currentSection + 1);
  }


  return (
    <>

    <div id='pagination-wrapper'>
      <div className="pagination-change-link-box">
        <div className='icon-box'>
          <div className='icon expand-left-icon'></div>
        </div>
        <div className='pagination-change-link-text' onClick={ onPreviousClickHandler }>{'이전'}</div>
      </div>

      <div className='pagination-divider'>{'\|'}</div>

      {viewPageList.map(page => 
        page === currentPage 
          ? <div key={page} className='pagination-text-active'>{page}</div>
          : <div key={page} className='pagination-text' onClick={ () => onPageClickHandler(page) }>{page}</div>
      )}

      <div className='pagination-divider'>{'\|'}</div>

      <div className="pagination-change-link-box">
        <div className='pagination-change-link-text' onClick={ onNextClickHandler }>{'다음'}</div>
        <div className='icon-box'>
          <div className='icon expand-right-icon'></div>
        </div>
      </div>
    </div>
    </>
  )
}