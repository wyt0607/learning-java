<template>
    <div id="home" class="box">
        <left></left>
        <right>
            <router-view></router-view>
        </right>
    </div>
</template>
<style lang="scss" rel="stylesheet/scss">
    @import "../assets/scss/variables.scss";
    .box {
        display: flex;
    }
</style>
<script>
    import left from "../components/layout/left-layout.vue";
    import right from "../components/layout/right-layout.vue";

    export default {
        data() {
            return {}
        },
        components: {
            left,
            right
        }
    }
</script>
