export default {
    USER(state, user) {
        state.user = user;
        state.isAuth = true;
        state.authorities = user.authorities;
    }
}