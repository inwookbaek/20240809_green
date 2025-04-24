import styles from './styles/index.module.scss'
import CommonHeader from '@/components/common/header/CommonHeader'
import CommonNav from '@/components/common/navigation/CommonNav'
import CommonSearchBar from '@/components/common/searchBar/CommonSearchBar'
import Card from './components/Card'
// import axios from 'axios'
import { CardDTO } from './types/card'
import { useRecoilValueLoadable } from 'recoil'

import CommonFooter from '@/components/common/footer/CommonFooter'
import DetailDialog from '@/components/common/dialog/DetailDialog'
import { useMemo, useState } from 'react'
import { imageData } from '@/recoil/selectors/imageSelector'
import Loading from './components/Loading'

// https://unsplash.com/oauth/applications/741360
// Access Key :  L6kOuGkX0fMbu0tzoPNH0K7eluMAT3hRuAbtIGf5KJE
// Secret key :  m5Ogq0sf1xWy62C1J65HCz74tP1GBTWrr6-dTwxVHss

function Index() {

/*
  // Recoil적용전 로직으로 Recoil적용후에 주석처리 함
  const [imgUrls, setImgUrls] = useState<CardDTO[]>([])
  const getData = async () => {
    const API_URL = 'https://api.unsplash.com/search/photos'
    const API_KEY = 'L6kOuGkX0fMbu0tzoPNH0K7eluMAT3hRuAbtIGf5KJE'
    const PER_PAGE = 30

    const searchValue = 'korea'
    const pageValue = 100

      // API 호출
    try {
      const res = await axios.get(`${API_URL}?query=${searchValue}&client_id=${API_KEY}&page=${pageValue}&per_page=${PER_PAGE}`)
      console.log(res)
      // return res.data.results
      if(res.status === 200) {
        setImgUrls(res.data.results);
      }
    } catch (error) {
      console.log(error)
    }    
  }
  const cardList = imgUrls.map((card: CardDTO) => {
    return <Card key={card.id} data={card} /> 
  })

  useEffect(() => {
    getData()
  }, []);
*/

  const [imgData, setImgData] = useState<CardDTO>();
  const [open, setOpen] = useState<boolean>(false); // 이미지 상세 다이얼로그 발생(관리) State

  //  Recoil 적용로직
  // const imageSelector = useRecoilValue<CardDTO[]>(imageData)
  // const CARD_LIST = imageSelector.map((card: CardDTO) => {
  //   return <Card key={card.id} data={card} handleDiglog={setOpen} handleSetData={setImgData} /> 
  // })

  // Recoil 적용로직 & useMemo
  // LoadingLoadable({ contents: Promise {<fulfilled>: Array(30)}, state: "Loading" })
  // ValueLoadable({ contents: [{이미지},...], state: "hasValue" })
  const imageSelector = useRecoilValueLoadable(imageData)
  const CARD_LIST = useMemo(() => {
    console.log(imageSelector);
    if(imageSelector.state === 'hasValue') {
        const result = imageSelector.contents.results.map((card: CardDTO) => {
          return <Card key={card.id} data={card} handleDiglog={setOpen} handleSetData={setImgData} /> 
        })
        return result
    } else {
      // return <div>Loading...</div>
      return <Loading />
    }
  }, [imageSelector])
  
  return (
    <div className={styles.page}>
      {/* 공통 헤더 UI 부분 */}
      <CommonHeader />
      {/* 공통 네비게이션 UI 부분 */}
      <CommonNav />
      <div className={styles.page__contents}>
        <div className={styles.page__contents__introBox}>
          <div className={styles.wrapper}>
              <span className={styles.wrapper__title}>GILCNS Photo Splash</span>
              <span className={styles.wrapper__desc}>
                  인터넷의 시각 자료 출처입니다. <br />
                  모든 지역에 있는 크리에이터들의 지원을 받습니다.
              </span>
              {/* 검색창 UI 부분 */}
              <CommonSearchBar />
          </div>
        </div>
        <div className={styles.page__contents__imageBox}>{CARD_LIST}</div>
      </div>
      {/* 공통 푸터 UI 부분 */}
      <CommonFooter />
      {open && <DetailDialog data={imgData} handleDialog={setOpen} /> }
    </div>
  )
}

export default Index