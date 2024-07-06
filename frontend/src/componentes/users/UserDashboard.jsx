import React, { useEffect, useState } from 'react';
import './UserDashboard.css';
import { useNavigate } from 'react-router-dom';
import { getAllElecciones } from '../../services/eleccionService';
import { getUserVotes } from '../../services/votoService';
import LogoutButton from '../LogoutButton';

const UserDashboard = ({ usuario }) => {
  const [elections, setElections] = useState([]);
  const [votes, setVotes] = useState([]);
  const [error, setError] = useState('');
  const navigate = useNavigate();

  useEffect(() => {
    const fetchElections = async () => {
      try {
        const data = await getAllElecciones();
        setElections(data);
      } catch (error) {
        console.error('Error fetching elections:', error);
        setError('Error al obtener elecciones');
      }
    };

    const fetchUserVotes = async () => {
      try {
        const data = await getUserVotes(usuario.id);
        setVotes(data);
      } catch (error) {
        console.error('Error fetching user votes:', error);
        setError('Error al obtener votos del usuario');
      }
    };

    fetchElections();
    fetchUserVotes();
  }, [usuario.id]);

  const handleBackToVote = () => {
    navigate('/vote');
  };

  return (
    <div className="user-dashboard-container">
      <LogoutButton />
      <h1>Panel de Control del Usuario</h1>
      {error && <p className="error">{error}</p>}
      <div className="section">
        <h2>Elecciones Disponibles</h2>
        <ul>
          {elections.map((election) => (
            <li key={election.id}>
              {election.nombre} - {election.fechaInicio} a {election.fechaFin}
              {votes.find(vote => vote.eleccion.id === election.id) && (
                <span className="voted-status">Ya has votado</span>
              )}
            </li>
          ))}
        </ul>
      </div>
      <div className="section">
        <h2>Mis Votos</h2>
        <ul>
          {votes.map((vote) => (
            <li key={vote.id}>
              Elección: {vote.eleccion.nombre} - Candidato: {vote.seleccion}
            </li>
          ))}
        </ul>
      </div>
      <button className="back-to-vote-button" onClick={handleBackToVote}>
        Regresar a Votar
      </button>
    </div>
  );
};

export default UserDashboard;
