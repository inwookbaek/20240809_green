
# React Basic 강의 콘텐츠: Unsplash Image API를 활용한 이미지 검색 사이트 만들기
#### git vclone https://github.com/daffy3/YT-React.git

## 개발환경

1. 프로젝트 환경설정(vite를 활용한 React 설치): `npm create vite@latest`
2. React 중앙집중식 상태관리 라이브러리 Recoil 설치: `npm install recoil`
3. 외부 오픈 API 통신을 위한 라이브러리 Axios 설치: `npm install axios`
4. CSS 스타일링을 위한 SASS/SCSS 설치: `npm install -D sass`
5. React Router 설치: `npm install react-router-dom localforage match-sorter sort-by`
6. TypeScript에서 Node.js 모듈을 쓸 수 있는 환경 구축 : `npm i @types/node`
7. React Toast Popup 모듈 설치 : `npm install react-simple-toasts`
8. recoil은 react19버전과 호환이 않되어 다운그레이드 (나중에 zustand등으로 마이그레이션할 것 )
   `npm install react@18.3.1 react-dom@18.3.1 recoil@0.7.7` 

9. 상태관리 : recoil대신에 zustand로 대체체
   `npm install react@latest react-dom@latest`
   `npm install zustand`