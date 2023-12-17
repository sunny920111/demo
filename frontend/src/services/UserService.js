import axios from "axios";

const PREFIX_URL = '/users';

export default {
    getSemesterInfo: (params) => axios.get(`${PREFIX_URL}/semester`, {params}),
}