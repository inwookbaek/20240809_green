import { useEffect, useState } from 'react';
import styles from './CommonFooter.module.scss';
import { useImageStore } from '@/store/store';

function CommonFooter() {
  const {
    page,
    searchQuery,
    currentStep,
    setPage,
    setCurrentStep,
    fetchImages
  } = useImageStore();

  const [totalPages, setTotalPages] = useState(0);
  const [pageGroups, setPageGroups] = useState<number[][]>([]);

  // 이미지 데이터와 총 페이지 수 가져오기
  useEffect(() => {
    const loadData = async () => {
      const data = await fetchImages();
      setTotalPages(data.total_pages);
    };
    loadData();
  }, [searchQuery, page, fetchImages]);

  // 페이지 그룹 생성
  useEffect(() => {
    if (totalPages > 0) {
      const allPages = Array.from({ length: totalPages }, (_, i) => i + 1);
      const groups = [];
      const groupSize = 10;
      
      for (let i = 0; i < allPages.length; i += groupSize) {
        groups.push(allPages.slice(i, i + groupSize));
      }
      
      setPageGroups(groups);
      // 검색어 변경 시 스텝 초기화
      if (currentStep >= groups.length) {
        setCurrentStep(0);
      }
    }
  }, [totalPages, currentStep, setCurrentStep]);

  const moveToPage = (selected: number) => {
    setPage(selected);
  };

  const moveToPrev = () => {
    if (currentStep > 0) {
      const newStep = currentStep - 1;
      setCurrentStep(newStep);
      setPage(pageGroups[newStep][0]);
    }
  };

  const moveToNext = () => {
    if (currentStep < pageGroups.length - 1) {
      const newStep = currentStep + 1;
      setCurrentStep(newStep);
      setPage(pageGroups[newStep][0]);
    }
  };

  return (
    <footer className={styles.footer}>
      <div className={styles.pagination}>
        <button 
          className={styles.pagination__button} 
          onClick={moveToPrev}
          disabled={currentStep === 0}
        >
          <img src="src/assets/icons/icon-arrowLeft.svg" alt="이전" />
        </button>
        
        {pageGroups[currentStep]?.map((item) => (
          <button
            key={item}
            className={
              item === page
                ? `${styles.pagination__button} ${styles.active}`
                : `${styles.pagination__button} ${styles.inactive}`
            }
            onClick={() => moveToPage(item)}
          >
            {item}
          </button>
        ))}
        
        <button
          className={styles.pagination__button}
          onClick={moveToNext}
          disabled={currentStep >= pageGroups.length - 1}
        >
          <img src="src/assets/icons/icon-arrowRight.svg" alt="다음" />
        </button>
      </div>
    </footer>
  );
}

export default CommonFooter;