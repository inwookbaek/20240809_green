import React, { ChangeEvent, useEffect, useRef, useState } from 'react'
import './style.css'
import { Board, CommentListItem, FavoriteListItem } from 'types/interface';
import { commentListMock, favoriteListMock } from 'mocks';
import FavoriteItem from 'components/FavoriteItem';
import CommentItem from 'components/CommentItem';
import Pagination from 'components/Pagination';
import defaultProfileImage from 'assets/image/defaultProfileImage.jpg';
import { useLoginUserStore } from 'stores';
import { useNavigate, useParams } from 'react-router-dom';
import { BOARD_PATH, BOARD_UPDATE_PATH, MAIN_PATH, USER_PATH } from 'constant';
import boardMock from 'mocks/board.mock';

export default function BoardDetail() {
  
  /* global status -------------------------------------------------------- */
  const { boardNumber } = useParams();
  const { loginUser } = useLoginUserStore();
  
  /* global function -------------------------------------------------------- */
  const navigator = useNavigate();
  
  
  /* Top 컴퍼넌트 -------------------------------------------------------- */
  const BoardDetailTop = () => {
    
    /* status -------------------------------------------------------- */
    const [board, setBoard] = useState<Board | null>(null);
    const [showMore, setShowMore] = useState<boolean>(false);
    
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
      setBoard(boardMock)
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
              <div className="board-detail-info-divider">{' \| '}</div>
              <div className="board-detail-write-date">{board?.writeDatetime}</div>
            </div>
            <div className="icon-button" onClick={ onMoreBttonClickHandler }>
              <div className="icon more-icon"></div>
            </div>
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
          {board?.boardImageList.map(image => <img className="board-detail-main-image" src={image} alt=''/>)}
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
    const [commentList, setCommentList] = useState<CommentListItem[]>([]);
    const [isFavorite, setFavorite] = useState<boolean>(false);
    const [showFavorite, setShowFavorite] = useState<boolean>(false);
    const [showComment, setShowComment] = useState<boolean>(false);
    const [comment, setComment] = useState<string>('');
    
    /* event handler-------------------------------------------------------------*/
    const onFavoriteClickHandler = () => {
      setFavorite(!isFavorite);
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

    /* boardNumber 변경시마다 실해 ----------------------------------------------*/
    useEffect(() => {
      setFavoriteList(favoriteListMock);
      setCommentList(commentListMock);
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
              {favoriteList.map(item => <FavoriteItem favoriteListItem={item}/>)}
            </div>
          </div>
        </div>} {/* favorite-box */}
      
        {showComment &&  
        <div className="board-detail-bottom-comment-box">
          <div className="board-detail-bottom-comment-container">
            <div className="board-detail-bottom-comment-title">{'댓글 '}<span className='emphasis'>{ commentList.length }</span></div>
            <div className="board-detail-bottom-comment-list-container">
              {commentList.map(item =>  <CommentItem commentListItem={item}/>)}
            </div>
          </div> {/* comment-container */}

          <div className="divider"></div>

          <div className="board-detail-bottom-comment-pagination-box">
            <Pagination />
          </div> {/* -pagination-box */}

          <div className="board-detail-bottom-comment-input-box">
            <div className="board-detail-bottom-comment-input-container">
              <textarea ref={commentRef} className="board-detail-bottom-comment-textarea" placeholder='댓글을 입력하세요....' value={comment} onChange={ onCommentChangeHandler }/>
              <div className="board-detail-bottom-comment-botton-box">
                <div className={comment === '' ? 'disable-button' : 'black-button'} onClick={ onCommentSubmitButtonClickHandler }>{'댓글달기'}</div>
              </div>
            </div>
          </div> {/* comment-input-box*/}

        </div>} {/* comment-box */}
      </div>
    ) 
  };

  return (
    <div id="board-detail-wrapper">
      <div className="board-detail-container">
        <BoardDetailTop />
        <BoardDetailBottom />
      </div>
    </div>
  )
}
