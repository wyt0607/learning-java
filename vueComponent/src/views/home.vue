<template>
    <div id="home" class="box">
        <div class="left">
            <div class="head">
                <img class="head-img" src="../../dist/mdzz16.jpg" alt="mdzz">
            </div>

            <div class="middle">
                <ul>
                    <li v-for="(item ,index) in links">
                        <router-link :to="item">测试{{index + 1}}</router-link>
                    </li>
                </ul>
            </div>
        </div>
        <div class="right">
            <transition name="test" mode="out-in">
                <router-view></router-view>
            </transition>
        </div>
    </div>
</template>
<style lang="scss" rel="stylesheet/scss" scoped>
    @import "../assets/scss/variables.scss";

    .box {
        display: flex;
        > div {
            margin: 10px;
        }
        .left {
            display: flex;
            width: $leftWidth;
            border: 1px solid;
            align-items: center;
            flex-flow: column;
        }
        .right {
            display: flex;
            width: $rightWidth;
        }
    }

    .head {
        display: flex;
        margin-top: 50px;
        .head-img {
            width: 200px;
            height: 200px;
            object-fit: cover;
            border-radius: 100px;
        }
    }

    .middle {
        flex-flow: column;
    }

    .test-entry-active {
        animation: ceshi1 1s;
    }

    .test-leave-active {
        animation: ceshi1 1s reverse;
    }

    @keyframes ceshi1 {
        from {
            opacity: 0;
        }
        to {
            opacity: 1;
        }
    }


</style>
<script>

    export default {
        data() {
            return {
                links: [
                    "/home/temp",
                    "/home/test",
                    "/home/chat"
                ]
            }
        },
        components: {}
    }
</script>
