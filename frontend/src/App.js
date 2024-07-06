import React from 'react';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import LoginRoute from './componentes/auth/LoginRoute';
import Dashboard from './componentes/dashboard/Dashboard';
import UserDashboard from './componentes/users/UserDashboard';
import Vote from './componentes/vote/Vote';
import Confirmation from './componentes/vote/Confirmation';
import { AuthProvider, useAuth } from './componentes/auth/AuthContext';
import './assets/styles/App.css';

const App = () => {
  return (
    <AuthProvider>
      <Router>
        <Routes>
          <Route path="/" element={<LoginRoute />} />
          <Route path="/vote" element={<PrivateRoute role="votante"><VoteRoute /></PrivateRoute>} />
          <Route path="/confirmation" element={<PrivateRoute role="votante"><ConfirmationRoute /></PrivateRoute>} />
          <Route path="/user-dashboard" element={<PrivateRoute role="votante"><UserDashboardRoute /></PrivateRoute>} />
          <Route path="/dashboard" element={<PrivateRoute role="admin"><Dashboard /></PrivateRoute>} />
          <Route path="*" element={<Navigate to="/" />} />
        </Routes>
      </Router>
    </AuthProvider>
  );
};

const PrivateRoute = ({ children, role }) => {
  const { user } = useAuth();
  const storedUser = JSON.parse(localStorage.getItem('user'));

  if (user?.rol === role || storedUser?.rol === role) {
    return children;
  }

  return <Navigate to="/" />;
};

const VoteRoute = () => {
  const { user } = useAuth();
  if (!user) {
    return <Navigate to="/" />;
  }
  return <Vote usuarioId={user.id} />;
};

const ConfirmationRoute = () => {
  const { user } = useAuth();
  if (!user) {
    return <Navigate to="/" />;
  }
  const transactionId = "dummy-transaction-id"; // Reemplaza esto con el ID real de la transacción si es necesario
  return <Confirmation usuario={user} transactionId={transactionId} />;
};

const UserDashboardRoute = () => {
  const { user } = useAuth();
  if (!user) {
    return <Navigate to="/" />;
  }
  return <UserDashboard usuario={user} />;
};

export default App;
