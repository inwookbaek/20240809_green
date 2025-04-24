import { create } from 'zustand'
import axios from 'axios'
import { CardDTO } from '@/pages/index/types/card'

interface ImageState {
  // 기존 상태들
  page: number;
  searchQuery: string;
  activeNav: string;
  currentStep: number;
  isLoading: boolean;
  error: string | null;
  images: CardDTO[]; // 이미지 데이터 추가
  totalPages: number;
  
  // 액션들
  setPage: (page: number) => void;
  setSearchQuery: (query: string) => void;
  setActiveNav: (path: string) => void;
  setCurrentStep: (step: number) => void;
  fetchImages: () => Promise<{ results: CardDTO[]; total_pages: number }>; // 반환 타입 수정
  setLoading: (isLoading: boolean) => void;
  setError: (error: string | null) => void;
  setImages: (images: CardDTO[]) => void,
  setTotalPages: (total: number) => void,
}

const API_URL = 'https://api.unsplash.com/search/photos'
const API_KEY = 'L6kOuGkX0fMbu0tzoPNH0K7eluMAT3hRuAbtIGf5KJE'
const PER_PAGE = 30

export const useImageStore = create<ImageState>((set, get) => ({
  // 기존 상태
  page: 1,
  searchQuery: 'Korea',
  activeNav: '/',
  currentStep: 0,
  isLoading: false,
  error: null,
  images: [], // 초기값
  totalPages: 0,

  // 액션들 

  // 상태 업데이트 액션들
  setPage: (page) => set({ page }),
  setSearchQuery: (searchQuery) => set({ searchQuery, page: 1, currentStep: 0 }),
  setActiveNav: (activeNav) => set({ activeNav }),
  setCurrentStep: (currentStep) => set({ currentStep }),
  setLoading: (isLoading) => set({ isLoading }),
  setError: (error) => set({ error }),
  setImages: (images) => set({ images }),
  setTotalPages: (totalPages) => set({ totalPages }),

  // 이미지 데이터 가져오기
  // API 호출 (total_pages 포함하도록 수정)
  fetchImages: async () => {
    set({ isLoading: true });
    const { searchQuery, page } = get();
    try {
      const res = await axios.get<{ results: CardDTO[]; total_pages: number }>(
        `${API_URL}?query=${searchQuery}&client_id=${API_KEY}&page=${page}&per_page=${PER_PAGE}`
      );
      set({ images: res.data.results, totalPages: res.data.total_pages }); // 상태 업데이트
      // console.log(res.data)
      return res.data; // 필요시 데이터 반환
    } catch (error) {
      console.error(error);
      set({ images: [], totalPages: 0 });
      return { results: [], total_pages: 0 };
    } finally {
      set({ isLoading: false });
    }
  }
}))