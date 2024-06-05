import React from "react";
import { useNavigate } from "react-router-dom";
import "./HomePage.css";

const HomePage = () => {
  const navigate = useNavigate();

  const handleLoginClick = () => {
    navigate("/login");
  };

  const handleRegisterClick = () => {
    navigate("/register");
  };

  return (
    <div className="page-container">
      <div className="card">
        <div className="centered-content">
          <div className="home-content">
            <h1>System Assistance</h1>
            <p>Welcome, Please login or register to continue.</p>
          </div>

          <div className="buttons">
            <button className="button" onClick={handleLoginClick}>
              Login
            </button>
            <button className="button" onClick={handleRegisterClick}>
              Register
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default HomePage;
