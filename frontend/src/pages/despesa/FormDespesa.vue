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
          <Calendar
            id="dataRealizacaoObraInicio"
            showTime
            showSeconds
            showButtonBar
            monthNavigator
            yearNavigator
            hourFormat="24"
            dateFormat="yy-mm-dd"
            v-model="form.dataRealizacaoObraInicio"
            class="inputfield w-full"
          />
        </div>
        <div class="field">
          <label for="dataRealizacaoObraTermino">Data Término:</label>
          <Calendar
            id="dataRealizacaoObraTermino"
            showTime 
            showSeconds
            showButtonBar
            monthNavigator
            yearNavigator
            hourFormat="24"
            dateFormat="yy-mm-dd"
            v-model="form.dataRealizacaoObraTermino"
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
          <label for="fornecedor">Fornecedor:</label>
          <Search-Component
            id="fornecedor"
            v-model:value="form.fornecedor"
            class="inputfield w-full"
            titleHeader="Fornecedores"
            :currentComponent="IndexFornecedor"
          />
        </div>
        <div class="field">
          <label for="obra">Obra:</label>
          <Search-Component
            id="obra"
            v-model:value="form.obra"
            class="inputfield w-full"
            titleHeader="Obras"
            :currentComponent="IndexObra"
          />
        </div>
        <div class="field">
          <label for="conta">Conta:</label>
          <Search-Component
            id="conta"
            v-model:value="form.conta"
            class="inputfield w-full"
            titleHeader="Contas"
            :currentComponent="IndexConta"
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
import SearchComponent from "@/components/SearchComponent";
import IndexConta from "@/pages/conta/IndexConta";
import IndexObra from "@/pages/obra/IndexObra";
import IndexFornecedor from "@/pages/fornecedor/IndexFornecedor";

export default {
  mixins: [FormMixin],
  components:{
    SearchComponent,
    IndexConta,
    IndexObra,
    IndexFornecedor
  },
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
      ],
      form: {
          id: null,
          descricao: "",
          dataRealizacaoObraInicio: null,
          dataRealizacaoObraTermino: null,
          tipo: null,
          situacao: null,
          informacoesAdicionais: null,
          fornecedor: null,
          obra: null,
          conta: null,
          parcelas: [],
          valorTotal:0,
          valorRecebido: 0,
      },
      IndexConta:IndexConta,
      IndexObra:IndexObra,
      IndexFornecedor:IndexFornecedor
    };
  },
  methods: {
    validate() {
      return new Promise((resolve, reject) => {
        if (this.form.descricao.length > 0) {
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