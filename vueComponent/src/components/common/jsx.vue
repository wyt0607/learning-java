<script>
    export default {
        props: {
            src: String
        },
        render: function (createElement) {
            return createElement("img", {
                attrs: {
                    src: this.src
                }
            })
        }
    }
</script>
