import {createApp} from 'vue'
import App from './App.vue';
import router from './router.js';
import axios from "axios";
import store from "./store";
import commonUtil from "@/plugins/commonUtil";
import VueSmoothScroll from 'vue3-smooth-scroll';

const app = createApp(App)


//axios 설정
const rootApi = process.env.VUE_APP_ROOT;
axios.defaults.baseURL = rootApi;

//Error 났을 때, console 창에 표시
app.config.errorHandler = function (err, vm, info) {
    console.log(err, vm, info)
    info && info.preventDefault();
}

app.use(router);
app.use(store);
app.use(commonUtil);
app.use(VueSmoothScroll, {
    duration: 1000,
    offset: -50
});

app.mount('#app');
axios.interceptors.request.use((config) => {
        // console.log('axios.interceptors.request->' + localStorage.accessToken);
        if (!localStorage.accessToken) {
            config.headers.Authorization = '';
            axios.defaults.headers.common['Authorization'] = '';
        } else {
            config.headers.Authorization = `Bearer ${localStorage.accessToken}`;
            axios.defaults.headers.common['Authorization'] = `Bearer ${localStorage.accessToken}`;
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

        if (error.response.status === 401) {
            router.push('/signIn');
        }
        return Promise.reject(error);
    }
);


window.onerror = function (message, source, lineno, colno, error) {
    console.log("Exception", error);
};
