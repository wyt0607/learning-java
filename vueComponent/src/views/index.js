/**
 * Created by WTON on 2016/10/15.
 */
const home = () => import('./home.vue')
const test = () => import('./test.vue')
const chat = () => import('./chat.vue')
const register = () => import('./register.vue')
export default {
    home, test, chat, register
}