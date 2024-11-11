import axios from 'axios';
import { BACKEND } from './Service';

const REST_API_BASE_URL = BACKEND + '/api/v1/users';

export const listUsers = () => axios.get(REST_API_BASE_URL);

export const getUser = (id) => axios.get(REST_API_BASE_URL + '/' + id);

export const createUser = (user) => axios.post(REST_API_BASE_URL, user);

export const updateUser = (id, user) => axios.put(REST_API_BASE_URL + '/' + id, user);

export const authenticate = (user) => axios.put(REST_API_BASE_URL + '/authenticate', user);

export const setUserPassword = (id, oldPassword, password) => 
    axios.patch(REST_API_BASE_URL + '/' + id + '?oldPassword=' + oldPassword + '&password=' + password);

export const deleteUser = (id) => axios.delete(REST_API_BASE_URL + '/' + id);