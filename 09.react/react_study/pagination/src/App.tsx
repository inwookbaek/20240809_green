import React from 'react';
import usePagination from './hooks/usePagination.hook';

interface Product {
  id: number;
  name: string;
  price: number;
}

const ProductList: React.FC = () => {
  // 예시 데이터
  const products: Product[] = [
    { id: 1, name: 'Product 1', price: 100 },
    { id: 2, name: 'Product 2', price: 200 },
    // ... 더 많은 데이터 (총 50개 가정)
  ];

  // 페이지네이션 훅 사용
  const {
    currentPage,
    totalPages,
    currentData,
    nextPage,
    prevPage,
    goToPage,
  } = usePagination({
    totalItems: products.length,
    itemsPerPage: 10,
    initialPage: 1,
    data: products,
  });

  return (
    <div className="product-list">
      <h2>Product List (Page {currentPage} of {totalPages})</h2>
      
      {/* 상품 목록 렌더링 */}
      <div className="products">
        {currentData?.map((product) => (
          <div key={product.id} className="product">
            <h3>{product.name}</h3>
            <p>${product.price}</p>
          </div>
        ))}
      </div>
      
      {/* 페이지네이션 컨트롤 */}
      <div className="pagination-controls">
        <button 
          onClick={prevPage} 
          disabled={currentPage === 1}
        >
          Previous
        </button>
        
        {/* 페이지 번호 표시 (1...5) */}
        {Array.from({ length: totalPages }, (_, i) => i + 1).map((page) => (
          <button
            key={page}
            onClick={() => goToPage(page)}
            className={currentPage === page ? 'active' : ''}
          >
            {page}
          </button>
        ))}
        
        <button 
          onClick={nextPage} 
          disabled={currentPage === totalPages}
        >
          Next
        </button>
      </div>
    </div>
  );
};

export default ProductList;