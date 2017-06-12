<template>
    <div id="wtonTree">
        <ul>
            <li v-for="item in treeData">
                <span @click="item.show=!item.show">
                    {{item.label}}{{item.show == true ? '[-]' : item.childs ? '[+]' : ''}}
                </span>
                <wton-tree v-if="item.show" :treeData="item.childs!=null?item.childs:null"></wton-tree>
            </li>
        </ul>
    </div>
</template>
<style lang="scss" rel="stylesheet/scss" scoped>
    span {
        cursor: pointer;
    }
</style>
<script>
    export default{
        name: 'wtonTree',
        props: {
            treeData: {
                type: Array,
                default: () => {
                    return [
                        {
                            label: 123,
                            childs: [{label: 'qweqwe'}]

                        },
                        {
                            label: 11233,
                            childs: [{label: '543', childs: [{label: 444222}]}],
                            show: true
                        },
                        {
                            label: 123
                        }
                    ]
                }
            }
        },
        created(){
            if (this.treeData) {
                this.treeData.forEach((e) => {
                    if (!e.show && e.childs) {
                        this.$set(e, 'show', false)
                    }
                })
            }

        },
        methods: {
            toggle(show){
                console.log(show)
                return !show;
            }
        }
    }
</script>
