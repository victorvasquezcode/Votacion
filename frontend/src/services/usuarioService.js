import axios from 'axios';

const API_URL = 'http://localhost:8080/api/usuarios';

export const getUsuarioById = async (id) => {
  try {
    const response = await axios.get(`${API_URL}/${id}`);
    return response.data;
  } catch (error) {
    console.error(`Error fetching usuario with id ${id}:`, error);
    throw error;
  }
};