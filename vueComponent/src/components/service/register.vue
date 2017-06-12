<template>
    <div id="register">
        <wton-form :rows="rows" @result="getValue"></wton-form>
        <wton-tree></wton-tree>
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
                  'username',"password"
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
            wtonForm,wtonTree
        }
    }
</script>
