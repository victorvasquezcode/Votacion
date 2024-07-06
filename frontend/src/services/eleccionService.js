import axios from 'axios';

const API_URL = 'http://localhost:8080/api/elecciones';

export const getAllElecciones = async () => {
  try {
    const response = await axios.get(API_URL);
    return response.data;
  } catch (error) {
    console.error('Error fetching elecciones:', error);
    throw error;
  }
};

export const getEleccionById = async (id) => {
  try {
    const response = await axios.get(`${API_URL}/${id}`);
    return response.data;
  } catch (error) {
    console.error(`Error fetching eleccion with id ${id}:`, error);
    throw error;
  }
};

export const createEleccion = async (eleccion) => {
  try {
    const response = await axios.post(API_URL, eleccion);
    return response.data;
  } catch (error) {
    console.error('Error creating eleccion:', error);
    throw error;
  }
};

export const updateEleccion = async (id, eleccion) => {
  try {
    const response = await axios.put(`${API_URL}/${id}`, eleccion);
    return response.data;
  } catch (error) {
    console.error(`Error updating eleccion with id ${id}:`, error);
    throw error;
  }
};

export const deleteEleccion = async (id) => {
  try {
    await axios.delete(`${API_URL}/${id}`);
  } catch (error) {
    console.error(`Error deleting eleccion with id ${id}:`, error);
    throw error;
  }
};

export const getCandidatosByEleccionId = async (eleccionId) => {
  try {
    const response = await axios.get(`${API_URL}/${eleccionId}/candidatos`);
    return response.data;
  } catch (error) {
    console.error(`Error fetching candidatos for eleccion with id ${eleccionId}:`, error);
    throw error;
  }
};
