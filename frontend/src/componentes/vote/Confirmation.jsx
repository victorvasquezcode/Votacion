import React from 'react';
import './Confirmation.css';
import { useNavigate } from 'react-router-dom';
import LogoutButton from '../LogoutButton';

const Confirmation = ({ transactionId }) => {
  const navigate = useNavigate();

  const handleBackClick = () => {
    navigate('/vote');
  };

  return (
    <div className="confirmation-container">
      <LogoutButton />
      <h1>Voto Registrado</h1>
      <p>Tu voto ha sido registrado exitosamente.</p>
      <p>ID de Transacción: {transactionId}</p>
      <button className="back-button" onClick={handleBackClick}>Volver a Votar</button>
    </div>
  );
};

export default Confirmation;
