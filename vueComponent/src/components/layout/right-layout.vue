<template>
    <div id="right" class="right">
        <slot></slot>
    </div>
</template>
<style lang="scss" rel="stylesheet/scss" scoped>
    @import "../../assets/scss/variables";
    .right {
        display: flex;
        background: #31b0d5;
        width: 77%;
    }
</style>
<script>
    export default {
        data() {
            return {}
        },
        components: {}
    }
</script>
