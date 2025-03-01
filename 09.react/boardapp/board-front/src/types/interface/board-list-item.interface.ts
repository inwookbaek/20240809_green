export default interface BoardListItem {
  boardNumber: number;          // 게시글 번호 (유일 식별자)
  title: string;                // 게시글 제목
  content: string;              // 게시글 내용
  boardTitleImage: string | null;  // 게시글 대표 이미지 URL (이미지가 없을 경우 null)
  favoriteCount: number;        // 좋아요 수
  commentCount: number;         // 댓글 수
  viewCount: number;            // 조회수
  writeDatetime: string;        // 게시글 작성일 (날짜 및 시간 문자열)
  writeNickname: string;        // 작성자 닉네임
  writeProfileImage: string | null; // 작성자 프로필 이미지 URL (이미지가 없을 경우 null)
}

/*
📌 사용 예시
이 인터페이스를 활용하여 게시글 목록을 저장할 수 있습니다.
✅ 이 인터페이스를 사용하면 BoardListItem 타입을 기반으로 TypeScript가 자동으로 데이터 형식을 검증하여, 오류를 방지하고 코드의 안정성을 높일 수 있습니다! 🚀

const exampleBoardItem: BoardListItem = {
  boardNumber: 101,
  title: "React 초보자를 위한 가이드",
  content: "React의 기본 개념을 설명합니다...",
  boardTitleImage: "https://example.com/image1.jpg",
  favoriteCount: 25,
  commentCount: 12,
  viewCount: 150,
  writeDatetime: "2025-02-21 14:30:00",
  writeNickname: "홍길동",
  writeProfileImage: "https://example.com/profile1.jpg",
};
*/