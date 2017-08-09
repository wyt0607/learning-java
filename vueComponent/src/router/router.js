/**
 * Created by wton on 2016/10/6.
 */
import Vue from "vue";
import VueRouter from "vue-router";
import myViews from "../views/index.js";

const app = () => import( '../App.vue');
const temp = () => import( '../components/common/temp.vue');
Vue.use(VueRouter);

export function createRouter() {
    return new VueRouter({
        mode: 'history',
        scrollBehavior(to, from, savedPosition) {
            if (savedPosition) {
                return savedPosition
            } else {
                return {x: 0, y: 0}
            }
        },
        routes: [
            {
                path: '/home', component: myViews.home,
                children: [
                    {path: "temp", component: temp},
                    {path: 'test', component: myViews.test},
                    {path: 'chat', component: myViews.chat}
                ]
            },
            {path: '/test', component: myViews.test},
            {path: "/mdzz", component: temp},
            {path: "*", component: app}
        ]
    });
}
