import { useEffect, useState } from 'react'

const usePagination = <T>(countPerPage: number) => {
  const [totalList, setTotalList ] = useState<T[]>([]);                 // 전체내용(T=게시글, 좋아요, 댓글)
  const [viewList, setViewList] = useState<T[]>([]);                    // 보여줄내용
  const [currentPage, setCurrentPage ] = useState<number>(1);           // 현재 페이지
  const [totalPageList, setTotalPageList] = useState<number[]>([1]);    // 전체 페이지
  const [viewPageList, setViewPageList] = useState<number[]>([1]);      // 보여줄 페이지
  const [currentSection, setCurrentSection] = useState<number>(1);       // 현재섹션상태
  const [totalSection, setTotalSection] = useState<number>(1);          // 전체섹션상태

  // 보여줄 객체리스트 추출
  const setView = () => {
    const FIRST_INDEX = countPerPage * (currentPage - 1);
    const LAST_INDEX = totalList.length > countPerPage * currentPage ? countPerPage * currentPage : totalList.length;
    const viewList = totalList.slice(FIRST_INDEX, LAST_INDEX);
    setViewList(viewList);
  }

  const setViewPage = () => {
    const FIRST_INDEX = 10 * (currentSection - 1);
    const LAST_INDEX = totalPageList.length > 10  * currentSection ? 10 * currentSection : totalPageList.length;
    const viewPageList = totalPageList.slice(FIRST_INDEX, LAST_INDEX);
    setViewPageList(viewPageList);
  }

  useEffect(() => {
    const totalPage = Math.ceil(totalList.length / countPerPage);
    const totalPageList = Array.from({ length: totalPage }, (_, i) => i + 1);
    setTotalPageList(totalPageList);
    
    const totalSection = Math.ceil(totalList.length / (countPerPage * 10));
    setTotalSection(totalSection);

    setCurrentPage(1);
    setCurrentSection(1);  

    setView();
    setViewPage();

  }, [totalList]);

  // 현재페이지변경시마다 실행앟 작업, setViewPage처럼 한줄로 표시가능
  useEffect(() => {
    setView();
  }, [currentPage]);

  // 현재페이지섹션변경시마다 실행할 작업
  useEffect(setViewPage, [currentSection]);
  



  return {
      currentPage
    , setCurrentPage
    , currentSection
    , setCurrentSection
    , viewList
    , viewPageList
    , totalSection
    , setTotalList 
  };

};

export default usePagination;

