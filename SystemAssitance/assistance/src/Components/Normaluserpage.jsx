import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import "./NormalUserPage.css";
import Chat from "../Asserts/Chat.jpg";

const NormalUserPage = () => {
  const navigate = useNavigate();
  const [responseMessage, setResponseMessage] = useState("");
  const [showLink, setShowLink] = useState(false);
  const [userName, setUserName] = useState("");
  const [userId, setUserId] = useState("");
  const [emailId, setEmailId] = useState("");
  const [userRole, setUserRole] = useState("");
  const [isNormal_UserLoggedIn, setIsNormal_UserLoggedIn] = useState(false);
  const [showProfile, setShowProfile] = useState(false); // State to toggle profile visibility

  useEffect(() => {
    const userName = sessionStorage.getItem("SessionUserName");
    const userId = sessionStorage.getItem("SessionUserId");
    const emailId = sessionStorage.getItem("SessionEmailId");
    const userRole = sessionStorage.getItem("SessionUserRole");
    const isUserLoggedInTemp = sessionStorage.getItem("isNormal_UserLoggedIn");

    if (isUserLoggedInTemp === "true" && userName) {
      setUserName(userName);
      setUserId(userId);
      setEmailId(emailId);
      setUserRole(userRole);
      setIsNormal_UserLoggedIn(true);
    } else {
      navigate("/login");
    }
  }, [navigate]);

  const handleSupportRequest = async (supportType) => {
    const userId = sessionStorage.getItem("SessionUserId");
    const role = supportType;

    try {
      const response = await axios.post(
        "http://localhost:8080/conversation/assign",
        { userId, role }
      );
      console.log(response.data);
      setResponseMessage(response.data);
      setShowLink(true);
    } catch (error) {
      console.error("Error sending support request:", error);
      setResponseMessage("An error occurred. Please try again.");
      setShowLink(false);
    }
  };

  const handleLogout = () => {
    sessionStorage.clear(); // Clear session storage
    navigate("/login"); // Redirect to login page
  };

  const toggleProfile = () => {
    setShowProfile(!showProfile); // Toggle profile visibility
  };

  return (
      <div className="normal-user-page">
        <h1>System Assistance Portal</h1>
        <button onClick={toggleProfile}>
          {showProfile ? "Hide Profile" : "Show Profile"}
        </button>
        {showProfile && (
          <div className="profile-card">
            <div className="card-content">
              <h2>Profile</h2>
              <div className="profile-details">
                <div>
                  <strong>Email ID:</strong> {emailId}
                </div>
                <div>
                  <strong>Role:</strong> {userRole}
                </div>
              </div>
            </div>
          </div>
        )}
        <h3>Chat with our IT Team</h3>
        <h4>
          Having technical issues or questions? Our IT team is here to help!
          Whether you're experiencing a tech glitch or just need some expert
          advice, our friendly and professional IT experts are just a chat away.
          Don’t struggle alone—connect with our IT team for quick and reliable
          solutions. Start a chat now and get the support you need instantly.
          Your technical problems are our priority, and we're ready to assist
          you at any time.
        </h4>
        <div className="button-container">
          <img
            src={Chat}
            alt="IT Support"
            onClick={() => handleSupportRequest("IT_Support")}
            style={{ cursor: "pointer" }}
          />
        </div>
        <br></br>
        <p
          className="response-message"
          style={{ fontWeight: "bold", color: "white" }}
        >
          {responseMessage}
          {showLink && (
            <a
              href="http://localhost:8080/"
              target="_blank"
              rel="noopener noreferrer"
            >
              Here is your link to chat
            </a>
          )}
        </p>
        <br></br>
        <button onClick={handleLogout}>Logout</button>
      </div>
   
  );
};

export default NormalUserPage;
