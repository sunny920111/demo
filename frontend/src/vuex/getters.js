export default {
    getUser: (state) => state.user,
    getIsAuth: (state) => state.isAuth,
    isAdmin: state => state.user.authorities.find(x => x.authority === 'SYSTEM_ADMIN'),
    getAuthorities: state => state.user.authorities
}