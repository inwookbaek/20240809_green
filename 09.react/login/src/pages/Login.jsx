import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

const Login = ({ onLogin }) => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const handleLogin = async () => {
    if (!email || !password) {
      alert("이메일과 비밀번호를 입력하세요.");
      return;
    }

    try {
      const response = await fetch("http://localhost:8080/api/auth/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email, password }),
      });

      if (!response.ok) {
        throw new Error("로그인 실패! 아이디 또는 비밀번호를 확인하세요.");
      }

      const data = await response.json();
      localStorage.setItem("token", data.token);
      onLogin(data.token);
      navigate("/");
    } catch (error) {
      setError(error.message);
    }
  };

  return (
    <div style={styles.container}>
      <h2>로그인</h2>
      {error && <p style={styles.error}>{error}</p>}
      <input
        type="email"
        placeholder="이메일 입력"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
        style={styles.input}
      />
      <input
        type="password"
        placeholder="비밀번호 입력"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
        style={styles.input}
      />
      <button onClick={handleLogin} style={styles.button}>로그인</button>
    </div>
  );
};

const styles = {
  container: { textAlign: "center", padding: "20px", maxWidth: "300px", margin: "50px auto", border: "1px solid #ccc", borderRadius: "10px" },
  input: { width: "100%", padding: "10px", margin: "10px 0", borderRadius: "5px", border: "1px solid #ccc" },
  button: { width: "100%", padding: "10px", background: "#61dafb", border: "none", borderRadius: "5px", cursor: "pointer" },
  error: { color: "red", fontSize: "14px" }
};

export default Login;
