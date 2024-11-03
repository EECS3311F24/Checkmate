import axios from 'axios';

const CHESS_API_BASE_URL = "http://localhost:8080/api/v1/boards";

export const startGuestGame = () => axios.put(CHESS_API_BASE_URL);

export const makeMove = (id, from, to) => axios.post(CHESS_API_BASE_URL + '/' + id, { from, to });

export const getPossibleMoves = (row, col) => {
    return axios.get(`${CHESS_API_BASE_URL}/moves?row=${row}&col=${col}`);
}

export const getCurrentGameState = () => {
    return axios.get(`${CHESS_API_BASE_URL}/state`);
}