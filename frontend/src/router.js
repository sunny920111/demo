import {createRouter, createWebHistory} from 'vue-router'
import AuthLayout from '@/layout/AuthLayout.vue';
import UserLayout from "@/layout/UserLayout.vue";
import AdminLayout from "@/layout/AdminLayout.vue";
import store from './store';

const requireAuth = (to, from, next) => {
    if (store.state.isAuth) {
        next();
    } else {
        if (localStorage.accessToken) {
            next({
                name: 'loading',
                query: {returnPath: to.path},
                params: {query: to.query}
            });
        } else {
            next({
                name: 'signIn',
                query: {returnPath: to.path},
                params: {query: to.query}
            });
        }
    }

};

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
            },
            {
                path: 'loading',
                name: 'loading',
                component: () => import('./components/common/LoadingComponent.vue')
            },

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
                component: () => import('./views/user/MainHome.vue'),
                beforeEnter: requireAuth
            },
            {
                path: 'board/:type/:semesterId?',
                name: 'boardList',
                component: () => import('./views/user/board/BoardList.vue'),
                beforeEnter: requireAuth
            },
            {
                path: 'board/:type/:semesterId/view/:boardId',
                name: 'boardView',
                component: () => import('./views/user/board/BoardView.vue'),
                beforeEnter: requireAuth
            },
            {
                path: 'board/:type/:semesterId/write/:boardId?',
                name: 'boardEdit',
                component: () => import('./views/user/board/BoardWrite.vue'),
                beforeEnter: requireAuth
            }
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