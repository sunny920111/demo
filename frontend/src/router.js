import {createRouter, createWebHistory} from 'vue-router'
import AuthLayout from '@/layout/AuthLayout.vue';
import UserLayout from "@/layout/UserLayout.vue";
import AdminLayout from "@/layout/AdminLayout.vue";

const routes = [
    {
        path: '/',
        redirect: 'signIn',
        component: AuthLayout,
        children: [
            {
                path: 'signIn',
                name: 'signIn',
                component: () => import('./views/SignIn.vue')
            },
            {
                path: 'signUp',
                name: 'signUp',
                component: () => import('./views/SignUp.vue')
            }
        ]
    },
    {
        path: '/',
        redirect: 'board',
        component: UserLayout,
        children: [
            {
                path: 'mainHome',
                name: 'mainHome',
                component: () => import('./views/user/MainHome.vue')
            },
            {
                path: 'board',
                name: 'boardList',
                component: () => import('./views/user/board/BoardList.vue')
            },
            {
                path: 'board/:boardId',
                name: 'boardView',
                component: () => import('./views/user/board/BoardView.vue')
            },
            {
                path: 'board/write/:boardId',
                name: 'boardWrite',
                component: () => import('./views/user/board/BoardWrite.vue')
            },
            {
                path: 'board/write',
                name: 'boardWrite',
                component: () => import('./views/user/board/BoardWrite.vue')
            },
        ]
    },
    {
        path: '/admin',
        redirect: '/admin/home',
        component: AdminLayout,
        children: [
            {
                path: 'home',
                name: 'adminHome',
                component: () => import('./views/admin/AdminHome.vue')
            },
            {
                path: 'users',
                name: 'userList',
                component: () => import('./views/admin/UserList.vue')
            },
            {
                path: 'users',
                name: 'userRoleList',
                component: () => import('./views/admin/UserRoleList.vue')
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