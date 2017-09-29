<template>
    <div id="register">
        <div>
            <label>注册:
                <wton-form :rows="rows" @result="register"></wton-form>
            </label>
        </div>
        <div>
            <label>登录:
                <wton-form :rows="rows" @result="login"></wton-form>
            </label>
        </div>
        <div>
            <label>测试:
                <wton-form :rows="rows" @result="test"></wton-form>
            </label>
        </div>
    </div>
</template>
<style lang="scss" rel="stylesheet/scss" scoped></style>
<script>
    import wtonForm from '../components/common/wton-form.vue';

    export default {
        data() {
            return {
                rows: [
                    'username', "password"
                ],
                treeData: [
                    {
                        label: '节点1',
                        children: [
                            {
                                label: '子节点',
                                fn: (vm) => {
                                    console.log(vm)
                                    console.log('子节点自定义方法')
                                }
                            }
                        ]

                    },
                    {
                        label: '节点2',
                        children: [
                            {label: '子节点2'},
                            {label: '子节点3'},
                            {label: '子节点4'},
                            {label: '子节点5'}
                        ]
                    },
                    {
                        label: '路由节点', url: '/',
                        children: [
                            {label: 'home', url: '/home'},
                            {label: 'test', url: 'test'}
                        ]
                    }
                ]
            }
        },
        methods: {
            register(e) {
                this.$store.dispatch("REGISTER", e)
            },
            login(e) {
                this.$store.dispatch("LOGIN", e)
            },
            test(e) {
                this.$store.dispatch("test", e)
            }
        },
        components: {
            wtonForm
        }
    }
</script>
