import './App.css';
import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import HomePage from './Components/HomePage';
import RegisterPage from './Components/RegisterPage';
import LoginPage from './Components/Loginpage';
import NormalUserPage from './Components/Normaluserpage';
import AdminPage from './Components/AdminPage';
import ITSupportPage from './Components/ITSupportpage';


const App = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/register" element={<RegisterPage />} />
        <Route path="/normalUserPage" element={<NormalUserPage />} />
        <Route path="/adminPage" element={<AdminPage />} />
        <Route path="/ITSupportPage" element={<ITSupportPage />} />
      </Routes>
    </Router>
  );
};

export default App;
