import {createApp} from 'vue'
import App from './App.vue';
import router from './router.js';
import axios from "axios";

const app = createApp(App)


//axios 설정
const rootApi = process.env.VUE_APP_ROOT;
axios.defaults.baseURL = rootApi;

axios.interceptors.request.use((config) => {
        if (!localStorage.accessToken) {
            config.headers.Authorization = '';
            axios.defaults.headers['Authorization'] = '';
        }
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);

axios.interceptors.response.use(
    function (response) {
        if (response.headers['update-auth-token']) {
            localStorage.accessToken = response.headers['update-auth-token'];
            axios.defaults.headers['Authorization'] = `Bearer ${localStorage.accessToken}`;
        }
        return response;
    },
    function (error) {
        return Promise.reject(error);
    }
);

//Error 났을 때, console 창에 표시
app.config.errorHandler = (error) => {
    console.log('errorHandler->', error);
    console.log(error.stack);
    alert("예상치 못한 에러가 발생하였습니다.");
};

app.use(router);
app.mount('#app');

window.onerror = function (message, source, lineno, colno, error) {
    console.log("Exception", error);
};