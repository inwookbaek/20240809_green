import React, { ChangeEvent, ChangeEventHandler, useEffect, useRef, useState } from 'react'
import defaultProfileImage from 'assets/image/defaultProfileImage.jpg';
import { useNavigate, useParams } from 'react-router-dom';
import './style.css';
import { BoardListItem } from 'types/interface';
import { latestBoardListMock } from 'mocks';
import BoardItem from 'components/BoardItem';
import Pagination from 'components/Pagination';
import { usePagination } from 'hooks';
import { BOARD_PATH, BOARD_WRITE_PATH, MAIN_PATH, USER_PATH } from 'constant';
import { useLoginUserStore } from 'stores';
import { fileUploadRequest, getUserBoardListRequest, getUserRequest, patchNicknameRequest, patchProfileImageRequest } from 'apis';
import { GetUserResponseDto, PatchNicknameResponseDto, PatchProfileImageResponseDto } from 'apis/response/user';
import { ResponseDto } from 'apis/response';
import { PatchNicknameRequestDto, PatchProfileImageRequestDto } from 'apis/request/user';
import { useCookies } from 'react-cookie';
import { GetUserBoardListResponseDto } from 'apis/response/board';

export default function User() {

  const { userEmail } = useParams();
  const [ cookies, setCookies] = useCookies();
  const [isMyPage, setMyPage] = useState<boolean>(false);
  const { loginUser } = useLoginUserStore();

  const navigate = useNavigate();

  const UserTop = () => {

    const imageIputReef = useRef<HTMLInputElement | null>(null);  
    const [isNicknameChange, setNicknameChange] = useState<boolean>(false);
    const [nickname, setNickname] = useState<string>('');
    const [changeNickname, setChangeNickname] = useState<string>('');
    const [profileImage, setProfileImage] = useState<string | null>(null);
    
    const gerUserResponse = (responseBody: GetUserResponseDto | ResponseDto | null) => {
      if(!responseBody) return;
      const { code } = responseBody;
      if(code === 'NB') alert('존재하지 않는 게시물 입니다!!');
      if(code === 'DBE') alert('데이터베이스 오류 입니다!!');
      if(code !== 'SU') {
        navigate(MAIN_PATH());
        return; 
      }

      const { email, nickname, profileImage } = responseBody as GetUserResponseDto;
      setNickname(nickname);
      setProfileImage(profileImage);

      const isMyPage = email === loginUser?.email;
      setMyPage(isMyPage); 
    };

    const fileUploadResponse = (profileImage: string | null) => {
      if(!profileImage) return;
      if( !cookies.accessToken) return;

      const requestBody: PatchProfileImageRequestDto = { profileImage };

      patchProfileImageRequest(requestBody, cookies.accessToken)
        .then(patchProfileImageResponse)
        .catch(error => {
          console.error(error);
          alert('프로필 이미지 업데이트 중 오류가 발생했습니다.');
        });
    }

    const patchProfileImageResponse = (responseBody: PatchProfileImageResponseDto | ResponseDto | null) => {
      if(!responseBody) return;
      
      const { code } = responseBody;
      if(code === 'AF') alert('인증에 실패 했습니다!!');
      if(code === 'NU') alert('존재하지 않는 사용자입니다!!');
      if(code === 'DBE') alert('데이터베이스 오류 입니다!!');            
      if(code !== 'SU') return;

      if(!userEmail) return;    
      getUserRequest(userEmail).then(gerUserResponse); 
    }

    const patchNicknameResponse = (responseBody: PatchNicknameResponseDto | ResponseDto | null) => {
      if(!responseBody) return;
      const { code } = responseBody;
      if(code === 'VF') alert('닉네임은 필수 입력사항입니다!!');
      if(code === 'AF') alert('인증에 실패 했습니다!!');
      if(code === 'DN') alert('중복된 닉네임입니다!!');
      if(code === 'NU') alert('존재하지 않는 사용자입니다!!');
      if(code === 'DBE') alert('데이터베이스 오류 입니다!!');            
      if(code !== 'SU') return;
      
      if(!userEmail) return;    
      getUserRequest(userEmail).then(gerUserResponse);
      setNicknameChange(false);
    };    

    const onProfileBoxClickHandler = () => {
      if(!isMyPage) return;
      if(!imageIputReef.current) return;
      imageIputReef.current?.click();
    }

    const onNicknameEditButtonClickHandler = () => {     

      if(!isNicknameChange) {
        setChangeNickname(nickname);
        setNicknameChange(!isNicknameChange);
        return;
      } 

      if(!cookies.accessToken) return;

      const requestBody: PatchNicknameRequestDto = {
        nickname: changeNickname
      };

     patchNicknameRequest(requestBody, cookies.accessToken).then(patchNicknameResponse);
    }

    const onProfileImageChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
      if(!event.target.files || !event.target.files.length) return;

      const file = event.target.files[0];
      const data = new FormData();
      data.append('file', file);    

      fileUploadRequest(data).then(fileUploadResponse);
    }

    const onNicknameChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
      const { value } = event.target; 
      setChangeNickname(value);
    }

    useEffect(() => {
      if(!userEmail) return;
      // setNickname('나는 이누기!!');
      // setProfileImage('https://newsimg.sedaily.com/2020/09/23/1Z7YTQGX0D_1.jpg');
      getUserRequest(userEmail).then(gerUserResponse);
      
    }, [userEmail]);

    return (
      <div id='user-top-wrapper'>
        <div className="user-top-container">
          {isMyPage 
            ? <div className="user-top-my-profile-image-box" onClick={onProfileBoxClickHandler}>
                {profileImage !== null && profileImage !== ''
                  ? <div className="user-top-profile-image" style={{ backgroundImage: `url(${profileImage})` }}></div>
                  : <div className='icon-box-large'>
                      <div className="icon image-box-white-icon"></div>
                    </div> 
                }
                <input ref={imageIputReef} type="file" accept='image/*' style={{ display: 'none' }} onChange={onProfileImageChangeHandler}/>
              </div>             
            : <div className="user-top-profile-image-box" style={{ backgroundImage: `url(${profileImage ? profileImage : defaultProfileImage})`}}></div>
          }
          <div className="user-top-info-box"> 
            <div className="user-top-info-nickname-box">
              {isMyPage
                ? <>
                    {isNicknameChange 
                      ? <input className='user-top-info-nickname-input' type="text" size={ changeNickname.length } value={changeNickname} onChange={onNicknameChangeHandler} />
                      : <div className="user-top-info-nickname">{nickname}</div>
                    }
                    <div className="icon-button" onClick={onNicknameEditButtonClickHandler}>
                      <div className="icon edit-icon"></div>
                    </div>
                  </> 
                : <div className="user-top-info-nickname">{nickname}</div>
              }
            </div>
            <div className="user-top-info-email">{userEmail}</div>
          </div>
        </div>
      </div>
    );
  }

  const UserBottom = () => {

    const {currentPage, currentSection, viewList, viewPageList, totalSection,
      setCurrentPage, setCurrentSection, setTotalList 
    } = usePagination<BoardListItem>(3);

    const [count, setCount] = useState<number>(2);
    const [userBoardList, setUserBoardList] = useState<BoardListItem[]>([]);
    
    const gerUserBoardListResponse = (responseBody: GetUserBoardListResponseDto | ResponseDto | null) => {
      if(!responseBody) return;
      const { code } = responseBody;
      if(code === 'NU') {
        alert('존재하지 않는 사용자입니다!!');
        navigate(MAIN_PATH());
        return;
      }; 
      if(code === 'DBE') alert('데이터베이스 오류 입니다!!');            
      if(code !== 'SU') return;
      
      const { userBoardList } = responseBody as GetUserBoardListResponseDto;
      setTotalList(userBoardList);
      setCount(userBoardList.length);
    }

    const onSideCardClickHandler = () => {
      if(isMyPage) navigate(BOARD_PATH() + '/' + BOARD_WRITE_PATH());
      else if(loginUser) navigate(USER_PATH(loginUser.email));
    }

    useEffect(() => { 
      // setCount(10);
      // setUserBoardList(latestBoardListMock);
      if(!userEmail) return;
      getUserBoardListRequest(userEmail).then(gerUserBoardListResponse);

    }, [userEmail]);

    return (
      <div id='user-bottom-wrapper'>
        <div className="user-bottom-container">
          <div className="user-bottom-title">{isMyPage ? '내 게시글 ' : '게시글 '}<span className="emphasis">{count}</span></div>
          <div className="user-bottom-contents-box">
            {count === 0 
              ? <div className='user-bottom-contents-nothing'>{'게시물이 없습니다!!'}</div>
              : <div className='user-bottom-contents'>
                  {/* { userBoardList.map((boardListItem, index) =>  <BoardItem boardListItem={boardListItem} />) } */}
                  { viewList.map((boardListItem, index) =>  <BoardItem boardListItem={boardListItem} />) }

                </div>
            }
            <div className="user-bottom-side-box">
              <div className="user-bottom-side-card" onClick={onSideCardClickHandler}>
                <div className="user-bottom-side-container">
                  {isMyPage 
                    ? <>
                        <div className='icon-box'>
                          <div className="icon edit-icon"></div>
                        </div>
                        <div className='user-bottom-side-text'>{'글쓰기'}</div>
                      </>
                    : <>
                       <div className='user-bottom-side-text'>{'내 게시물로 가기가기'}</div>
                       <div className='icon-box'>
                          <div className="icon arrow-right-icon"></div>
                        </div>
                      </>
                  }
                </div>
              </div>
            </div>
          </div>
          <div className="user-bottom-pagination-box">
            {count !== 0 && 
              <Pagination 
                currentPage = {currentPage}
                currentSection = {currentSection}
                setCurrentPage = {setCurrentPage}
                setCurrentSction = {setCurrentSection}
                viewPageList = {viewPageList}
                totalSection = {totalSection} 
              /> 
            }
          </div>
        </div>
      </div>
    );
  }

  return (
    <>
      <UserTop />
      <UserBottom />
    </>
  );
}
