import { BrowserRouter, Routes, Route } from 'react-router-dom';
import MainPage from '@/pages/index/index'
import BookmarkPage from '@pages/bookmark/index'
import AboutPage from '@pages/about'
import { RecoilRoot } from 'recoil';

function App() {
  return (
    <RecoilRoot>
      <BrowserRouter>
        <Routes>
          {/* index 는 React Router v6에서 도입된 속성으로, 부모 경로와 정확히 일치할 때 렌더링되는 기본 라우트를 의미 
              * :id (필수 파라미터)
                /about/123 → 매칭됨 (useParams().id는 "123")
                /about → 매칭되지 않음
              * :id? (선택적 파라미터)
                /about/123 → 매칭됨 (useParams().id는 "123")
                /about → 매칭됨 (useParams().id는 undefined)
          */}
          <Route index path="/" element={<MainPage />} /> 
          <Route index path="/search/:id" element={<MainPage />} /> 
          <Route path="/bookmark" element={<BookmarkPage />}></Route>
          <Route path="/" element={<AboutPage />} /> 
        </Routes>
      </BrowserRouter>
    </RecoilRoot>
  )
}

export default App