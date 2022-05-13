<template>
  <div>
    <div>
      <div class="card">
        <h2>Despesa</h2>
        <div class="field">
          <label for="id">Id:</label>
          <InputText
            id="id"
            type="text"
            v-model="form.id"
            class="inputfield w-full"
            disabled
          />
        </div>
        <div class="field">
          <label for="nome">Nome:</label>
          <InputText
            id="nome"
            type="text"
            v-model="form.nome"
            class="inputfield w-full"
          />
        </div>
        <Toolbar>
          <template #end>
            <Button
              icon="pi pi-save"
              label="Salvar"
              class="p-button-success mr-2"
              @click="save"
              :disabled="btnSave"
            />
            <Button
              icon="pi pi-times"
              label="Cancelar"
              class="p-button-danger"
              @click="cancel"
            />
          </template>
        </Toolbar>
      </div>
    </div>
  </div>
</template>

<script>
import despesa from "@/service/despesa";
import FormMixin from "@/mixin/FormMixin";
export default {
  mixins: [FormMixin],
  data() {
    return {
      pageRoutes: {
        editPage: "DespesaEdit",
        indexPage: "DespesaIndex",
      },
      service: despesa,
    };
  },
  methods: {
    validate() {
      return new Promise((resolve, reject) => {
        if (this.form.nome.length > 0) {
          resolve();
        } else {
          reject();
        }
      });
    },
  },
};
</script>

<style>
</style>