import axios from "axios";

const PREFIX_URL = '/users';

export default {
    getList: (params) => axios.get(`${PREFIX_URL}`, {params}),
    getOne: (userId) => axios.get(`${PREFIX_URL}/${userId}`),
    getSemesterInfo: (params) => axios.get(`${PREFIX_URL}/semester`, {params}),
}