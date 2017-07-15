import Vue from "vue";
import App from "./App.vue";
import {createRouter} from "./router/router";
import {createStore} from "./store/store";
import {sync} from "vuex-router-sync";
import "./assets/index";
import "./directives/index"

export function createApp() {
    const store = createStore()
    const router = createRouter()
    sync(store, router)
    const app = new Vue({
        render: h => h(App),
        router,
        store
    })
    return {app, router, store}
}
