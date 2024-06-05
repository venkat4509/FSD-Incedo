import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import './AdminPage.css';

const AdminPage = () => {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const navigate = useNavigate(); 

  useEffect(() => {
    axios.get('http://localhost:8080/conversation/admin')
      .then(response => {
        setData(response.data);
        setLoading(false);
      })
      .catch(error => {
        setError(error.message);
        setLoading(false);
      });
  }, []);

  const handleLogout = () => {
    navigate('/login');
  };

  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error}</div>;

  return (
    <div className="page-container">
      <div className="admin-user-page">
        <h1>Admin </h1>
        
        <table>
          <thead>
            <tr>
              <th>Normal User</th>
              <th>Support User</th>
              <th>Timestamp</th>
              <th>Conversation</th>
            </tr>
          </thead>
          <tbody>
            {data.map((item, index) => (
              <tr key={index}>
                <td>{item.normalUserUserName}</td>
                <td>{item.supportUserUserName}</td>
                <td>{item.timestamp}</td>
                <td>{item.content}</td>
              </tr>
            ))}
          </tbody>
        </table>
        <button onClick={handleLogout}>Logout</button> 
      </div>
    </div>
  );
};

export default AdminPage;
