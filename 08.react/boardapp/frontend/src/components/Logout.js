import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";

const Logout = ({ onLogout }) => {
  const navigate = useNavigate();

  useEffect(() => {
    localStorage.removeItem("isLoggedIn");
    localStorage.removeItem("token");
    onLogout();
    navigate("/login"); // 로그아웃 후 로그인 페이지로 이동
  }, [onLogout, navigate]);

  return <h2>Logging out...</h2>;
};

export default Logout;
