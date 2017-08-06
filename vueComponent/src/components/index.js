/**
 * Created by Administrator on 2016/12/22.
 */
/*
 按需加载例子
 */
const register = () => import("./service/register.vue")
const wtonForm = () => import("./common/wton-form.vue")
export {register, wtonForm}