import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import "./LoginPage.css";

const LoginPage = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [errorMessage, setErrorMessage] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post('http://localhost:8080/User/login', {
        email,
        password,
      });

      if (response.data === "Login successful") {
        const roleResponse = await axios.post('http://localhost:8080/User/getRoleandUserID', { email });
        
        const { role: userRole, userId: UserId, username: UserName } = roleResponse.data;

        // Store user details in session storage
        sessionStorage.setItem('isNormal_UserLoggedIn', 'true');
        sessionStorage.setItem('SessionUserRole', userRole);
        sessionStorage.setItem('SessionUserId', UserId);
        sessionStorage.setItem('SessionEmailId', email);
        sessionStorage.setItem('SessionUserName', UserName);

        if (userRole === "Normal_User") {
          navigate('/normalUserPage');
          console.log('Session Storage:', sessionStorage);
        } else if (userRole === "Admin") {
          navigate('/adminPage');
        } else if (userRole === "IT_Support") {
          navigate('/ITSupportPage');
        } else if (userRole === "HR_Support") {
          navigate('/HRSupportPage');
        } else {
          setErrorMessage("Unexpected role received");
        }
      } else {
        setErrorMessage("Invalid login details");
      }
    } catch (error) {
      console.error('Error occurred:', error);
      setErrorMessage("An error occurred during login");
    }
  };

  return (
    <div className="page-container">
    <div className="login-page">
      <h1>Login</h1>
      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="email">Email:</label>
          <input
            type="email"
            id="email"
            name="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
        </div>
        <div>
          <label htmlFor="password">Password:</label>
          <input
            type="password"
            id="password"
            name="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>
        <button type="submit">Login</button>
      </form>
      {errorMessage && <p className="error-message">{errorMessage}</p>}
    </div>
    </div>
  );
};

export default LoginPage;
