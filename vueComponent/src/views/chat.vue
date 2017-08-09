<template>
    <div id="chat" class="chat">
        <div class="chat-left">
            <div class="chat-left-item">
                <div class="chat-head">
                    公屏:
                </div>
                <div class="chat-content">
                    <div v-for="item in 100">{{item}}</div>
                </div>
            </div>
            <div class="chat-left-item">
                <div class="chat-head">
                    私聊:
                </div>
                <div class="chat-content">
                    <div v-for="item in 100">{{item}}</div>
                </div>
            </div>
            <div class="chat-left-item">
                <div></div>
                <div>
                    <input type="text">
                </div>
            </div>
        </div>
        <div class="chat-right">
            <div class="chat-right-item">
                <span>在线人数:</span>
                <ul>
                    <li v-for="item in users">1</li>
                </ul>
            </div>
        </div>
    </div>
</template>
<style lang="scss" rel="stylesheet/scss" scoped>
    @import "../assets/scss/variables.scss";

    .chat {
        display: flex;
        justify-content: space-between;
        width: 1000px;
        > div {
            margin: 0px 5px;
        }
        .chat-left {
            display: flex;
            width: $rightWidth;
            border: solid 1px;
            flex-flow: column;
            align-items: center;
            margin-left: -5px;
            .chat-left-item {
                margin: 20px;
                width: 550px;
                height: 150px;
                border: solid 1px;
            }
        }
        .chat-right {
            display: flex;
            width: $leftWidth;
            border: solid 1px;
            flex-flow: column;
            align-items: center;
            .chat-right-item {
                margin: 50px;
            }
        }
    }

    .chat-head {
        margin: 5px;

    }

    .chat-content {
        height: 122px;
        border-top: 1px solid;
        overflow-x: hidden;
    }
</style>
<script>
    export default {
        data() {
            return {
                users: 10
            }
        },
        components: {}
    }
</script>
