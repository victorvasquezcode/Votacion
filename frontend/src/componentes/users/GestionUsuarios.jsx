// src/components/GestionUsuarios.jsx
import React, { useState } from 'react';
import './GestionUsuarios.css';

const GestionUsuarios = () => {
  const [usuarios, setUsuarios] = useState([]);
  const [nuevoUsuario, setNuevoUsuario] = useState('');

  const agregarUsuario = () => {
    // Lógica para agregar un usuario
    setUsuarios([...usuarios, nuevoUsuario]);
    setNuevoUsuario('');
  };

  return (
    <div className="gestion-usuarios">
      <h1>Gestión de Usuarios</h1>
      <input 
        type="text" 
        value={nuevoUsuario} 
        onChange={(e) => setNuevoUsuario(e.target.value)} 
        placeholder="Nombre del Usuario" 
      />
      <button onClick={agregarUsuario}>Agregar Usuario</button>
      <ul>
        {usuarios.map((usuario, index) => (
          <li key={index}>{usuario}</li>
        ))}
      </ul>
    </div>
  );
};

export default GestionUsuarios;
