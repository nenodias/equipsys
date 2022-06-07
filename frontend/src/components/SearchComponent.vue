<template>
    <div>
        <Dialog v-model:visible="display">
            <template #header>
                <h3>{{titleHeader}}</h3>
            </template>

            <component markRaw v-bind:is="currentComponent" dialog @input="onInput" @clear="clear"></component>

        </Dialog>
        <div class="p-inputgroup">
            <InputText :id="id+'-value'" :placeholder="placeholder" :value="actualValue" disabled />
            <InputText :id="id+'-label'" :value="actualLabel" disabled />
            <Button :id="id+'-btn-clear'" @click="clear" icon="pi pi-times" :disabled="disabled" class="p-button"/>
            <Button :id="id+'-btn-search'" @click="onClick" icon="pi pi-search" :disabled="disabled" class="p-button-info"/>
        </div>
    </div>
</template>

<script>
export default {
    name: "SearchComponent",
    emits:['update:value'],
    props:{
        value:{
            type: [String, Number, Object]
        },
        id:{
            type: String
        },
        titleHeader:{
            type: String
        },
        disabled:{
            type: Boolean,
            default: false
        },
        placeholder:{
            type:String,
            default: ""
        },
        currentComponent:{
            type: Object,
        },
        label:{
            type: String,
            default: "nome"
        },
        fnGetValue:{
            type: Function,
            default: function(dados){
                if(dados != null){
                    return dados.id;
                }
                return "";
            }
        },
        fnGetLabel:{
            type: Function,
            default: function(dados){
                if(dados != null){
                    return dados[this.label];
                }
                return "";
            }
        }
    },
    data(){
        return {
            actualValue: this.fnGetValue(this.$props.value),
            actualLabel: this.fnGetLabel(this.$props.label),
            display: false
        }
    },
    methods:{
        onClick(){
            this.display = !this.display;
        },
        onInput(dados){
            this.actualValue = this.fnGetValue(dados);
            this.actualLabel = this.fnGetLabel(dados);
            this.display = false;
            this.$emit('update:value', dados);
        },
        clear(){
            this.actualValue = "";
            this.actualLabel = "";
            this.display = false;
            this.$emit('update:value', null);
        }
    }
}
</script>

<style>

</style>