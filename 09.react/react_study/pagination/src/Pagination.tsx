import React, { useState, useEffect } from 'react';
import usePagination from './hooks/pagination.hook';

interface Item {
  id: number;
  name: string;
}

const MyComponent: React.FC = () => {
  const [items, setItems] = useState<Item[]>([]);
  const [loading, setLoading] = useState<boolean>(true);

  // 가상의 데이터 가져오기 함수
  const fetchData = async () => {
    setLoading(true);
    // 실제로는 API 호출을 통해 데이터를 가져옵니다.
    await new Promise((resolve) => setTimeout(resolve, 1000));
    const dummyData: Item[] = Array.from({ length: 100 }, (_, i) => ({
      id: i + 1,
      name: `Item ${i + 1}`,
    }));
    setItems(dummyData);
    setLoading(false);
  };

  useEffect(() => {
    fetchData();
  }, []);

  const {
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
  } = usePagination({ initialItemsPerPage: 10, initialTotalItems: items.length });

  useEffect(() => {
    setTotalItems(items.length);
  }, [items]);

  const startIndex = (currentPage - 1) * itemsPerPage;
  const endIndex = startIndex + itemsPerPage;
  const currentItems = items.slice(startIndex, endIndex);

  if(loading){
    return <div>Loading...</div>
  }

  return (
    <div>
      <h1>My Component</h1>
      <ul>
        {currentItems.map((item) => (
          <li key={item.id}>{item.name}</li>
        ))}
      </ul>

      <div>
        <button onClick={goToFirstPage} disabled={currentPage === 1}>
          First
        </button>
        <button onClick={goToPreviousPage} disabled={currentPage === 1}>
          Previous
        </button>
        {pageRange.map((page) => (
          <button
            key={page}
            onClick={() => setCurrentPage(page)}
            disabled={currentPage === page}
          >
            {page}
          </button>
        ))}
        <button onClick={goToNextPage} disabled={currentPage === totalPages}>
          Next
        </button>
        <button onClick={goToLastPage} disabled={currentPage === totalPages}>
          Last
        </button>
      </div>
      <div>
        <select value={itemsPerPage} onChange={(e) => setItemsPerPage(Number(e.target.value))}>
          <option value={5}>5</option>
          <option value={10}>10</option>
          <option value={20}>20</option>
        </select>
      </div>
    </div>
  );
};

export default MyComponent;
