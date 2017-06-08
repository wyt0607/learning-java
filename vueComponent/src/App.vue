<template>
    <div id="app" class="container">
        <img src="./assets/images/logo.png">
        <h1>{{ msg }}</h1>
        <h2>Essential Links</h2>
        <ul>
            <li><a href="https://vuejs.org" target="_blank">Core Docs</a></li>
            <li><a href="https://forum.vuejs.org" target="_blank">Forum</a></li>
            <li><a href="https://gitter.im/vuejs/vue" target="_blank">Gitter Chat</a></li>
            <li><a href="https://twitter.com/vuejs" target="_blank">Twitter</a></li>
        </ul>
        <h2>Ecosystem</h2>
        <ul>
            <li class="" style=""><a href="http://router.vuejs.org/" target="_blank">vue-router</a></li>
            <li><a href="http://vuex.vuejs.org/" target="_blank">vuex</a></li>
            <li><a href="http://vue-loader.vuejs.org/" target="_blank">vue-loader</a></li>
            <li><a href="https://github.com/vuejs/awesome-vue" target="_blank">awesome-vue</a></li>
        </ul>
        <div class="container">
            <input type="text" class="form-control" placeholder="请输入">
            233333333333aa试过
        </div>
    </div>
</template>
<style lang="scss" rel="stylesheet/scss" scoped>
    h1 {
        display: flex;
    }
#app{
}
    $nav-color: #F90;
    nav {
        $width: 100px;
        width: $width;
        color: $nav-color;
        span {
            span {

            }
        }
    }
</style>
<script>
    export default {
        data () {
            return {
                msg: 'Welcome to Your Vue.js App'
            }
        },
        mounted(){
            fetch("/package.json").then((response) => {
                console.log(response)
                return response.text();
            }).then((data) => {
                console.log(data);
            }).catch((error) => {
                console.log(error)
            })
        }
    }
</script>

