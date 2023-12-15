import {createStore} from "vuex";
import mutations from './vuex/mutations';
import getters from './vuex/getters';
import actions from './vuex/actions';


const state = {
    user: null,
    isAuth: false,
    authorities: []
};

export default createStore({
    state,
    mutations,
    getters,
    actions
});