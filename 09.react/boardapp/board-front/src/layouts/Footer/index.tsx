import React from 'react'
import './style.css'

export default function Footer() {

  const onInstaIconClickHandler = ()=> {
    window.open('https://www.instagram.com');
  }

  const onNaverBlogIconButtonHandler = ()=> {
    window.open('https://blog.naver.com');
  }

  return (
    <div id="footer">
      <div className="footer-container">
        <div className="footer-top">
          <div className="footer-logo-box">
            <div className="icon-box">
              <div className="icon logo-light-icon"></div>
            </div>
            <div className="footer-logo-text">GILCNS's Board</div>
          </div>
          <div className="footer-link-box">
            <div className="footer-email-link">admin@gmail.com</div>
            <div className="icon-button" onClick={onInstaIconClickHandler}>
              <div className="icon insta-icon"></div>
            </div>
            <div className="icon-button" onClick={onNaverBlogIconButtonHandler}>
              <div className="icon naver-blog-icon"></div>
            </div>
          </div>
        </div>
        <div className="footer-bottom">
          <div className="footer-copyright">Copyright &copy; 2025 GILCNS All Rights Reserved.</div>
        </div>
      </div>
    </div>
  )
}
