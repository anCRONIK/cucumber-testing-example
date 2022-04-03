import axios from "axios";

export default axios.create({
  baseURL: process.env.REACT_APP_BACKEND_API_URL,
  headers: {
    "Content-type": "application/json",
    "Authorization": process.env.REACT_APP_BACKEND_API_AUTH_HEADER //FIXME create login form
  }
});