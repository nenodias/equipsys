/* eslint-disable */
import { createWebHashHistory, createRouter } from "vue-router";
import HelloWorld from  "./components/HelloWorld.vue";
import IndexConta from  "./pages/conta/IndexConta.vue";
import FormConta from  "./pages/conta/FormConta.vue";

const routes = [
    { 
        path: '/',
        component: HelloWorld,
    },
    { 
        path: '/conta',
        component: IndexConta,
        name: "ContaIndex",
    },
    { 
        path: '/conta/new',
        component: FormConta,
        name: "ContaNew",
    },
    {
        path: "/conta/:id",
        name: "ContaEdit",
        component: FormConta,
      }
]

const router = createRouter({
    history: createWebHashHistory(),
    routes,
})

export default router;