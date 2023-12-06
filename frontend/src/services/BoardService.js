import axios from "axios";

const PREFIX_URL = '/board';

export default {
    getList: (params) => axios.get(`${PREFIX_URL}`, {params}),
    getOne: (boardId, params) => axios.get(`${PREFIX_URL}/${boardId}`, {params}),
    save: (params) => axios.post(`${PREFIX_URL}`, params),
    update: (boardId, params) => axios.put(`${PREFIX_URL}/${boardId}`, params),
    remove: (boardId) => axios.delete(`${PREFIX_URL}/${boardId}`),
}