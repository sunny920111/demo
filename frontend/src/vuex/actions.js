import axios from "axios";

export default {
    USER_INIT({commit}) {
        if (localStorage.accessToken) {
            axios.defaults.headers.common['Authorization'] = `Bearer ${localStorage.accessToken}`;
        }
        return axios.get('/auth/detail').then(({data}) => {
            console.log('USER_INIT->', data);
            commit('USER', data);
        }).catch((error) => {
            console.log('[USER_INIT] ERROR:', error);
        });
    }
}