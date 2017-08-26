<template>
    <div id="chat" class="chat">
        <div class="chat-left">
            <div class="chat-left-item">
                <div class="chat-head">
                    公屏:
                </div>
                <div class="chat-content">
                    <div v-for="item in messages">{{item.userId}}说:{{item.message}}</div>
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
                <div>
                    发送列表:
                    <ul>
                        <li v-for="item in sendList" @click="removeSendList(item)">
                            {{item}}
                        </li>
                    </ul>
                </div>
                <div>
                    <input v-model="message" :placeholder="prompting" @keyup.enter="sendMessage('sendAll','SET_MESSAGE')">
                </div>
                <div>
                    <button @click="sendMessage('sendAll','SET_MESSAGE')">发送公屏</button>
                    <button @click="sendSomeOne()">发送私聊</button>
                </div>
            </div>
        </div>
        <div class="chat-right">
            <div class="chat-right-item">
                <span>在线人数:{{userList.length}}</span>
                <ul>
                    <li v-for="item in userList" @click="addSendList(item)">
                        {{item}}
                    </li>
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
            width: 100%;
            border: solid 1px;
            flex-flow: column;
            align-items: center;
            margin-left: -5px;
            .chat-left-item {
                margin: 20px;
                width: 550px;
                height: 150px;
                border: solid 1px;
                span {
                    margin-left: 5px;
                }
            }
        }
        .chat-right {
            display: flex;
            border: solid 1px;
            flex-flow: column;
            align-items: center;
            .chat-right-item {
                margin: 50px;
                width: 100%;
                span {
                    text-align: center
                }
                li {
                    cursor: pointer;
                    margin-top: 5px;
                }
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
    import {SET_MESSAGE} from '../store/types'

    export default {
        data() {
            return {
                message: null,
                websocketClient: null,
                prompting: '发送消息',
                userId: (Math.random() * 10000) / 100,
                targetId: null,
                sendList: []
            }
        },
        asyncData({store, route}) {
        },
        methods: {
            sendMessage(targetId,type) {
                if (this.message != null) {
                    let msg = JSON.stringify({
                        userId: this.userId,
                        message: this.message,
                        targetId: targetId,
                        type: type
                    })
                    this.websocketClient.send(msg);
                    this.message = null;
                    this.prompting = '发送消息'
                } else {
                    this.prompting = '发送消息不能为空'
                }
            },
            sendSomeOne() {

                this.sendList.forEach((targetId) => {
                    this.sendMessage(targetId)
                })
            },
            addSendList(userId) {
                if (!this.sendList.includes(userId)) {
                    this.sendList.push(userId);
                }
            },
            removeSendList(userId) {
                let temp = this.sendList.filter((e) => {
                    if (e != userId) return true;
                });
                this.sendList = [];
                this.sendList.push(...temp)
            }
        },
        mounted() {
            this.websocketClient = new WebSocket("ws://localhost:8081/initWebsocket?" + this.userId)
            this.$store.dispatch("INIT_WEBSOCKET", this.websocketClient)
        },
        computed: {
            messages() {
                return this.$store.state.messages;
            },
            userList() {
                return this.$store.state.userList;
            }
        },
        components: {}
    }
</script>
