import React, { useState, useContext, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { AuthContext } from "./AuthContext";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";

const Login = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const { login, isLoggedIn } = useContext(AuthContext);
  const navigate = useNavigate();

  // ✅ 로그인 성공 시 자동으로 "/board" 이동
  useEffect(() => {
    if (isLoggedIn) {
      navigate("/board");
    }
  }, [isLoggedIn, navigate]);

  const handleLogin = async () => {
    try {
      const response = await axios.post("http://localhost:8090/members/login", {
        username,
        password,
      });

      if (response.data.accessToken) {
        localStorage.setItem("token", response.data.accessToken);
        login(); // 🔹 로그인 상태 변경
      }
    } catch (error) {
      console.error("Login failed:", error);
    }
  };

  return (
    <Form>
      <Form.Group controlId='username'>
        <Form.Label>Username</Form.Label>
        <Form.Control
          type='text'
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        />
      </Form.Group>

      <Form.Group controlId='password'>
        <Form.Label>Password</Form.Label>
        <Form.Control
          type='password'
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
      </Form.Group>

      <Button variant='primary' onClick={handleLogin} className='mt-3'>
        Login
      </Button>
    </Form>
  );
};

export default Login;
