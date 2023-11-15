import axios from "axios";

const PREFIX_URL = '/auth';

export default {
    signIn: (params) => axios.post(`${PREFIX_URL}/signIn`, params),
    signUp: (params) => axios.post(`${PREFIX_URL}/signUp`, params)
}