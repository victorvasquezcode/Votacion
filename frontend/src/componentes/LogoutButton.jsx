import React from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from './auth/AuthContext';
import './LogoutButton.css';

const LogoutButton = () => {
  const { logout } = useAuth();
  const navigate = useNavigate();

  const handleLogout = () => {
    if (window.confirm('¿Estás seguro de que deseas desconectarte?')) {
      logout();
      navigate('/');
    }
  };

  return (
    <button className="logout-button" onClick={handleLogout}>
      Desconectar
    </button>
  );
};

export default LogoutButton;
