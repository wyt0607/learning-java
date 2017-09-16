<template>
    <div id="message" :class="['message',message.userId==name?'message-right':'message-left']">
        <div style="display: flex;">
            <img src="../../assets/mdzz/mdzz1.jpg" alt="找不到图片" @click="getInfo">
            <span v-if=" message.userId!=name">{{message.userId}}</span>
        </div>

        <div :class="['triangle',message.userId==name?'triangle-right':'triangle-left']"></div>
        <div class="message-content" :style="'width:'+width+'px'">
            {{message.text}}
        </div>

    </div>
</template>
<style lang="scss" rel="stylesheet/scss" scoped>
    .message {
        display: flex;
        margin: 15px;
        img {
            width: 30px;
            height: 30px;
            border-radius: 5px;
            cursor: pointer;
        }
    }

    .message-left {
        flex-direction: row;
    }

    .message-right {
        flex-direction: row-reverse;
    }

    .message-content {

        word-wrap: break-word;
        border: 1px solid rebeccapurple;
        border-radius: 10px;
        padding: 5px 20px;
    }

    .triangle {

        border-top: 10px solid;
        margin-top: 12px;
    }

    .triangle-left {
        border-left: 15px solid transparent;
    }

    .triangle-right {
        border-right: 15px solid transparent;
    }
</style>
<script>
    export default {
        data() {
            return {}
        },
        methods: {
            getInfo() {
                this.$emit('exec', this.message)
            }
        },
        components: {},
        props: {
            message: {
                type: Object
            },
            width: {
                type: Number,
                default: () => {
                    return 100
                }
            }
        },
        computed: {
            name() {
                return this.$store.state.name
            }
        }
    }
</script>
