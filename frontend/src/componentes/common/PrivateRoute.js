import React from 'react';
import { Navigate, Outlet } from 'react-router-dom';
import { useAuth } from './AuthContext';

const PrivateRoute = ({ role }) => {
  const { user } = useAuth();
  const storedUser = JSON.parse(localStorage.getItem('user'));

  if (user?.rol === role || storedUser?.rol === role) {
    return <Outlet />;
  }

  return <Navigate to="/" />;
};

export default PrivateRoute;
