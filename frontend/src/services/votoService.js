import axios from 'axios';

const API_URL = 'http://localhost:8080/api/votos';

export const getAllVotos = async () => {
  try {
    const response = await axios.get(API_URL);
    return response.data;
  } catch (error) {
    console.error('Error fetching votos:', error);
    throw error;
  }
};

export const getVotoById = async (id) => {
  try {
    const response = await axios.get(`${API_URL}/${id}`);
    return response.data;
  } catch (error) {
    console.error(`Error fetching voto with id ${id}:`, error);
    throw error;
  }
};

export const createVoto = async (voto) => {
  try {
    const response = await axios.post(API_URL, voto);
    return response;
  } catch (error) {
    return error.response;
  }
};

export const updateVoto = async (id, voto) => {
  try {
    const response = await axios.put(`${API_URL}/${id}`, voto);
    return response.data;
  } catch (error) {
    console.error(`Error updating voto with id ${id}:`, error);
    throw error;
  }
};

export const deleteVoto = async (id) => {
  try {
    await axios.delete(`${API_URL}/${id}`);
  } catch (error) {
    console.error(`Error deleting voto with id ${id}:`, error);
    throw error;
  }
};

export const getUserVotes = async (userId) => {
  try {
    const response = await axios.get(`${API_URL}/usuario/${userId}`);
    return response.data;
  } catch (error) {
    console.error('Error fetching user votes:', error);
    throw error;
  }
};
