import {createApp} from 'vue'
import App from './App.vue';
import router from './router.js';

const app = createApp(App)

// Vue.config 설정
app.config.productionTip = false
app.use(router);
app.mount('#app')