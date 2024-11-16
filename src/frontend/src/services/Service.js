import axios from 'axios';
axios.defaults.withCredentials = true
export const BACKEND = import.meta.env.VITE_BACKEND ? import.meta.env.VITE_BACKEND : "http://localhost:8080"