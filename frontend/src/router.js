import {createRouter, createWebHistory} from 'vue-router'
import AuthLayout from '@/components/layout/AuthLayout.vue';
import UserLayout from "@/layout/UserLayout.vue";

const routes = [
    {
        path: '/',
        redirect: 'signIn',
        component: AuthLayout,
        children: [
            {
                path: 'signIn',
                name: 'signIn',
                component: () => import('./components/common/SignIn.vue')
            },
            {
                path: 'signUp',
                name: 'signUp',
                component: () => import('./components/common/SignUp.vue')
            }
        ]
    },
    {
        path: '/',
        redirect: 'mainHome',
        component: UserLayout,
        children: [
            {
                path: 'mainHome',
                name: 'mainHome',
                component: () => import('./components/common/MainHome.vue')
            },
            {
                path: 'boardList',
                name: 'boardList',
                component: () => import('./components/user/board/BoardList.vue')
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