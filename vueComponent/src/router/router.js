/**
 * Created by wton on 2016/10/6.
 */
import Vue from "vue";
import VueRouter from "vue-router";
import app from "../App.vue";
import myViews from "../views/index.js";
Vue.use(VueRouter);


export  default  new VueRouter({
    mode: 'history',
    base: "/",
    scrollBehavior (to, from, savedPosition) {
        if (savedPosition) {
            return savedPosition
        } else {
            return {x: 0, y: 0}
        }
    },
    routes: [
        {path:'/home',component:myViews.home},
        {path: "*", redirect:'/home'}
    ]
});
