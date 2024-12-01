import axios from 'axios';
import { BACKEND } from './Service';

const CHESS_API_BASE_URL = BACKEND + '/api/v1/boards';

export const startGuestGame = (mode) => axios.put(CHESS_API_BASE_URL + "?mode=" + mode);

export const move = (id, moves) => axios.patch(CHESS_API_BASE_URL + '/' + id + '/moves', moves);

export const getBoard = (id) => axios.get(CHESS_API_BASE_URL + '/' + id);

export const getChessPiece = (row, col) => axios.get(CHESS_API_BASE_URL + '/' + id + '/moves');

export const deleteBoard = (id) => axios.delete(CHESS_API_BASE_URL + '/' + id);

export const deleteAllBoards = () => axios.delete(CHESS_API_BASE_URL);

export const getGameHistory = async () => {
    try {
      const response = await axios.get(BACKEND + '/api/chess/history');
      return response;
    } catch (error) {
      console.error('Error fetching game history:', error);
      throw error;
    }
  };

  export const getGameReplay = async (gameId) => {
    try {
      const response = await axios.get(BACKEND + "/api/chess/replay/" + gameId);
      return response;
    } catch (error) {
      console.error('Error fetching game replay:', error);
      throw error;
    }
  };

  export const saveGameResult = async (gameData) => {
    try {
      const response = await axios.post(BACKEND + '/api/chess/save', gameData);
      return response;
    } catch (error) {
      console.error('Error saving game result:', error);
      throw error;
    }
  };