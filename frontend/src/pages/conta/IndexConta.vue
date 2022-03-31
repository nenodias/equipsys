<template>
  <div>
    <ConfirmDialog></ConfirmDialog>
    <div class="card">
      <DataTable
        ref="dt"
        :totalRecords="totalRecords"
        :loading="loading"
        dataKey="id"
        :value="dados"
        :paginator="true"
        :rows="10"
        v-model:filters="filters"
        paginatorTemplate="CurrentPageReport FirstPageLink PrevPageLink PageLinks NextPageLink LastPageLink RowsPerPageDropdown"
        @page="onPage($event)"
        @sort="onSort($event)"
        @filter="onFilter($event)"
        :rowsPerPageOptions="[10, 20, 50]"
        responsiveLayout="scroll"
        stripedRows
        showGridlines
        currentPageReportTemplate="Exibindo {first} até {last} de {totalRecords} registro(s)"
      >
        <Column field="id" header="Id" :sortable="true"></Column>
        <Column field="nome" header="Nome" :sortable="true"></Column>
        <Column :exportable="false" style="min-width: 8rem">
          <template #body="slotProps">
            <Button
              icon="pi pi-pencil"
              class="p-button-rounded p-button-warning mr-2"
              @click="edit(slotProps.data)"
            />
            <Button
              icon="pi pi-trash"
              class="p-button-rounded p-button-danger"
              @click="confirmDelete(slotProps.data)"
            />
          </template>
        </Column>
        <template #paginatorstart>
          <Button
            type="button"
            icon="pi pi-refresh"
            class="p-button-text"
            @click="refresh"
          />
        </template>
        <template #paginatorend> &nbsp; </template>
        <template #header>
          <Toolbar>
            <template #start>
              <Button label="Inserir" icon="pi pi-plus" class="mr-2" />
            </template>

            <template #end>
              <span class="p-input-icon-left">
                <i class="pi pi-search" />
                <InputText
                  v-model="filters['global'].value"
                  placeholder="Buscar"
                />
              </span>
              <Button
                icon="pi pi-search"
                class="p-button-rounded p-button-info"
                @click="doFilter"
              />
            </template>
          </Toolbar>
        </template>
      </DataTable>
    </div>
  </div>
</template>

<script>
import { FilterMatchMode, FilterOperator } from "primevue/api";
import ConfirmationEventBus from "primevue/confirmationeventbus";
import AppEventBus from "@/AppEventBus";
import conta from "@/service/conta";

const semaforo = {
  value: false,
};

export default {
  data() {
    return {
      loading: false,
      firstFilter: true,
      totalRecords: 0,
      lazyParams: {
        first: 0,
        rows: null,
        sortField: null,
        sortOrder: null,
        filters: null,
      },
      filters: {
        global: { value: null, matchMode: FilterMatchMode.CONTAINS },
      },
      dados: [],
    };
  },
  mounted() {
    this.loading = true;

    this.lazyParams = {
      first: 0,
      rows: this.$refs.dt.rows,
      sortField: null,
      sortOrder: null,
      filters: this.filters,
    };
    this.loadLazyData();
  },
  methods: {
    edit(data) {
      console.log(data);
    },
    confirmDelete(data) {
      ConfirmationEventBus.emit("confirm", this.dataConfirmDialog);
      this.$confirm.require({
        message: `Deseja remover o registro ${data.id}?`,
        header: "Atenção",
        icon: "pi pi-exclamation-triangle",
        acceptLabel: "Sim",
        rejectLabel: "Não",
        accept: () => {
          conta
            .deleteById(data.id)
            .then((res) => {
              if (res.status == 200) {
                AppEventBus.emit("message", {
                  severity: "success",
                  content: `Registro ${data.id} removido com sucesso!`,
                  closeable: true,
                });
              } else {
                AppEventBus.emit("message", {
                  severity: "error",
                  content: `Erro ao remover o registro ${data.id}!`,
                  closeable: true,
                });
              }
            })
            .catch((err) => {
              AppEventBus.emit("message", {
                severity: "error",
                content: `Erro ao remover o registro ${data.id}!`,
                closeable: true,
              });
              console.error(err);
              console.error(err.response);
            });
        },
        reject: () => {},
      });
    },
    loadLazyData() {
      semaforo.value = true;
      this.loading = true;
      this.$nextTick(() => {
        conta.findAll().then((dados) => {
          this.loading = false;
          semaforo.value = false;
          this.dados = dados.content;
          this.totalRecords = dados.totalElements;
        });
      });
    },
    onPage(event) {
      this.lazyParams = event;
      console.log("onPage", event);
      this.loadLazyData();
    },
    onSort(event) {
      this.lazyParams = event;
      console.log("onSort", event);
      this.loadLazyData();
    },
    onFilter(event) {
      console.log("onFilter", event);
      this.lazyParams.filters = this.filters;
    },
    doFilter(event) {
      console.log("doFilter", event);
      this.lazyParams.filters = this.filters;
      this.loadLazyData();
    },
    refresh() {
      this.loadLazyData();
    },
  },
};
</script>   