import { useState, useMemo } from 'react';

// src/hooks/usePagination.ts
interface PaginationParams<T> {
  totalItems: number;
  itemsPerPage: number;
  initialPage?: number;
  data?: T[]; // 선택적: 실제 데이터가 있는 경우
}
interface PaginationResult<T> {
  currentPage: number;
  totalPages: number;
  itemsPerPage: number;
  totalItems: number;
  nextPage: () => void;
  prevPage: () => void;
  goToPage: (page: number) => void;
  startIndex: number;
  endIndex: number;
  currentData?: T[]; // 선택적: 실제 데이터가 있는 경우
}

function usePagination<T>({
  totalItems,
  itemsPerPage,
  initialPage = 1,
  data = [],
}: PaginationParams<T>): PaginationResult<T> {
  const [currentPage, setCurrentPage] = useState(initialPage);

  // 총 페이지 수 계산
  const totalPages = useMemo(() => {
    return Math.ceil(totalItems / itemsPerPage);
  }, [totalItems, itemsPerPage]);

  // 현재 페이지의 시작/끝 인덱스 계산
  const { startIndex, endIndex } = useMemo(() => {
    const start = (currentPage - 1) * itemsPerPage;
    const end = Math.min(start + itemsPerPage - 1, totalItems - 1);
    return { startIndex: start, endIndex: end };
  }, [currentPage, itemsPerPage, totalItems]);

  // 현재 페이지의 데이터 슬라이싱 (data가 제공된 경우)
  const currentData = useMemo(() => {
    if (data.length > 0) {
      return data.slice(startIndex, endIndex + 1);
    }
    return undefined;
  }, [data, startIndex, endIndex]);

  // 다음 페이지로 이동
  const nextPage = () => {
    setCurrentPage((prev) => Math.min(prev + 1, totalPages));
  };

  // 이전 페이지로 이동
  const prevPage = () => {
    setCurrentPage((prev) => Math.max(prev - 1, 1));
  };

  // 특정 페이지로 이동
  const goToPage = (page: number) => {
    const pageNumber = Math.max(1, Math.min(page, totalPages));
    setCurrentPage(pageNumber);
  };

  return {
    currentPage,
    totalPages,
    itemsPerPage,
    totalItems,
    nextPage,
    prevPage,
    goToPage,
    startIndex,
    endIndex,
    currentData,
  };
}

export default usePagination;