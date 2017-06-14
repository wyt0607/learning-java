<template>
    <div id="register">
        <wton-form :rows="rows" @result="getValue"></wton-form>
        <wton-tree :treeData="treeData"></wton-tree>
    </div>
</template>
<style lang="scss" rel="stylesheet/scss" scoped></style>
<script>
    import wtonForm from '../common/wton-form.vue';
    import wtonTree from '../common/wton-tree.vue';
    export default{
        data(){
            return {
                rows: [
                    'username', "password"
                ],
                treeData: [
                    {
                        label: '节点1',
                        childs: [
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
                        childs: [
                            {label: '子节点2'},
                            {label: '子节点3'},
                            {label: '子节点4'},
                            {label: '子节点5'}
                        ]
                    },
                    {
                        label: '路由节点', url: '/',
                        childs: [
                            {label: 'home', url: '/home'},
                            {label: 'test', url: 'test'}
                        ]
                    }
                ]
            }
        },
        methods: {
            register(){
                console.log("注册成功!")
            },
            getValue(e){
                console.log(e)
            }
        },
        components: {
            wtonForm, wtonTree
        }
    }
</script>
