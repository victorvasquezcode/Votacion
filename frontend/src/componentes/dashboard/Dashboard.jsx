import React, { useEffect, useState } from 'react';
import './Dashboard.css';
import { getAllElecciones } from '../../services/eleccionService';
import { getAllVotos } from '../../services/votoService';
import LogoutButton from '../LogoutButton';

const Dashboard = () => {
  const [elections, setElections] = useState([]);
  const [selectedElection, setSelectedElection] = useState(null);
  const [votes, setVotes] = useState([]);
  const [voteCounts, setVoteCounts] = useState({});

  useEffect(() => {
    const fetchElections = async () => {
      try {
        const data = await getAllElecciones();
        setElections(data);
      } catch (error) {
        console.error('Error fetching elections:', error);
      }
    };

    const fetchVotes = async () => {
      try {
        const data = await getAllVotos();
        setVotes(data);
      } catch (error) {
        console.error('Error fetching votes:', error);
      }
    };

    fetchElections();
    fetchVotes();
  }, []);

  useEffect(() => {
    if (selectedElection && votes.length > 0) {
      const electionVotes = votes.filter(vote => vote.eleccion.id === selectedElection);
      const calculatedVoteCounts = electionVotes.reduce((acc, vote) => {
        acc[vote.seleccion] = (acc[vote.seleccion] || 0) + 1;
        return acc;
      }, {});

      setVoteCounts(calculatedVoteCounts);
    }
  }, [selectedElection, votes]);

  const handleElectionChange = (e) => {
    setSelectedElection(parseInt(e.target.value));
  };

  return (
    <div className="dashboard-container">
      <LogoutButton />
      <h1>Panel de Control</h1>
      <div className="section">
        <h2>Elecciones Disponibles</h2>
        <select onChange={handleElectionChange}>
          <option value="">Selecciona una elección</option>
          {elections.map((election) => (
            <option key={election.id} value={election.id}>{election.nombre}</option>
          ))}
        </select>
      </div>
      {selectedElection && (
        <div className="section">
          <h2>Resultados de Votación</h2>
          <ul>
            {Object.keys(voteCounts).map((candidate) => (
              <li key={candidate}>
                {candidate}: {voteCounts[candidate]} votos
              </li>
            ))}
          </ul>
        </div>
      )}
    </div>
  );
};

export default Dashboard;
