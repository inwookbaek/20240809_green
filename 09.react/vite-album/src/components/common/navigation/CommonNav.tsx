import { useEffect, useState } from 'react'
import { Link, useLocation } from 'react-router-dom'
import styles from './CommonNav.module.scss'
import navJson from './nav.json'
import { useImageStore } from '@/store/store'

interface Navigation {
  index: number
  path: string
  label: string
  searchValue: string
  isActive: boolean
}

function CommonNav() {
  const location = useLocation()
  const [navigation, setNavigation] = useState<Navigation[]>(navJson)
  
  // Zustand 스토어에서 필요한 액션들 가져오기
  const { setSearchQuery, setPage, setActiveNav } = useImageStore()

  useEffect(() => {
    const updatedNav = navigation.map((nav) => {
      const isActive = nav.path === location.pathname || 
                      location.pathname.includes(nav.path)
      
      if (isActive) {
        setSearchQuery(nav.searchValue)
        setPage(1)
        setActiveNav(nav.path) // 오타 수정: setAriveNav → setActiveNav
      }
      
      return { ...nav, isActive }
    })
    
    setNavigation(updatedNav)
  }, [location.pathname])

  return (
    <nav className={styles.navigation}>
      {navigation.map((item) => (
        <Link 
          to={item.path} 
          className={`${styles.navigation__menu} ${
            item.isActive ? styles.active : styles.inactive
          }`} 
          key={item.path}
        >
          <span className={styles.navigation__menu__label}>{item.label}</span>
        </Link>     
      ))}
    </nav>
  )
}

export default CommonNav