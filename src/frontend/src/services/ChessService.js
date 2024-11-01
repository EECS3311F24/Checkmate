import axios from 'axios';

const CHESS_API_BASE_URL = "http://localhost:8080/api/game";

export const startGuestGame = () => {
    return axios.post(`${CHESS_API_BASE_URL}/guest/start`);
}

export const makeMove = (from, to) => {
    return axios.post(`${CHESS_API_BASE_URL}/move`, { from, to });
}

export const getPossibleMoves = (row, col) => {
    return axios.get(`${CHESS_API_BASE_URL}/moves?row=${row}&col=${col}`);
}

export const getCurrentGameState = () => {
    return axios.get(`${CHESS_API_BASE_URL}/state`);
}