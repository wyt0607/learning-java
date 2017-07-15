/**
 * Created by wton on 2016/10/6.
 */
import Vue from "vue";
import Vuex from "vuex";
import state from "./state";
import mutations from "./mutations";
import actions from "./actions";

Vue.use(Vuex);

export function createStore() {
    return new Vuex.Store({
        state,
        mutations,
        actions
    });
}


