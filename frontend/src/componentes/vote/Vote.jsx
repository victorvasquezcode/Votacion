import React, { useEffect, useState } from 'react';
import './Vote.css';
import { useNavigate } from 'react-router-dom';
import { getAllElecciones, getCandidatosByEleccionId } from '../../services/eleccionService';
import { createVoto } from '../../services/votoService';
import { getUsuarioById } from '../../services/usuarioService';
import LogoutButton from '../LogoutButton';

const Vote = ({ usuarioId }) => {
  const [usuario, setUsuario] = useState(null);
  const [elections, setElections] = useState([]);
  const [selectedElection, setSelectedElection] = useState(null);
  const [candidates, setCandidates] = useState([]);
  const [selection, setSelection] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();

  useEffect(() => {
    const fetchUsuario = async () => {
      try {
        const data = await getUsuarioById(usuarioId);
        setUsuario(data);
      } catch (error) {
        console.error('Error fetching usuario:', error);
      }
    };

    const fetchElections = async () => {
      try {
        const data = await getAllElecciones();
        setElections(data);
      } catch (error) {
        console.error('Error fetching elections:', error);
      }
    };

    fetchUsuario();
    fetchElections();
  }, [usuarioId]);

  const handleElectionChange = async (e) => {
    const electionId = e.target.value;
    setSelectedElection(electionId);
    if (electionId) {
      try {
        const data = await getCandidatosByEleccionId(electionId);
        setCandidates(data);
      } catch (error) {
        console.error('Error fetching candidates:', error);
      }
    } else {
      setCandidates([]);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!selectedElection || !selection) {
      setError('Por favor selecciona una elección y una opción.');
      return;
    }

    if (!usuario || !usuario.id) {
      setError('Usuario no está definido.');
      return;
    }

    try {
      const voto = {
        eleccion: { id: selectedElection },
        usuario: { id: usuario.id },
        seleccion: selection,
        opcion: ''
      };
      const response = await createVoto(voto);
      if (response.status === 201) {
        navigate('/confirmation');
      } else if (response.status === 409) {
        setError('El usuario ya ha votado en esta elección.');
      } else {
        setError('Error al votar. Por favor intenta de nuevo.');
      }
    } catch (error) {
      console.error('Error creating vote:', error);
      setError('Error al votar. Por favor intenta de nuevo.');
    }
  };

  const handleVerifyVotes = () => {
    navigate('/user-dashboard');
  };

  return (
    <div>
      <LogoutButton />
      <div className="vote-container">
        <h1>Votar</h1>
        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label>Elección:</label>
            <select onChange={handleElectionChange}>
              <option value="">Selecciona una elección</option>
              {elections.map((election) => (
                <option key={election.id} value={election.id}>{election.nombre}</option>
              ))}
            </select>
          </div>
          <div className="form-group">
            <label>Candidato:</label>
            <select value={selection} onChange={(e) => setSelection(e.target.value)}>
              <option value="">Selecciona un candidato</option>
              {candidates.map((candidate) => (
                <option key={candidate.id} value={candidate.nombre}>{candidate.nombre}</option>
              ))}
            </select>
          </div>
          <button type="submit">Enviar Voto</button>
          {error && <p className="error">{error}</p>}
        </form>
        <button className="verify-votes-button" onClick={handleVerifyVotes}>
          Verificar Votos
        </button>
      </div>
    </div>
  );
};

export default Vote;
