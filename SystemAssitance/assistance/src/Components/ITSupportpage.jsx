import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "./ITSupportpage.css";
import Noti from "../Asserts/Noti.jpg"; // Import your notification icon image

const ITSupportPage = () => {
  const navigate = useNavigate(); // Hook for navigation
  const [responseMessage, setResponseMessage] = useState("");
  const [showLink, setShowLink] = useState(false);
  const [showProfile, setShowProfile] = useState(false); // State to toggle profile visibility
  const [email, setEmailId] = useState("");
  const [userRole, setUserRole] = useState("");
  const [userId, setUserId] = useState("");

  useEffect(() => {
    const email = sessionStorage.getItem("SessionEmailId");
    const userRole = sessionStorage.getItem("SessionUserRole");
    const userId = sessionStorage.getItem("SessionUserId");

    if (email && userRole && userId) {
      setEmailId(email);
      setUserRole(userRole);
      setUserId(userId);
    } else {
      // Redirect to login if user data is not available in session
      navigate("/login");
    }
  }, [navigate]);

  const handleCheckRequest = async () => {
    try {
      const response = await axios.post(
        "http://localhost:8080/Supportpage/checkMessage",
        { email },
        console.log(email)
      );
      setResponseMessage(response.data);
      if(response.data == "No user has been assigned to you."){
        setShowLink(false);
      }else{
        setShowLink(true);
      }
    } catch (error) {
      console.error("Error checking request:", error);
      setResponseMessage("An error occurred. Please try again.");
      setShowLink(false); 
    }
  };

  const handleEndChat = async () => {
    try {
      await axios.post("http://localhost:8080/Supportpage/end", { userId });
      setResponseMessage("Chat ended successfully.");
      setShowLink(false);
    } catch (error) {
      console.error("Error ending chat:", error);
      setResponseMessage(
        "An error occurred while ending the chat. Please try again."
      );
    }
  };

  const toggleProfile = () => {
    setShowProfile(!showProfile); // Toggle profile visibility
  };

  const handleLogout = () => {
    sessionStorage.clear(); // Clear session storage
    navigate("/login"); // Redirect to login page
  };

  return (
    <div className="ITSupport-user-page">
      <h1>System Assistance</h1>
      <button onClick={toggleProfile}>
        {showProfile ? "Hide Profile" : "Show Profile"}
      </button>
      {showProfile && (
        <div className="profile-card">
          <div className="card-content">
            <h2>Profile</h2>
            <div className="profile-details">
              <div>
                <strong>Email ID:</strong> {email}
              </div>
              <div>
                <strong>Role:</strong> {userRole}
              </div>
            </div>
          </div>
        </div>
      )}
      <h4>
        Click the button below to check for messages and promptly address
        queries. Stay informed and respond swiftly to ensure efficient
        communication. Your proactive engagement helps streamline our response
        process.
      </h4>
      <div className="button-container">
        <button onClick={handleCheckRequest}>
          <img src={Noti} alt="Notification" /> Check for request
        </button>
        {showLink && <button onClick={handleEndChat}>End</button>}
      </div>
      <p className="response-message">
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
      <button onClick={handleLogout}>Logout</button>
    </div>
  );
};

export default ITSupportPage;
