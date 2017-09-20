<template>
    <div id="register" style="width: 100%;display: flex;justify-content: center;align-items: center">
        <wton-form :rows="rows" @result="register"></wton-form>
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

                console.log(e)
                this.$store.dispatch("REGISTER", e)
            },
            getValue(e) {
                console.log(e)
            }
        },
        components: {
            wtonForm
        }
    }
</script>
