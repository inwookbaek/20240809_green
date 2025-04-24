import { useState } from 'react'
import { useImageStore } from '@/store/store'
import styles from './CommonSearchBar.module.scss'

function CommonSearchBar() {
  
  const [query, setQuery] = useState('')
  const { setSearchQuery } = useImageStore()
  const { setPage } = useImageStore()


  const onChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setQuery(event.target.value)
  }

  const handleKeyDown = (event: React.KeyboardEvent) => {
    if (event.key === 'Enter') {
      if (query === '') {
        // input 태그 안에 빈 값으로 검색하였을 때 => searching default value
        setSearchQuery('Korea')
        setPage(1)
      } else {
        setSearchQuery(query) // 작성한 Input Value 값 할당
        setPage(1)
      }
    }
  }

  const onSearch = () => {
    if (query === '') {
      // input 태그 안에 빈 값으로 검색하였을 때 => searching default value
      setSearchQuery('Korea')
      setPage(1)
    } else {
      setSearchQuery(query) // 작성한 Input Value 값 할당
      setPage(1)
    }
  }

  return (
    <div className={styles.searchBar}>
      <div className={styles.searchBar__search}>
          <input type="text" placeholder="찾으실 이미지를 검색하세요." className={styles.searchBar__search__input} value={query} onChange={onChange} onKeyDown={handleKeyDown} />
          <img src="./src/assets/icons/icon-search.svg" alt="" onClick={onSearch} />
      </div>
    </div>
    )  
}

export default CommonSearchBar