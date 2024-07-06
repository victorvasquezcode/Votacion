import axios from 'axios';

const API_URL = 'http://localhost:8080/api/usuarios';

export const login = async (dni, contraseña) => {
  try {
    const response = await axios.post(`${API_URL}/authenticate`, { dni, contraseña });
    return response.data;
  } catch (error) {
    console.error('Error logging in:', error);
    throw error;
  }
};

export const getUsuarios = async () => {
  try {
    const response = await axios.get(API_URL);
    return response.data;
  } catch (error) {
    console.error('Error fetching users:', error);
    throw error;
  }
};
