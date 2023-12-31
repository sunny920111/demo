import axios from "axios";

const PREFIX_URL = '/comment';

export default {
    getList: (boardId, params) => axios.get(`${PREFIX_URL}/boardId/${boardId}`, {params}),
    getOne: (commentId) => axios.get(`${PREFIX_URL}/${commentId}`),
    save: (params) => axios.post(`${PREFIX_URL}`, params),
    update: (commentId, params) => axios.put(`${PREFIX_URL}/${commentId}`, params),
    remove: (commentId) => axios.delete(`${PREFIX_URL}/${commentId}`),
}