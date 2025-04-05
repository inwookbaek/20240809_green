import React, { ChangeEvent, useEffect, useRef, useState } from 'react'
import dayjs from 'dayjs';
import './style.css'
import { Board, CommentListItem, FavoriteListItem } from 'types/interface';
// import { commentListMock, favoriteListMock } from 'mocks';
// import boardMock from 'mocks/board.mock'; // 테스트테이타
import FavoriteItem from 'components/FavoriteItem';
import CommentItem from 'components/CommentItem';
import Pagination from 'components/Pagination';
import defaultProfileImage from 'assets/image/defaultProfileImage.jpg';
import { useLoginUserStore } from 'stores';
import { useNavigate, useParams } from 'react-router-dom';
import { BOARD_PATH, BOARD_UPDATE_PATH, MAIN_PATH, USER_PATH } from 'constant';
import { getBoardRequest, getCommentListRequest, getFavoriteListRequest, increaseViewCountRequest, putFavoriteRequest } from 'apis';
import { GetBoardResponseDto, GetCommentListResponseDto, GetFavoriteListResponseDto, IncreaseViewCountResponseDto, PutFavoriteResponseDto } from 'apis/response/board';
import { ResponseDto } from 'apis/response';
import { useCookies } from 'react-cookie';

export default function BoardDetail() {
  
  /* global 상태 -------------------------------------------------------- */
  const { boardNumber } = useParams();
  const { loginUser } = useLoginUserStore();
  const [cookies, setCookies] = useCookies();
  
  /* global 실행함수 -------------------------------------------------------- */
  const navigator = useNavigate();

  const IncreaseViewCountResponse = (responseBody: IncreaseViewCountResponseDto | ResponseDto | null) => {
    if(!responseBody) return;
    const { code } = responseBody;
    if(code === 'NB') alert('존재하지 않는 게시물입니다!!');
    if(code === 'DBE') alert('데이터베이스 오류입니다!!');
  }
  
  /* Top 컴퍼넌트 -------------------------------------------------------- */
  const BoardDetailTop = () => {
    
    /* status ------------------------------------------------------------ */
    const [isWriter, setWriter] = useState<boolean>(false);
    const [board, setBoard] = useState<Board | null>(null);
    const [showMore, setShowMore] = useState<boolean>(false);
    
    /* function ------------------------------------------------------------ */
    // Dateformat
    const getWriteDateTimeFormat = () => {
      if(!board) return '';
      const date = dayjs(board.writeDatetime);
      return date.format('YYYY.MM.DD hh:mm:ss');
    }

    // board 상세내용
    const getBoardResponse = (responseBody: GetBoardResponseDto | ResponseDto | null) => {
      if(!responseBody) return;
      const { code } = responseBody;
      if(code === 'NB') alert('존재하지 않는 게시물 입니다!!');
      if(code === 'DBE') alert('데이터베이스 오류 입니다!!');
      if(code !== 'SU') {
        navigator(MAIN_PATH());
        return;
      }

      const board: Board = { ...responseBody as GetBoardResponseDto}
      setBoard(board);

      if(!loginUser) {
        setWriter(false)
        return;
      }
      const isWriter = loginUser.email=== board.writerEmail;
      setWriter(isWriter);
    }
    
    /* event handler -------------------------------------------------------- */
    const onNicknameClickHandler = () => {
      if(!board) return;
      navigator(USER_PATH(board.writerEmail));
    } 
    
    const onMoreBttonClickHandler = () => {
      setShowMore(!showMore)
    } 
    
    const onUpdateButtonClickHandler = () => {
      if(!board || !loginUser) return;
      if(loginUser.email !== board.writerEmail) return;
      navigator(BOARD_PATH() + '/' + BOARD_UPDATE_PATH(board.boardNumber));
    }
    
    const onDeleteButtonClickHandler = () => {
      if(!board || !loginUser) return;
      if(loginUser.email !== board.writerEmail) return;
      // todo delete request
      navigator(MAIN_PATH())
    }
        
    /* boardNumber변경시마다 해당 board정보 조회 --------------------------- */
    useEffect(() => {
      // setBoard(boardMock) /* mock data로 테스트 */
      if(!boardNumber) {
        navigator(MAIN_PATH());
        return;
      }
    
      getBoardRequest(boardNumber).then(response => {
        // console.log('API Response:', response); // 응답 데이터 로깅
        getBoardResponse(response);
      });
    }, [boardNumber])

    //if(!board) return <></> /* board가 없다면 반환값 없음 */
    return (
      <div className="board-detail-top">
        <div className="board-detail-top-header">
          <div className="board-detail-title">{board?.title}</div>
          <div className="board-detail-top-sub-box">
            <div className="board-detail-write-info-box">
              <div className="board-detail-writer-profile-image" style={{ backgroundImage: `url(${board?.writerProfileImage ? board.writerProfileImage : defaultProfileImage})`}}></div>
              <div className="board-detail-writer-nickname" onClick={ onNicknameClickHandler }>{board?.writerNickname}</div>
              <div className="board-detail-info-divider">{' / '}</div>
              <div className="board-detail-write-date">{getWriteDateTimeFormat()}</div> {/* dateformat plugin 설치 : npm i dayjs */}
            </div>
            {isWriter && 
            <div className="icon-button" onClick={ onMoreBttonClickHandler }>
              <div className="icon more-icon"></div>
            </div>
            }
            {showMore &&
            <div className="board-detail-more-box">
              <div className="board-detail-update-button" onClick={ onUpdateButtonClickHandler }>{'수정'}</div>
              <div className="divider"></div>
              <div className="board-detail-delete-button" onClick={ onDeleteButtonClickHandler }>{'삭제'}</div>
            </div>
            }
          </div>
        </div> {/* "board-detail-top-header" */}

        <div className="divider"></div>
        <div className="board-detail-top-main">
          <div className="board-detail-main-text">{board?.content}</div>
          {/* React에서 배열을 렌더링할 때는 반드시 각 자식 요소에 고유한 key prop을 제공해야 한다 
              이 key는 React가 요소를 효율적으로 업데이트하고 재사용할 수 있도록 도와준다. 일반적으로
              데이터의 고유 ID를 사용하는 것이 가장 좋으며, 최후의 수단으로 배열 인덱스를 사용할 수 있다.
          */}
          {board?.boardImageList.map((image, index) => <img key={index} className="board-detail-main-image" src={image} alt=''/>)}
        </div>
      </div>
    )
  };

    /* Bottom 컴퍼넌트 -------------------------------------------------------- */
  const BoardDetailBottom = () => {

    /* ref -------------------------------------------------------------------*/
    const commentRef = useRef<HTMLTextAreaElement | null>(null);
    
    /* state -------------------------------------------------------------------*/
    const [favoriteList, setFavoriteList] = useState<FavoriteListItem[]>([]);
    const [isFavorite, setFavorite] = useState<boolean>(false);
    const [showFavorite, setShowFavorite] = useState<boolean>(false);
    
    const [commentList, setCommentList] = useState<CommentListItem[]>([]);
    const [showComment, setShowComment] = useState<boolean>(false);
    const [comment, setComment] = useState<string>('');

    /* function -------------------------------------------------------------*/
    // 좋아요목록
    const getFavoriteListResponse = (responseBody: GetFavoriteListResponseDto | ResponseDto | null) => {
      if(!responseBody) return;
      const { code } = responseBody;
      if(code === 'NB') alert('존재하지 않는 게시물입니다!!');
      if(code === 'DBE') alert('데이터베이스 오류입니다!!');
      if(code !== 'SU') return;

      const { favoriteList } = responseBody as GetFavoriteListResponseDto;
      setFavoriteList(favoriteList);

      if(!loginUser) {
        setFavorite(false);
        return;
      }

      const isFavorite = favoriteList.findIndex(favorite => favorite.email === loginUser.email) !== -1;
      setFavorite(isFavorite);
    }

    const getCommentListResponse= (responseBody: GetCommentListResponseDto | ResponseDto | null) => {
      if(!responseBody) return;
      const { code } = responseBody;
      if(code === 'NB') alert('존재하지 않는 게시물입니다!!');
      if(code === 'DBE') alert('데이터베이스 오류입니다!!');
      if(code !== 'SU') return;

      const { commentList } = responseBody as GetCommentListResponseDto;
      setCommentList(commentList);
    }
    
    const putFavoriteResponse= (responseBody: PutFavoriteResponseDto | ResponseDto | null) => {
      if(!responseBody) return;
      const { code } = responseBody;
      if(code === 'VF') alert('잘못된 접근입니다!!');
      if(code === 'NU') alert('존재하지 않는 사용자입니다!!');
      if(code === 'NB') alert('존재하지 않는 게시물입니다!!');
      if(code === 'AF') alert('인증에 실패했습니다!!');
      if(code === 'DBE') alert('데이터베이스 오류입니다!!');
      if(code !== 'SU') return;

      if(!boardNumber) return;
      getFavoriteListRequest(boardNumber).then(getFavoriteListResponse);

    }

    /* event handler-------------------------------------------------------------*/
    const onFavoriteClickHandler = () => {
      // click toggle 로직을 아래로직으로 대체
      // setFavorite(!isFavorite);
      if(!boardNumber || !loginUser || !cookies.accessToken) return;
      putFavoriteRequest(boardNumber, cookies.acessToken).then(putFavoriteResponse);
    }
    
    const onShowFavoriteClickHandler = () => {
      setShowFavorite(!showFavorite);
    }
    
    const onShowCommentClickHandler = () => {
      setShowComment(!showComment);
    }
    
    const onCommentSubmitButtonClickHandler = () => {
      if(!comment) return;
      alert('댓글달기버튼 클릭!!!')
    }
    
    const onCommentChangeHandler = (event: ChangeEvent<HTMLTextAreaElement>) => {
      const { value }= event.target;
      setComment(value);
      if(!commentRef.current) return;
      commentRef.current.style.height = 'auto';
      commentRef.current.style.height = `${commentRef.current.scrollHeight}px`;
    }

    /* boardNumber 변경시마다 실행 ----------------------------------------------*/
    useEffect(() => {
      //setFavoriteList(favoriteListMock);
      if(!boardNumber) return;
      getFavoriteListRequest(boardNumber).then(getFavoriteListResponse);

      // setCommentList(commentListMock);
      getCommentListRequest(boardNumber).then(getCommentListResponse)

    }, [boardNumber]);

    return (
      <div className="board-detail-bottom">
        <div className="board-detail-bottom-box">
          <div className="board-detail-bottom-group">
            <div className="icon-button" onClick={ onFavoriteClickHandler }>
              {isFavorite ?
                <div className="icon favorite-fill-icon"></div> : 
                <div className="icon favorite-light-icon"></div>
              }
            </div>
            <div className="board-detail-bottom-text">{`좋아요 ${favoriteList.length}`}</div>
            <div className="icon-button" onClick={ onShowFavoriteClickHandler }>
              {showFavorite ? 
                <div className="icon up-light-icon"></div> :
                <div className="icon down-light-icon"></div>              
              }
            </div>
          </div> {/* bottom-group 좋아요 */}

          <div className="board-detail-bottom-group">
            <div className="icon-button">
              <div className="icon comment-icon"></div>
            </div>
            <div className="board-detail-bottom-text">{`댓글 ${commentList.length}`}</div>
            <div className="icon-button" onClick={ onShowCommentClickHandler }>
            {showComment ? 
              <div className="icon up-light-icon"></div> :
              <div className="icon down-light-icon"></div>              
            }
            </div>
          </div> {/* bottom-group 댓글 */}
        </div> {/* bottom-box */}
        {showFavorite &&
        <div className="board-detail-bottom-favorite-box">
          <div className="board-detail-bottom-favorite-container">
            <div className="board-detail-bottom-favorite-title">{'좋아요 '}<span className='emphasis'>{ favoriteList.length }</span></div>
            <div className="board-detail-bottom-favorite-contents">
              {favoriteList.map((item, idx) => <FavoriteItem key={idx} favoriteListItem={item}/>)}
            </div>
          </div>
        </div>} {/* favorite-box */}
      
        {showComment &&  
        <div className="board-detail-bottom-comment-box">
          <div className="board-detail-bottom-comment-container">
            <div className="board-detail-bottom-comment-title">{'댓글 '}<span className='emphasis'>{ commentList.length }</span></div>
            <div className="board-detail-bottom-comment-list-container">
              {commentList.map((item, idx)  =>  <CommentItem key={idx} commentListItem={item}/>)}
            </div>
          </div> {/* comment-container */}

          <div className="divider"></div>

          <div className="board-detail-bottom-comment-pagination-box">
            <Pagination />
          </div> {/* -pagination-box */}

          {loginUser !== null &&
          <div className="board-detail-bottom-comment-input-box">
            <div className="board-detail-bottom-comment-input-container">
              <textarea ref={commentRef} className="board-detail-bottom-comment-textarea" placeholder='댓글을 입력하세요....' value={comment} onChange={ onCommentChangeHandler }/>
              <div className="board-detail-bottom-comment-botton-box">
                <div className={comment === '' ? 'disable-button' : 'black-button'} onClick={ onCommentSubmitButtonClickHandler }>{'댓글달기'}</div>
              </div>
            </div>
          </div> /* comment-input-box*/
          } 

        </div>} {/* comment-box */}
      </div>
    ) 
  };

  /* 게시물조회수 증가 (4번씩 증가를 1번으로 적용 ------------------------------*/
  let effectFlag = true;
  useEffect(() => {
    if(!boardNumber) return;
    if(effectFlag) {
      effectFlag = false;
      return;
    }

    increaseViewCountRequest(boardNumber).then(IncreaseViewCountResponse);
  }, [boardNumber])

  return (
    <div id="board-detail-wrapper">
      <div className="board-detail-container">
        <BoardDetailTop />
        <BoardDetailBottom />
      </div>
    </div>
  )
}
