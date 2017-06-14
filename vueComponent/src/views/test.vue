<template>
    <div id="text" style="height: 2000px;width: 2000px;background: blue">
        <div v-drag style="position: absolute; margin: 10px;height: 100px;width: 100px;background: red"></div>
    </div>
</template>
<style lang="sass" rel="stylesheet/scss" scoped></style>
<script>
    export default{
        data(){
            return {}
        },
        methods: {
            test(){
                console.log(1234567)
            }
        },
        components: {}
    }
</script>
