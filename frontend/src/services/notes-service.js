import axios from 'axios';
import getAuthorizationHeader from './authentication-service';

const API_URL = process.env.BACKEND_API_URL + '/notes';
class NotesService {
  getNotes() {
    return axios.get(API_URL);
  }
  getNotesWithPaging(page, pageSize, sort) {
    return axios.get(API_URL, { headers: getAuthorizationHeader() });
  }
  getNote(id) {
    return axios.get(API_URL + `/{id}`, { headers: getAuthorizationHeader() });
  }
  deleteNote(id) {
    return axios.delete(API_URL + `/{id}`, {
      headers: getAuthorizationHeader(),
    });
  }
  updateNote(id) {
    return axios.delete(API_URL + `/{id}`, {
      headers: getAuthorizationHeader(),
    });
  }
}
export default new NotesService();
