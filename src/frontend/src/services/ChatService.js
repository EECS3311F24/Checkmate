import axios from 'axios';
import { BACKEND } from './Service';

const REST_API_BASE_URL = BACKEND + '/api/v1/chats';

export const getChatMessages = () => axios.get(REST_API_BASE_URL);

export const getChatMessage = (id) => axios.get(REST_API_BASE_URL + '/' + id);

export const getChatMessagesByBoardId = (boardId) => axios.get(REST_API_BASE_URL + '?boardId=' + boardId);

export const createChatMessage = (chatMessage) => axios.post(REST_API_BASE_URL, chatMessage);

export const changeChatMessage = (message) => axios.patch(REST_API_BASE_URL + '/' + id, message, {headers: {"Content-Type": "text/plain"}});

export const deleteChatMessage = (id) => axios.delete(REST_API_BASE_URL + '/' + id);

export const deleteChatMessagesByBoardId = (boardId) => axios.delete(REST_API_BASE_URL + '?boardId=' + boardId);

export const deleteAllChatMessages = () => axios.delete(REST_API_BASE_URL);