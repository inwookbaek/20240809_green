// pagination.hook.ts

import { useState, useEffect } from 'react';

/**
 * usePagination 훅에 대한 설명
 *
 * usePagination은 React 애플리케이션에서 페이지네이션을 구현하기 위한 커스텀 훅입니다.
 * 이 훅은 다음과 같은 기능을 제공합니다:
 *
 * 1. 현재 페이지 번호 관리: 사용자가 보고 있는 페이지 번호를 추적합니다.
 * 2. 페이지당 항목 수 관리: 한 페이지에 표시할 항목 수를 설정하고 관리합니다.
 * 3. 전체 항목 수 관리: 페이지네이션을 계산하는 데 필요한 전체 항목 수를 관리합니다.
 * 4. 페이지 수 계산: 전체 항목 수와 페이지당 항목 수를 기반으로 전체 페이지 수를 계산합니다.
 * 5. 페이지 범위 계산: 현재 페이지를 기준으로 표시할 페이지 번호 범위를 계산합니다. (예: [1, 2, 3, 4, 5])
 * 6. 페이지 변경 핸들링: 사용자가 페이지를 변경할 때 호출할 수 있는 함수를 제공합니다.
 * 7. 이전/다음 페이지 이동: 이전 페이지와 다음 페이지로 이동하는 함수를 제공합니다.
 * 8. 첫 페이지/마지막 페이지 이동: 첫 페이지와 마지막 페이지로 이동하는 함수를 제공합니다.
 *
 * 이 훅은 페이지네이션 UI를 구축하는 데 필요한 모든 로직을 캡슐화하여, 컴포넌트에서 페이지네이션 로직을 쉽게 재사용할 수 있도록 합니다.
 *
 * 커스텀 훅을 만드는 방법
 *
 * 1. `use`로 시작하는 함수를 만듭니다.
 * 2. 함수 내부에서 `useState`, `useEffect`와 같은 React 훅을 사용합니다.
 * 3. 필요한 상태와 로직을 구현합니다.
 * 4. 상태와 상태를 변경하는 함수들을 객체로 반환합니다.
 * 5. 필요에 따라 매개변수를 받아서 동적으로 동작하도록 합니다.
 *
 * usePagination 훅의 사용 예시
 *
 * ```typescript
 * const {
 *   currentPage,
 *   totalPages,
 *   pageRange,
 *   itemsPerPage,
 *   totalItems,
 *   setCurrentPage,
 *   setItemsPerPage,
 *   setTotalItems,
 *   goToPreviousPage,
 *   goToNextPage,
 *   goToFirstPage,
 *   goToLastPage,
 * } = usePagination({ initialCurrentPage: 1, initialItemsPerPage: 10, initialTotalItems: 100 });
 *
 * // ... UI에서 currentPage, totalPages, pageRange 등을 사용하여 페이지네이션 UI를 렌더링합니다.
 * // ... setCurrentPage, goToPreviousPage, goToNextPage 등을 사용하여 페이지를 변경합니다.
 * ```
 */

interface UsePaginationProps {
  initialCurrentPage?: number;
  initialItemsPerPage?: number;
  initialTotalItems?: number;
  pageRangeDisplayed?: number;
}

interface UsePaginationResult {
  currentPage: number;
  totalPages: number;
  pageRange: number[];
  itemsPerPage: number;
  totalItems: number;
  setCurrentPage: (page: number) => void;
  setItemsPerPage: (items: number) => void;
  setTotalItems: (total: number) => void;
  goToPreviousPage: () => void;
  goToNextPage: () => void;
  goToFirstPage: () => void;
  goToLastPage: () => void;
}

const usePagination = ({
  initialCurrentPage = 1,
  initialItemsPerPage = 10,
  initialTotalItems = 0,
  pageRangeDisplayed = 5,
}: UsePaginationProps = {}): UsePaginationResult => {
  const [currentPage, setCurrentPage] = useState<number>(initialCurrentPage);
  const [itemsPerPage, setItemsPerPage] = useState<number>(initialItemsPerPage);
  const [totalItems, setTotalItems] = useState<number>(initialTotalItems);
  const [totalPages, setTotalPages] = useState<number>(0);
  const [pageRange, setPageRange] = useState<number[]>([]);

  useEffect(() => {
    const calculatedTotalPages = Math.ceil(totalItems / itemsPerPage);
    setTotalPages(calculatedTotalPages);
  }, [totalItems, itemsPerPage]);

  useEffect(() => {
    const calculatePageRange = () => {
      const range: number[] = [];
      if (totalPages <= pageRangeDisplayed) {
        for (let i = 1; i <= totalPages; i++) {
          range.push(i);
        }
      } else {
        const start = Math.max(
          1,
          currentPage - Math.floor(pageRangeDisplayed / 2)
        );
        const end = Math.min(start + pageRangeDisplayed - 1, totalPages);
        for (let i = start; i <= end; i++) {
          range.push(i);
        }
      }
      return range;
    };

    setPageRange(calculatePageRange());
  }, [currentPage, totalPages, pageRangeDisplayed]);

  const goToPreviousPage = () => {
    setCurrentPage((prev) => Math.max(1, prev - 1));
  };

  const goToNextPage = () => {
    setCurrentPage((prev) => Math.min(totalPages, prev + 1));
  };

  const goToFirstPage = () => {
    setCurrentPage(1);
  };

  const goToLastPage = () => {
    setCurrentPage(totalPages);
  };

  return {
    currentPage,
    totalPages,
    pageRange,
    itemsPerPage,
    totalItems,
    setCurrentPage,
    setItemsPerPage,
    setTotalItems,
    goToPreviousPage,
    goToNextPage,
    goToFirstPage,
    goToLastPage,
  };
};

export default usePagination;
