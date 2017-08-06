<template>
    <div id="app">
            <router-view></router-view>
    </div>
</template>
<style lang="scss" rel="stylesheet/scss"></style>
<script>
    export default {
        data() {
            return {}
        },
        beforeRouteEnter(to, from, next) {
            next("/mdzz")
        }
    }
</script>

