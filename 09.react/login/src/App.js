import React, { useState, useEffect } from "react";
import { BrowserRouter as Router, Route, Routes, Link, Navigate } from "react-router-dom";
import Home from "./pages/Home";
import Login from "./pages/Login";
import ProductList from "./pages/ProductList";
import ProductDetail from "./pages/ProductDetail";

function App() {
  const [isAuthenticated, setIsAuthenticated] = useState(false);

  useEffect(() => {
    const token = localStorage.getItem("token");
    setIsAuthenticated(!!token);
  }, []);

  const handleLogin = (token) => {
    localStorage.setItem("token", token);
    setIsAuthenticated(true);
  };

  const handleLogout = () => {
    localStorage.removeItem("token");
    setIsAuthenticated(false);
  };

  return (
    <Router>
      <div>
        <nav style={styles.nav}>
          <ul style={styles.navList}>
            <li><Link to="/" style={styles.link}>Home</Link></li>
            {isAuthenticated ? (
              <li><button onClick={handleLogout} style={styles.button}>Logout</button></li>
            ) : (
              <li><Link to="/login" style={styles.link}>Login</Link></li>
            )}
            <li><Link to="/products" style={styles.link}>Products</Link></li>
          </ul>
        </nav>

        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/login" element={<Login onLogin={handleLogin} />} />
          <Route path="/products" element={<ProductList />} />
          <Route path="/products/:id" element={<ProductDetail />} />
          <Route
            path="/protected"
            element={isAuthenticated ? <ProductList /> : <Navigate to="/login" />}
          />
        </Routes>
      </div>
    </Router>
  );
}

const styles = {
  nav: { padding: "10px", background: "#282c34", display: "flex", justifyContent: "space-between" },
  navList: { display: "flex", listStyle: "none", padding: 0, margin: 0 },
  link: { color: "white", textDecoration: "none", padding: "10px" },
  button: { color: "white", background: "transparent", border: "none", cursor: "pointer", padding: "10px" }
};

export default App;
