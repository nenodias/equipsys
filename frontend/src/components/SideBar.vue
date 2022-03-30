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