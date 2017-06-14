import Vue from "vue";
import App from "./App.vue";
import router from "./router/router";
import store from "./store/store";
import {sync} from "vuex-router-sync";
import "./assets/index";
import "./directives/index"

sync(store, router)

const app = new Vue({
    render: h => h(App),
    router,
    store
})

export {app, router, store}
