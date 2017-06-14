<template>
    <div id="wtonTree">
        <ul>
            <li v-for="item in treeData">
                <router-link :to="item.url" v-if="item.url">{{item.label}}</router-link>
                <span v-else-if="item.fn!=null" @click="item.fn(vm)">{{item.label}}</span>
                <span v-else @click="dMethod">{{item.label}}</span>
                <span @click="item.show=!item.show">
                    {{item.show == true ? '[-]' : item.childs ? '[+]' : ''}}
                </span>
                <transition name="fade">
              <wton-tree v-show="item.show" :treeData="item.childs!=null?item.childs:null"></wton-tree>
                </transition>
            </li>
        </ul>
    </div>
</template>
<style lang="scss" rel="stylesheet/scss" scoped>
    span {
        cursor: pointer;
    }
    .fade-enter-active {
        transition: opacity .5s
    }
    .fade-enter{
        opacity: 0
    }
</style>
<script>
    export default{
        name: 'wtonTree',
        data(){
            return {
                vm:this
            }
        },
        props: {
            treeData: {
                type: Array,
                default: () => {
                    return [
                        {
                            label: 123,
                            childs: [{label: 'qweqwe'}],
                            url: "/test"

                        },
                        {
                            label: 11233,
                            childs: [
                                {
                                    label: '543',
                                    childs: [
                                        {
                                            label: 444222,
                                            fn: () => {
                                                console.log(444222)
                                            }
                                        }
                                    ],
                                    fn: () => {
                                        console.log(5433)
                                    }
                                }],
                            show: true,
                            fn: () => {
                                console.log(11233)
                            }
                        },
                        {
                            label: 123,
                            fn: (vm) => {
                                vm.dMethod();
                                vm.$router.push('/test')
                                console.log(vm)
                            }
                        }
                    ]
                }
            }
        },
        created(){
            if (this.treeData) {
                this.treeData.forEach((e) => {
                    if (!e.show && e.childs) {
                        this.$set(e, 'show', false);
                    }
                })
            }
        },
        methods: {
            dMethod(){
                console.log('默认方法')
            }
        }
    }
</script>
