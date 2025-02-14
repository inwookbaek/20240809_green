import React, { useContext } from "react";
import { BrowserRouter as Router, Route, Routes, Link } from "react-router-dom";
import { AuthProvider, AuthContext } from "./components/AuthContext";
import Login from "./components/Login";
import Register from "./components/Register";
import Board from "./components/Board";
import Test from "./components/Test";

function App() {
  return (
    <AuthProvider>
      <Router>
        <div className='container mt-5'>
          <Navigation />
          <Routes>
            <Route path='/' element={<h2>Welcome to Home Page</h2>} />
            <Route path='/login' element={<Login />} />
            <Route path='/board' element={<BoardWrapper />} />
            <Route path='/register' element={<Register />} />
            <Route path='/test' element={<Test />} />
          </Routes>
        </div>
      </Router>
    </AuthProvider>
  );
}

// 🔹 네비게이션 바
function Navigation() {
  const { isLoggedIn, logout } = useContext(AuthContext);

  return (
    <nav>
      <Link to='/'>Home</Link> |{" "}
      {!isLoggedIn ? (
        <Link to='/login'>Login</Link>
      ) : (
        <button
          onClick={logout}
          style={{
            border: "none",
            background: "none",
            cursor: "pointer",
            color: "blue",
          }}
        >
          Logout
        </button>
      )}{" "}
      | <Link to='/register'>Register</Link> | <Link to='/test'>Test</Link>
    </nav>
  );
}

// 🔹 로그인 없이 게시판 접근 차단
function BoardWrapper() {
  const { isLoggedIn } = useContext(AuthContext);
  return isLoggedIn ? <Board /> : <h2>Please log in</h2>;
}

export default App;
