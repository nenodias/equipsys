import { createApp } from 'vue'
import App from './App.vue'
import PrimeVue from 'primevue/config';
import router from './router.js';
import install from './install/primevue';

const app = createApp(App)
app.use(PrimeVue)
app.use(install)
app.use(router)
app.mount('#app')
