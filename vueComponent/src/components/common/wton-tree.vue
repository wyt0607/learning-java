<template>
    <div>
        <ul>
            <li v-for="(item,index) in treeData" ref="li">
                <input v-if="item.checked" type="checkbox" @change="checked(index)">
                <router-link :to="item.url" v-if="item.url">{{item.label}}</router-link>
                <span v-else-if="item.fn!=null" @click="item.fn(vm)">{{item.label}}</span>
                <span v-else @click="dMethod">{{item.label}}</span>
                <span v-if="item.children!=null&&item.children.length>0" @click="item.show=!item.show">
                    {{item.show == true ? '[-]' : '[+]'}}
                </span>
                <transition name="fade">
                    <wton-tree v-show="item.show" :treeData="item.children" ref="wTree"></wton-tree>
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

    .fade-enter {
        opacity: 0
    }
</style>
<script>
    export default{
        name: 'wtonTree',
        data(){
            return {
                vm: this
            }
        },
        props: {
            treeData: {
                type: Array,
                default: () => {
                    return [
                        {
                            label: 123,
                            children: [{label: 'qweqwe'}],
                            url: "/test"

                        },
                        {
                            label: 11233,
                            children: [
                                {
                                    label: '543',
                                    children: [
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
                    if (!e.show && e.children) {
                        this.$set(e, 'show', false);
                    }
                    if (!e.checked) {
                        this.$set(e, 'checked', false);
                    }
                })
            }
        },
        methods: {
            dMethod(){
                console.log('默认方法')
            },
            checked(index, parentNode){
                console.log(parentNode)
                if (this.$refs.li) {
                    let currentwTree = this.$refs.wTree[index];
                    if (currentwTree) {
                        console.log(currentwTree)
                        let currentLi = this.$refs.li[index];

                        this.treeData[index].show = currentLi.firstChild.checked;
                        if (currentwTree.$refs.li) {

                            currentwTree.$refs.li.forEach((e, i) => {
                                e.firstChild.checked = currentLi.firstChild.checked;
                                console.log(currentwTree.$refs)
                                if (currentwTree.$refs.wTree) {
                                    let childwTree = currentwTree.$refs.wTree[i];
                                    childwTree.checked(i)
                                    if (childwTree.$refs.wTree) {
                                        console.log(childwTree.$refs.wTree[i])
                                        childwTree.$refs.wTree[i].checked(i, this);
                                    }
                                }
                            })
                        } else {
                                let input = currentwTree.$parent.$parent.$el.firstChild.firstChild.firstChild;
                                this.$refs.li.forEach((e) => {
                                    e.firstChild.checked = input.checked;
                                })
                        }
                    }
                }
            }
        }
    }
</script>
