import React from "react";
import { Link, useNavigate } from "react-router-dom";

function Navbar() {
  const isAuthenticated = localStorage.getItem("isAuthenticated");
  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.removeItem("isAuthenticated");
    navigate("/login");
  };

  return (
    <nav style={styles.navbar}>
      <ul style={styles.navList}>
        <li style={styles.navItem}>
          <Link to='/' style={styles.navLink}>
            Home
          </Link>
        </li>
        {isAuthenticated ? (
          <>
            <li style={styles.navItem}>
              <Link to='/dashboard' style={styles.navLink}>
                Dashboard
              </Link>
            </li>
            <li style={styles.navItem}>
              <button onClick={handleLogout} style={styles.logoutButton}>
                Logout
              </button>
            </li>
          </>
        ) : (
          <li style={styles.navItem}>
            <Link to='/login' style={styles.navLink}>
              Login
            </Link>
          </li>
        )}
      </ul>
    </nav>
  );
}

const styles = {
  navbar: {
    backgroundColor: "#333",
    padding: "10px",
  },
  navList: {
    listStyle: "none",
    display: "flex",
    justifyContent: "flex-start",
    margin: 0,
    padding: 0,
  },
  navItem: {
    margin: "0 10px",
  },
  navLink: {
    color: "#fff",
    textDecoration: "none",
    fontSize: "16px",
  },
  logoutButton: {
    backgroundColor: "transparent",
    border: "none",
    color: "#fff",
    cursor: "pointer",
    fontSize: "16px",
  },
};

export default Navbar;
