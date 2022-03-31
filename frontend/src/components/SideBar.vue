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