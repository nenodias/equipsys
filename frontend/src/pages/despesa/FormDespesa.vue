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
          <label for="descricao">Descricao:</label>
          <InputText
            id="descricao"
            type="text"
            v-model="form.descricao"
            class="inputfield w-full"
          />
        </div>
        <div class="field">
          <label for="dataRealizacaoObraInicio">Data Inicio:</label>
          <InputText
            id="dataRealizacaoObraInicio"
            type="text"
            v-model="form.dataRealizacaoObraInicio"
            class="inputfield w-full"
          />
        </div>
        <div class="field">
          <label for="tipo">Tipo:</label>
          <Dropdown
            id="tipo"
            v-model="form.tipo"
            class="inputfield w-full"
            :options="Tipos"
            optionLabel="label"
            optionValue="value"
          />
        </div>
        <div class="field">
          <label for="situacao">Situação:</label>
          <Dropdown
            id="situacao"
            v-model="form.situacao"
            class="inputfield w-full"
            :options="Situacoes"
            optionLabel="label"
            optionValue="value"
          />
        </div>
        <div class="field">
          <label for="informacoesAdicionais">Informações adicionais:</label>
          <Editor
            id="informacoesAdicionais"
            v-model="form.informacoesAdicionais"
            class="inputfield w-full"
          />
        </div>
        <div class="field">
          <label for="valorTotal">Valor Total:</label>
          <InputNumber
            id="valorTotal"
            v-model="form.valorTotal"
            class="inputfield w-full"
            :minFractionDigits="2" 
            :maxFractionDigits="2"
            mode="decimal" locale="pt-BR" 
          />
        </div>
        <div class="field">
          <label for="valorRecebido">Valor Recebido:</label>
          <InputNumber
            id="valorRecebido"
            v-model="form.valorRecebido"
            class="inputfield w-full"
            :minFractionDigits="2" 
            :maxFractionDigits="2"
            mode="decimal" locale="pt-BR" 
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
      Tipos:[
        {label:"Parcela",value:"PARCELA"},
        {label:"Despesa Fixa",value:"DESPESA_FIXA"}
      ],
      Situacoes:[
        {label:"Pago",value:"PAGO"},
        {label:"Parcialmente Pago",value:"PARCIALMENTE_PAGO"},
        {label:"Não Pago",value:"NAO_PAGO"},
      ]
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