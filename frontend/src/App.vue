<template>
  <HelloWorld msg="Welcome to Your Vue.js App"/>
</template>

<script>
import HelloWorld from './components/HelloWorld.vue'

export default {
  name: 'App',
  components: {
    HelloWorld
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
  /* Loading CSS themes */
  @import 'primevue/resources/primevue.min.css';
  @import 'primevue/resources/themes/saga-blue/theme.css';
  @import 'primeicons/primeicons.css';
  @import 'primeflex/primeflex.min.css';
  @import 'primeflex/themes/saga-blue.css';
</style>
