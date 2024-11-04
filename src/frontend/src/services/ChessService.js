import axios from 'axios';

// TODO skill issue, cors cant be set to * and allow credentials
//axios.defaults.withCredentials = true

const CHESS_API_BASE_URL = "http://localhost:8080/api/v1/boards";

export const startGuestGame = () => axios.put(CHESS_API_BASE_URL);

export const move = (id, moves) => axios.patch(CHESS_API_BASE_URL + '/' + id + '/moves', moves);

export const getBoard = (id) => axios.get(CHESS_API_BASE_URL + '/' + id);

export const getChessPiece = (row, col) => axios.get(CHESS_API_BASE_URL + '/' + id + '/moves');