import { CardDTO, Tag } from '@/pages/index/types/card'
import styles from './DetailDialog.module.scss'
import 'react-simple-toasts/dist/theme/dark.css'
import { useEffect, useState } from 'react'

import toast, { toastConfig } from 'react-simple-toasts'
import 'react-simple-toasts/dist/theme/dark.css'

toastConfig({ 
  theme: 'dark',
  position: 'top-right',  // 중앙 정렬
  duration: 100,
})

interface Props {
  data: CardDTO
  handleDialog: (eventValue: boolean) => void
}

function DetailDialog({ data, handleDialog }: Props) {

  const [bookmark, setBookmark] = useState(false)

  const closeDialog = (event: React.MouseEvent<HTMLElement, MouseEvent>) => {
    handleDialog(false)
    event.stopPropagation()
  }

// 북마크 추가/삭제 이벤트 (토글 방식)
const addBookmark = (selected: CardDTO) => {
  const getLocalStorage = JSON.parse(localStorage.getItem('bookmark')) || [];

  // 1. 이미 북마크에 있는 경우 → 제거
  if (bookmark) {
    const updatedBookmarks = getLocalStorage.filter((item: CardDTO) => item.id !== selected.id);
    localStorage.setItem('bookmark', JSON.stringify(updatedBookmarks));
    setBookmark(false);
    toast('북마크에서 제거되었습니다. ❌');
  } 
  // 2. 북마크에 없는 경우 → 추가
  else {
    // 2-1. 북마크가 로컬스토리지에 아예 없는 경우
    if (!getLocalStorage || getLocalStorage.length === 0) {
      localStorage.setItem('bookmark', JSON.stringify([selected]));
    } 
    // 2-2. 이미 다른 북마크가 있는 경우
    else {
      const isAlreadyBookmarked = getLocalStorage.some((item: CardDTO) => item.id === selected.id);
      if (isAlreadyBookmarked) {
        toast('이미 북마크에 추가된 이미지입니다. ❌');
        return;
      }
      const updatedBookmarks = [...getLocalStorage, selected];
      localStorage.setItem('bookmark', JSON.stringify(updatedBookmarks));
    }
    setBookmark(true);
    toast('북마크에 저장되었습니다. 😄');
  }
};


useEffect(() => {
  // ✅ 초기 북마크 상태 체크
  const storedBookmarks = JSON.parse(localStorage.getItem('bookmark')) || [];
  const isBookmarked = storedBookmarks.some((item: CardDTO) => item.id === data.id);
  setBookmark(isBookmarked);

  // ✅ ESC 키로 다이얼로그 닫기
  const handleKeyDown = (event: KeyboardEvent) => {
    if (event.key === 'Escape') {
      handleDialog(false);
    }
  };

  window.addEventListener('keydown', handleKeyDown);
  return () => window.removeEventListener('keydown', handleKeyDown);
}, [data.id, handleDialog]);

  // useEffect(() => {
  //   const getLocalStorage = JSON.parse(localStorage.getItem('bookmark'))

  //   if (getLocalStorage && getLocalStorage.findIndex((item: CardDTO) => item.id === data.id) > -1) {
  //       setBookmark(true)
  //   } else if (!getLocalStorage) return

  //   const escKeyDownCloseDialog = (event: KeyboardEvent) => {
  //     if (event.key === 'Escape') {
  //       handleDialog(false);
  //     }
  //   };

  //   // ESC Key를 눌렀을 때, 다이얼로그창 닫기
  //   window.addEventListener('keydown', escKeyDownCloseDialog) // 위에 만들어 놓은 escKeyDownCloseDialog를 keydown했을 때, 이벤트로 등록한다.
  //   return () => window.removeEventListener('keydown', escKeyDownCloseDialog)
  // }, [])

  return (
    <div className={styles.container}>
      <div className={styles.container__dialog}>
        <div className={styles.container__dialog__header}>
            <div className={styles.close}>
                <button className={styles.close__button} onClick={closeDialog}>
                    {/* 구글 아이콘을 사용 */}
                    <span className="material-symbols-outlined" style={{ fontSize: 16 + 'px' }}>
                        close
                    </span>
                </button>
                <img src={data.user.profile_image.small} alt="사진작가 프로필 사진" className={styles.close__authorImage} />
                <span className={styles.close__authorName}>{data.user.name}</span>
            </div>
            <div className={styles.bookmark}>           
              <button className={styles.bookmark__button} onClick={() => addBookmark(data)}>
                {/* 구글 아이콘을 사용 */}
                {/* {bookmark === false
                  ? <span className="material-symbols-outlined" style={{ fontSize: 16 + 'px' }}>favorite</span>
                  : <span className="material-symbols-outlined fill" style={{ fontSize: 16 + 'px', color: 'red' }}>favorite</span>
                } */}
                {/* fill 옵션이 작동 않됨 */}
                <span className="material-symbols-outlined" 
                      style={{ 
                        fontSize: '16px', 
                        color: bookmark ? 'red' : 'inherit',
                        fontVariationSettings: bookmark ? "'FILL' 1" : "'FILL' 0"
                      }}>favorite
                </span>
                북마크
              </button>
              <button className={styles.bookmark__button}>다운로드</button>
          </div>
        </div>
        <div className={styles.container__dialog__body}>
            <img src={data.urls.small} alt="상세이미지" className={styles.image} />
        </div>
        <div className={styles.container__dialog__footer}>
            <div className={styles.infoBox}>
                <div className={styles.infoBox__item}>
                    <span className={styles.infoBox__item__label}>이미지 크기</span>
                    <span className={styles.infoBox__item__value}>{data.height} X {data.width}</span>
                </div>
                <div className={styles.infoBox__item}>
                    <span className={styles.infoBox__item__label}>업로드</span>
                    <span className={styles.infoBox__item__value}>{data.created_at.split('T')[0]}</span>
                </div>
                <div className={styles.infoBox__item}>
                    <span className={styles.infoBox__item__label}>마지막 업데이트</span>
                    <span className={styles.infoBox__item__value}>{data.updated_at.split('T')[0]}</span>
                </div>
                <div className={styles.infoBox__item}>
                    <span className={styles.infoBox__item__label}>다운로드횟수</span>
                    <span className={styles.infoBox__item__value}>{data.likes}</span>
                </div>
            </div>
            <div className={styles.tagBox}>
              {/* tag 데이터가 없음 */}
              {data.tags &&
                  data.tags.map((tag: Tag) => {
                  return (
                    <div className={styles.tagBox__tag} key={tag.title}>
                        {tag.title}
                    </div>
                  )
                })
              }
              {!data.tags &&
              <>
                <div className={styles.tagBox__tag}>{data.description}</div>
                <div className={styles.tagBox__tag}> {data.alt_description}</div>
              </>
              }
            </div>
        </div>
      </div>
    </div>
  )
}

export default DetailDialog
