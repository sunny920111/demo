import axios from "axios";

const PREFIX_URL = '/users';

export default {
    signIn: (params) => axios.post(`${PREFIX_URL}/signIn`, params)
}