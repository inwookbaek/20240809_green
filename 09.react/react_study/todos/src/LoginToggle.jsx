import { useState, useEffect } from "react";

const LoginToggle = () => {
  // 로그인 상태 관리
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  // 로컬 스토리지에서 로그인 상태 확인
  useEffect(() => {
    const savedLoginState = localStorage.getItem("isLoggedIn");
    if (savedLoginState === "true") {
      setIsLoggedIn(true);
    }
  }, []);

  // 로그인/로그아웃 토글 함수
  const handleLoginToggle = () => {
    if (isLoggedIn) {
      localStorage.removeItem("isLoggedIn"); // 로그아웃 시 상태 제거
    } else {
      localStorage.setItem("isLoggedIn", "true"); // 로그인 상태 저장
    }
    setIsLoggedIn(!isLoggedIn);
  };

  return (
    <div style={{ textAlign: "center", marginTop: "50px" }}>
      <h2>{isLoggedIn ? "환영합니다! 😊" : "로그인이 필요합니다. 🔒"}</h2>
      <button
        onClick={handleLoginToggle}
        style={{ padding: "10px 20px", fontSize: "16px" }}
      >
        {isLoggedIn ? "로그아웃" : "로그인"}
      </button>
    </div>
  );
};

export default LoginToggle;
