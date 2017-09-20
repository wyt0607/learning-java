<template>
    <div id="app">
        <router-view></router-view>
    </div>
</template>
<style lang="scss" rel="stylesheet/scss" scoped="">
    body {
        margin: 0px;
    }

    #app {
        background: url("./assets/mdzz/mdzz3.jpg") no-repeat;
        background-size: 100%;
    }
</style>
<script>
    export default {
        beforeRouteEnter(to, from, next) {
            next("/home")
        }
    }
</script>

