import http from "../api/axios.js";

const note_path = "/notes"

const getAll = (params) => {
  return http.get(note_path, { params });
};

const get = id => {
  return http.get(`${note_path}/${id}`);
};

const create = data => {
  return http.post(note_path, data);
};

const update = (id, data) => {
  return http.put(`${note_path}/${id}`, data);
};

const remove = id => {
  return http.delete(`${note_path}/${id}`);
};


export default {
  getAll,
  get,
  create,
  update,
  remove
};