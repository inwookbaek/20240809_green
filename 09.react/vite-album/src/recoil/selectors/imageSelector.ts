import { selector } from 'recoil'
import { searchState } from '../atoms/searchState'
import { pageState } from '../atoms/pageState'
import axios from 'axios'
import { CardDTO } from '@/pages/index/types/card'

// https://unsplash.com/oauth/applications/741360
// Access Key :  L6kOuGkX0fMbu0tzoPNH0K7eluMAT3hRuAbtIGf5KJE
// Secret key :  m5Ogq0sf1xWy62C1J65HCz74tP1GBTWrr6-dTwxVHss

const API_URL = 'https://api.unsplash.com/search/photos'
const API_KEY = 'L6kOuGkX0fMbu0tzoPNH0K7eluMAT3hRuAbtIGf5KJE'
const PER_PAGE = 30

export const imageData = selector({
    key: 'imageData',
    get: async ({ get }) => {
        const searchValue = get(searchState)
        const pageValue = get(pageState)

        // API 호출
        try {
            const res = await axios.get<{ results: CardDTO[] }>(
                `${API_URL}?query=${searchValue}&client_id=${API_KEY}&page=${pageValue}&per_page=${PER_PAGE}`)
            return res.data;
        } catch (error) {
            console.log(error)
            return [] // 에러 시 빈 배열 반환
        }
    },
})