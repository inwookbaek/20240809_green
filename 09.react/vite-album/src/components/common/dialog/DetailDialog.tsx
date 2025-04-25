import { CardDTO, Tag } from '@/pages/index/types/card'
import styles from './DetailDialog.module.scss'
import { useState, useEffect } from 'react'

import toast, { toastConfig } from 'react-simple-toasts'
import 'react-simple-toasts/dist/style.css';
import 'react-simple-toasts/dist/theme/dark.css';
toastConfig({ 
  theme: 'dark',
  position: 'top-center',
  duration: 1000,
})

interface Props {
  data: CardDTO
  handleDialog: (eventValue: boolean) => void
}

function DetailDialog({ data, handleDialog }: Props) {
  const [bookmark, setBookmark] = useState(false)

  const closeDialog = (event: React.MouseEvent<HTMLElement, MouseEvent>) => {
    event.stopPropagation()
    handleDialog(false)
  }

  const toggleBookmark = (selected: CardDTO) => {
    const storedBookmarks = JSON.parse(localStorage.getItem('bookmark') || '[]')
    
    if (bookmark) {
      const updated = storedBookmarks.filter((item: CardDTO) => item.id !== selected.id)
      localStorage.setItem('bookmark', JSON.stringify(updated))
      toast('북마크에서 제거되었습니다. ❌')
    } else {
      const updated = [...storedBookmarks, selected]
      localStorage.setItem('bookmark', JSON.stringify(updated))
      toast('북마크에 저장되었습니다. 😄')
    }
    
    setBookmark(!bookmark)
  }

    // useEffect(() => {
  //   const storedBookmarks = JSON.parse(localStorage.getItem('bookmark') || '[]')
  //   setBookmark(storedBookmarks.some((item: CardDTO) => item.id === data.id))
  // }, [data.id])
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
              <button className={styles.bookmark__button} onClick={() => toggleBookmark(data)}>
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