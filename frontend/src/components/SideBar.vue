<template>
  <PanelMenu :model="items" :exact="true" />
</template>

<script>
export default {
  data() {
    return {
      items: [
        {
          label: "Home",
          icon: "pi pi-home",
          to: "/",
        },
        {
          label: "Conta",
          icon: "pi pi-id-card",
          items:[
            {
              label: "Listar",
              icon: "pi pi-list",
              to: "/conta",
            },
            {
              label: "Cadastrar",
              icon: "pi pi-pencil",
              to: "/conta/new",
            }
          ],
        },
        {
          label: "Obra",
          icon: "pi pi-briefcase",
          items:[
            {
              label: "Listar",
              icon: "pi pi-list",
              to: "/obra",
            },
            {
              label: "Cadastrar",
              icon: "pi pi-pencil",
              to: "/obra/new",
            }
          ],
        },
        {
          label: "Equipamento",
          icon: "pi pi-compass",
          items:[
            {
              label: "Listar",
              icon: "pi pi-list",
              to: "/equipamento",
            },
            {
              label: "Cadastrar",
              icon: "pi pi-pencil",
              to: "/equipamento/new",
            }
          ],
        },
        {
          label: "Fornecedor",
          icon: "pi pi-shopping-cart",
          items:[
            {
              label: "Listar",
              icon: "pi pi-list",
              to: "/fornecedor",
            },
            {
              label: "Cadastrar",
              icon: "pi pi-pencil",
              to: "/fornecedor/new",
            }
          ],
        },
        {
          label: "Despesa",
          icon: "pi pi-money-bill",
          items:[
            {
              label: "Listar",
              icon: "pi pi-list",
              to: "/despesa",
            },
            {
              label: "Cadastrar",
              icon: "pi pi-pencil",
              to: "/despesa/new",
            }
          ],
        },
        {
          label: "External",
          icon: "pi pi-check",
          url: "https://www.primefaces.org/primevue",
        },
      ],
    };
  },
};
</script>
<style scoped>
</style>