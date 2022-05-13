/* eslint-disable */
import { createWebHashHistory, createRouter } from "vue-router";
import HelloWorld from  "./components/HelloWorld.vue";

import IndexConta from  "./pages/conta/IndexConta.vue";
import FormConta from  "./pages/conta/FormConta.vue";

import IndexObra from  "./pages/obra/IndexObra.vue";
import FormObra from  "./pages/obra/FormObra.vue";

import IndexEquipamento from  "./pages/equipamento/IndexEquipamento.vue";
import FormEquipamento from  "./pages/equipamento/FormEquipamento.vue";

import IndexFornecedor from  "./pages/fornecedor/IndexFornecedor.vue";
import FormFornecedor from  "./pages/fornecedor/FormFornecedor.vue";

import IndexDespesa from  "./pages/despesa/IndexDespesa.vue";
import FormDespesa from  "./pages/despesa/FormDespesa.vue";

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
    },
    //
    { 
        path: '/obra',
        component: IndexObra,
        name: "ObraIndex",
    },
    { 
        path: '/obra/new',
        component: FormObra,
        name: "ObraNew",
    },
    {
        path: "/obra/:id",
        name: "ObraEdit",
        component: FormObra,
    },
    //
    { 
        path: '/equipamento',
        component: IndexEquipamento,
        name: "EquipamentoIndex",
    },
    { 
        path: '/equipamento/new',
        component: FormEquipamento,
        name: "EquipamentoNew",
    },
    {
        path: "/equipamento/:id",
        name: "EquipamentoEdit",
        component: FormEquipamento,
    },
    //
    { 
        path: '/fornecedor',
        component: IndexFornecedor,
        name: "FornecedorIndex",
    },
    { 
        path: '/fornecedor/new',
        component: FormFornecedor,
        name: "FornecedorNew",
    },
    {
        path: "/fornecedor/:id",
        name: "FornecedorEdit",
        component: FormFornecedor,
    },
    //
    { 
        path: '/despesa',
        component: IndexDespesa,
        name: "DespesaIndex",
    },
    { 
        path: '/despesa/new',
        component: FormDespesa,
        name: "DespesaNew",
    },
    {
        path: "/despesa/:id",
        name: "DespesaEdit",
        component: FormDespesa,
    }
]

const router = createRouter({
    history: createWebHashHistory(),
    routes,
})

export default router;