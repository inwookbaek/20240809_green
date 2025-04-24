import styles from './styles/index.module.scss'
import CommonHeader from '@/components/common/header/CommonHeader'
import CommonNav from '@/components/common/navigation/CommonNav'
import CommonSearchBar from '@/components/common/searchBar/CommonSearchBar'
import Card from './components/Card'
import { CardDTO } from './types/card'
import CommonFooter from '@/components/common/footer/CommonFooter'
import DetailDialog from '@/components/common/dialog/DetailDialog'
import { useEffect, useMemo, useState } from 'react'
import Loading from './components/Loading'
import { useImageStore } from '@/store/store'

function Index() {
  const [imgData, setImgData] = useState<CardDTO>()
  const [open, setOpen] = useState<boolean>(false)
  
  // Zustand 스토어에서 상태와 액션 가져오기
  const images = useImageStore((state) => state.images);
  const loading = useImageStore((state) => state.isLoading);
  const fetchImages = useImageStore((state) => state.fetchImages);
  const setSearchQuery = useImageStore((state) => state.setSearchQuery);
  const setPage = useImageStore((state) => state.setPage);

  // const { images, isLoading, fetchImages, setSearchQuery, setPage } = useImageStore(
  //   (state) => ({
  //     images: state.images,
  //     isLoading: state.isLoading,
  //     fetchImages: state.fetchImages,
  //     setSearchQuery: state.setSearchQuery,
  //     setPage: state.setPage  
  //   }),
  //   shallow
  // )

  useEffect(() => {
    const loadImages = async () => {
      await fetchImages()
    }
    loadImages()
    setSearchQuery('Korea')
    setPage(1)
  }, [fetchImages, setSearchQuery, setPage])

  const CARD_LIST = useMemo(() => {
    if (loading) {
      return <Loading />
    }
    return images.map((card: CardDTO) => (
      <Card 
        key={card.id} 
        data={card} 
        handleDialog={setOpen} 
        handleSetData={setImgData} 
      />
    ))
  }, [images, loading])

  return (
    <div className={styles.page}>
      <CommonHeader />
      <CommonNav />
      <div className={styles.page__contents}>
        <div className={styles.page__contents__introBox}>
          <div className={styles.wrapper}>
              <span className={styles.wrapper__title}>GILCNS Photo Splash</span>
              <span className={styles.wrapper__desc}>
                  인터넷의 시각 자료 출처입니다. <br />
                  모든 지역에 있는 크리에이터들의 지원을 받습니다.
              </span>
              <CommonSearchBar />
          </div>
        </div>
        <div className={styles.page__contents__imageBox}>{CARD_LIST}</div>
      </div>
      <CommonFooter />
      {open && <DetailDialog data={imgData} handleDialog={setOpen} />}
    </div>
  )
}

export default Index