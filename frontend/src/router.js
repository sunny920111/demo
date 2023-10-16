import {createRouter, createWebHistory} from 'vue-router'
import AuthLayout from '@/components/layout/AuthLayout.vue';

const routes = [
    {
        path: '/',
        redirect: 'login',
        component: AuthLayout,
        children: [
            {
                path: 'login',
                name: 'login',
                component: () => import('./components/common/SignIn.vue')
            }
        ]
    },
];
// 라우터 인스턴스 생성
const router = createRouter({
    history: createWebHistory(),
    routes
})
export default router