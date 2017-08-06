<template>
    <div id="left" class="left">
        <div>1345</div>
    </div>
</template>
<style lang="scss" rel="stylesheet/scss" scoped>
    @import "../../assets/scss/variables";
    .left {
        display: flex;
        background: red;
        width: $leftWidth;
        .center {
            justify-content: center;
            background: yellow;
        }
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
