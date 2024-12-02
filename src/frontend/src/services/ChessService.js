import axios from 'axios';
import { BACKEND } from './Service';

const CHESS_API_BASE_URL = BACKEND + '/api/v1/boards';

export const startGuestGame = (mode) => axios.put(CHESS_API_BASE_URL + "?mode=" + mode);

export const move = (id, moves) => axios.patch(CHESS_API_BASE_URL + '/' + id + '/moves', moves);

export const getBoard = (id) => axios.get(CHESS_API_BASE_URL + '/' + id);

export const getChessPiece = (row, col) => axios.get(CHESS_API_BASE_URL + '/' + id + '/moves');

export const deleteBoard = (id) => axios.delete(CHESS_API_BASE_URL + '/' + id);

export const deleteAllBoards = () => axios.delete(CHESS_API_BASE_URL);

export const getAllBoards = async () => {
    try {
      const response = await fetch(CHESS_API_BASE_URL);
      const responseText = await response.text(); // Get raw response text
  
      if (!response.ok) {
        throw new Error('Failed to fetch chessboards');
      }
  
      // Attempt to parse as JSON
      return JSON.parse(responseText);
    } catch (error) {
      console.error('Error fetching all chessboards:', error);
      throw error;
    }
  };