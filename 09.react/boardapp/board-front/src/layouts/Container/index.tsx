import React from 'react'
import "./style.css";
import { Outlet, useLocation } from 'react-router-dom';
import Header from 'layouts/Header';
import Footer from 'layouts/Footer';
import { AUTH_PATH } from 'constant';

export default function Container() {

  // 현재페이지의 path name
  const { pathname } = useLocation();

  return (
    <>
      <Header />
      <Outlet />
      {pathname !== AUTH_PATH() && <Footer />}
    </>
  )
}
