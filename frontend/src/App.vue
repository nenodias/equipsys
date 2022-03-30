<template>
  <div class="app-container">
    <div class="layout-appbar">
      <AppBar />
    </div>

    <div class="layout-sidebar">
        <SideBar />
    </div>

    <div class="layout-main-container">
        <Card>
          <template #content>
              <router-view />
          </template>
        </Card>
        <!-- footer place  -->
    </div>
  </div>
</template>

<script>
import AppBar from "./components/AppBar.vue";
import SideBar from "./components/SideBar.vue";
import HelloWorld from "./components/HelloWorld.vue";

export default {
  name: "App",
  components: {
    AppBar,
    SideBar,
    HelloWorld,
  }
};
</script>

<style>
body {
  margin: 0;
}
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
.layout-appbar, .layout-sidebar{
  background: #f8f9fa;
}
.layout-appbar {
  grid-area: appbar;
}
.layout-sidebar {
  grid-area: sidebar;
  width: 300px;
}
.layout-main-container{
  padding: 20px;
  grid-area: main;
}
.app-container {
  width: 100%;
  margin: 0;
  display: grid;
  grid-template-areas: 
    'appbar appbar appbar'
    'sidebar main main'
    'sidebar main main'
    'sidebar main main'
  ;
}

.p-toast.p-toast-top-right {
    z-index: 1000;
    top: 7rem;
}

/* Loading CSS themes */
@import "primevue/resources/primevue.min.css";
@import "primevue/resources/themes/saga-blue/theme.css";
@import "primeicons/primeicons.css";
@import "primeflex/primeflex.min.css";
@import "primeflex/themes/saga-blue.css";
</style>
