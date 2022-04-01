<template>
  <div>
    <div>
        <div class="card">
            <h2>Conta</h2>
            <div class="field">
                <label for="id">Id:</label>
                <InputText id="id" type="text" v-model="form.id" class="inputfield w-full" disabled />
            </div>
            <div class="field">
                <label for="nome">Nome:</label>
                <InputText id="nome" type="text" v-model="form.nome" class="inputfield w-full" />
            </div>
            <Toolbar>
                <template #end>
                    <Button icon="pi pi-save" label="Salvar" class="p-button-success mr-2" @click="save" />
                    <Button icon="pi pi-times"  label="Cancelar" class="p-button-danger" @click="cancel" />
                </template>
            </Toolbar>
        </div>
    </div>
  </div>
</template>

<script>
import conta from "@/service/conta";
import AppEventBus from "@/AppEventBus";
export default {
    data(){
        return {
            form:{
                id:null,
                nome:""
            },
            btnSave:false,
        }
    },
    created(){
        console.log(this.$route);
        if(this.$route.params && this.$route.params.id){
            const id = this.$route.params.id;
            conta.findById(id).then(data => {
                this.form = data;
            }).catch((err) => {
                if(err && err.response && err.response.status == 404){
                    AppEventBus.emit("message", {
                        severity: "error",
                        content: `Registro com identificador ${id} não encontrado!`,
                        closeable: true,
                    });
                }else {
                    AppEventBus.emit("message", {
                        severity: "error",
                        content: `Erro ao carregar o registro ${id}!`,
                        closeable: true,
                    });
                }
                console.error(err);
                console.error(err.response);
            });
        }
    },
    methods:{
        save(){
            const catchException = (err) => {
                if(err && err.response && err.response.status == 404){
                    AppEventBus.emit("message", {
                        severity: "error",
                        content: `Registro com identificador ${id} não encontrado!`,
                        closeable: true,
                    });
                }else {
                    AppEventBus.emit("message", {
                        severity: "error",
                        content: `Erro ao carregar o registro ${id}!`,
                        closeable: true,
                    });
                }
                console.error(err);
                console.error(err.response);
            };
            if(this.form.id === null){
                conta.save(this.form).then(data => {
                    this.form = data;
                    AppEventBus.emit("message", {
                        severity: "success",
                        content: `Registro com identificador ${data.id} salvo com sucesso!`,
                        closeable: true,
                    });
                    this.$router.push({name:"ContaEdit", params:{ id: data.id }});
                }).catch(catchException);
            }else{
                conta.update(this.form).then(data => {
                    this.form = data;
                    AppEventBus.emit("message", {
                        severity: "success",
                        content: `Registro com identificador ${data.id} atualizado com sucesso!`,
                        closeable: true,
                    });
                }).catch(catchException);
            }
        },
        cancel(){
            this.$router.push({name:"ContaIndex"});
        }
    }
}
</script>

<style>

</style>