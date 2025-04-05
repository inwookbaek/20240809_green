import { useNavigate } from 'react-router-dom';
import  './style.css'
import { CommentListItem } from 'types/interface'
import defaultProfileImage from "assets/image/defaultProfileImage.jpg";
import dayjs from 'dayjs';

interface Props {
  commentListItem: CommentListItem;
}

// component : Comment List Item
export default function CommentItem({ commentListItem } : Props) {
  
  // properties
  const { nickname, profileImage, writeDatetime, content} = commentListItem;

  // DateFormat
  const getRlapsedTime = () => {
    const now = dayjs().add(9, 'hour'); 
    const writeTime = dayjs(writeDatetime);

    const gap = now.diff(writeTime, 's');
    if(gap < 60) return `${gap}초 전`;
    if(gap < 3600) return `${Math.floor(gap / 60)}분 전`;
    if(gap < 86400) return `${Math.floor(gap / 3600)}시간 전`;
    if (gap < 2592000) return `${Math.floor(gap / 86400)}일 전`;  // 일 단위 (1일 ~ 30일)
    
    const monthDiff = now.diff(writeTime, 'months'); // 개월 단위 (1개월 ~ 12개월)
    if (monthDiff < 12) return `${monthDiff}개월 전`;
  
    // 년 단위 (1년 이상)
    const yearDiff = now.diff(writeTime, 'years');
    return `${yearDiff}년 전`;
  }

  return (
    <div className='comment-list-item'>
        <div className='comment-list-item-top'>
            <div className='comment-list-item-profile-box'>
                <div className='comment-list-item-profile-image' style={{ backgroundImage : `url(${ profileImage ? profileImage : defaultProfileImage })`}}></div>
            </div>
            <div className='comment-list-item-nickname'>{nickname}</div>
            <div className='comment-list-item-divider'>{'\|'}</div>
            <div className='comment-list-item-time'>{getRlapsedTime()}</div>
        </div>
        <div className='comment-list-item-main'>
            <div className='comment-list-item-main-content'>{content}</div>  
        </div>
    </div>
  )
}
