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
        :lazy="true"
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
              @click="doEdit(slotProps.data)"
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
              <Button
                label="Inserir"
                icon="pi pi-plus"
                class="mr-2"
                @click="doNew"
              />
            </template>

            <template #end>
              <span class="p-input-icon-left">
                <i class="pi pi-search" />
                <InputText
                  v-model="filters['global'].value"
                  placeholder="Buscar"
                  @keyup.enter="doFilter"
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
import DataTableMixin from "@/mixin/DataTableMixin";
import IndexMixin from "@/mixin/IndexMixin";
import obra from "@/service/obra";

export default {
  mixins: [IndexMixin, DataTableMixin],
  data() {
    return {
      pageRoutes:{
        newPage:"ObraNew",
        editPage:"ObraEdit"
      },
      service: obra
    };
  }
};
</script>   