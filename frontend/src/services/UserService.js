import axios from "axios";

const PREFIX_URL = '/users';

export default {
    signUp: (params) => axios.post(`${PREFIX_URL}/signIn`, params)
}