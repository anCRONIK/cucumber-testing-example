import axios from 'axios';

const authenticationUrl = process.env.REACT_APP_AUTH_URL;

class AuthenticationService {
  login(username, password) {
    return axios
      .post(
        authenticationUrl,
        {
          username,
          password,
        },
        {
          'content-type': 'application/json',
          'Access-Control-Allow-Origin': '*',
          'Access-Control-Allow-Methods': 'POST, GET, OPTIONS, PUT, DELETE',
          'Content-Type': 'application/x-www-form-urlencoded',
        }
      )
      .then((response) => {
        if (response.data.token) {
          localStorage.setItem('user', JSON.stringify(response.data));
        }
        return response.data;
      });
  }
  logout() {
    localStorage.removeItem('user');
  }

  getAccessToken() {
    return JSON.parse(localStorage.getItem('user')).token;
  }
}
export default new AuthenticationService();

export function getAuthorizationHeader() {
  const user = JSON.parse(localStorage.getItem('user'));
  if (user && user.token) {
    return { Authorization: 'Bearer ' + user.token };
  } else {
    return {};
  }
}
