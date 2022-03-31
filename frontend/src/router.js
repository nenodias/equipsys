/* eslint-disable */
import { createWebHashHistory, createRouter } from "vue-router";
import HelloWorld from  "./components/HelloWorld.vue";
import IndexConta from  "./pages/conta/IndexConta.vue";

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
        component: IndexConta,
        name: "ContaNew",
    },
    {
        path: "/conta/:id",
        name: "ContaEditId",
        component: IndexConta,
      }
]

const router = createRouter({
    history: createWebHashHistory(),
    routes,
})

export default router;