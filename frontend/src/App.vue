<template>
  <div class="overlay-messages">
    <Message v-for="msg of messages" :severity="msg.severity" :key="msg.content" :closable="msg.closable">{{msg.content}}</Message>
  </div>
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
import AppEventBus from "@/AppEventBus";

export default {
  name: "App",
  components: {
    AppBar,
    SideBar,
    HelloWorld,
  },
  data(){
    return {
      messages:[]
    }
  },
  created(){
    AppEventBus.on("message", (msg)=>{
      this.messages.push(msg);
    });
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
  width: 100%;
}
.layout-main-container{
  padding: 20px;
  grid-area: main;
  text-align: initial;
}
.app-container {
  width: 100%;
  margin: 0;
  display: grid;
  grid-template-areas: 
    'appbar appbar appbar appbar appbar appbar'
    'sidebar main main main main main'
    'sidebar main main main main main'
    'sidebar main main main main main'
  ;
}

.p-toast.p-toast-top-right {
    z-index: 1000;
    top: 7rem;
}
.overlay-messages {
  z-index: 1000;
  position: absolute;
  top: 3rem;
  right: 0px;
}

/* Loading CSS themes */
@import "primevue/resources/primevue.min.css";
@import "primevue/resources/themes/saga-blue/theme.css";
@import "primeicons/primeicons.css";
@import "primeflex/primeflex.min.css";
@import "primeflex/themes/saga-blue.css";
</style>
