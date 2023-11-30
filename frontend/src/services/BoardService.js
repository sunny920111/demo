import axios from "axios";

const PREFIX_URL = '/board';

export default {
    getBoardList: (params) => axios.get(`${PREFIX_URL}`, params)
}