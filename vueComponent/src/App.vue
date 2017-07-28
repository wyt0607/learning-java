<template>
    <div id="app" class="box">
        <div class="left">
            <div>1345</div>
        </div>


        <div class="right">
            <router-view></router-view>
        </div>
    </div>
</template>
<style lang="scss" rel="stylesheet/scss">
    @import "./assets/scss/variables";
    .box {
        display: flex;
        .left {
            display: flex;
            background: red;
            width: $leftWidth;
            .center{
                justify-content:center;
                background: yellow;
            }
        }
        .right {
            display: flex;
            background: #31b0d5;
            width: 77%;
        }
    }
</style>
<script>
    export default {
        data() {
            return {}
        },
        mounted() {
        }
    }
</script>

