import React from 'react'
import defaultProfileImage from "assets/image/defaultProfileImage.jpg";
import FavoriteListItem from 'types/interface/favorite-list-item.interface';
import './style.css'

interface Props {
  favoriteListItem: FavoriteListItem
}

// component : Favorite List Item 컴퍼넌트
export default function FavoriteItem({ favoriteListItem } : Props) {

  // props
  const { profileImage, nickname } = favoriteListItem;

  return (
    <div className='favorite-list-item'>
        <div className='favorite-list-item-profile-box'>
            <div className='favorite-list-item-profile-image' style={{ backgroundImage:  `url(${ profileImage ? profileImage : defaultProfileImage })`}}></div>
        </div>
        <div className='favorite-list-item-nickname'>{nickname}</div>
    </div>
  )
}
