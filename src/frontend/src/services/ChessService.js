import axios from 'axios';
import { BACKEND } from './Service';

const CHESS_API_BASE_URL = BACKEND + '/api/v1/boards';

export const startGuestGame = (mode) => axios.put(CHESS_API_BASE_URL + "?mode=" + mode);

export const move = (id, moves) => axios.patch(CHESS_API_BASE_URL + '/' + id + '/moves', moves);

export const getBoard = (id) => axios.get(CHESS_API_BASE_URL + '/' + id);

export const getChessPiece = (row, col) => axios.get(CHESS_API_BASE_URL + '/' + id + '/moves');

export const deleteBoard = (id) => axios.delete(CHESS_API_BASE_URL + '/' + id);

export const deleteAllBoards = () => axios.delete(CHESS_API_BASE_URL);