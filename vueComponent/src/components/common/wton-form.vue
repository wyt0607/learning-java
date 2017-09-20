<template>
    <div id="wtonForm" style="display: flex;justify-content: center">
        <div v-for=" row in rows" ref="total">
            <input :name="row.name!=null?row.name:row"
                   :placeholder="row.placeholder!=null?row.placeholder:'请输入'+row"
                   :type="row.type!=null?row.type:'text'">
        </div>
        <div>
            <button @click="reset()">重置</button>
            <button @click="getValues()">确定</button>
        </div>
    </div>
</template>
<style lang="scss" rel="stylesheet/scss" scoped>
    div{
        margin: 5px;
    }
</style>
<script>
    export default{
        data(){
            return {
                total: null
            }
        },
        props: {
            rows: {
                type: Array,
                default: () => {
                    return [
                        {
                            type: "text",
                            placeholder: "请输入",
                            name: ""
                        }
                    ]
                }
            },
            customStyle: {
                type: Object
            }
        },
        methods: {
            getValues(){
                let total = this.$refs.total;
                let formData = new FormData();
                total.forEach((e) => {
                    let input = e.childNodes[0];
                    formData.append(input.name, input.value);
                })
                this.$emit("result", formData)
            },
            reset(){
                let total = this.$refs.total;
                total.forEach((e) => {
                    let input = e.childNodes[0];
                    input.value = null;
                })
            }
        }
    }
</script>
