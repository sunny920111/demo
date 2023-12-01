import axios from "axios";

const PREFIX_URL = '/board';

export default {
    get: (params) => axios.get(`${PREFIX_URL}`, {params}),
    save: (params) => axios.post(`${PREFIX_URL}`, params),
    update: (boardId, params) => axios.put(`${PREFIX_URL}/${boardId}`, params),
    remove: (boardId) => axios.delete(`${PREFIX_URL}/${boardId}`),
}