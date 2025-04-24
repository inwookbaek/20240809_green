import CommonHeader from '@/components/common/header/CommonHeader'
import styles from './styles/index.module.scss'
import { useEffect, useState } from 'react'
import Card from './components/Card'
import { CardDTO } from '../index/types/card'

function Index() {

  const [data, setData] = useState([])
  const getData = () => {
      const getLocalStorage = JSON.parse(localStorage.getItem('bookmark'))
      if (getLocalStorage || getLocalStorage !== null) setData(getLocalStorage)
      else setData([])
  }

  useEffect(() => {
      getData()
  }, [])

  return (
    <div className={styles.page}>
      {/* 공통 헤터 UI 부분 */}
      <CommonHeader />
      {/* 공통 네비게이션 UI 부분 */}
      <main className={styles.page__contents}>
        { data.length === 0 
          ? (<h3 className={styles.page__contents__noData}>북마크에 저장된 데이터가 없습니다!!!</h3>)
          : ( data.map((item: CardDTO) => { return <Card prop={item} key={item.id} /> }))
        }
      </main>
    </div>
  )
}

export default Index
