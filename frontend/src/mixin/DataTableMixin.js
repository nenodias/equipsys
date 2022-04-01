import { FilterMatchMode, FilterOperator } from "primevue/api";

const createLazyParams = () => {
    return {
        first: 0,
        page: 0,
        rows: null,
        sortField: "id",
        sortOrder: null,
        filters: null,
    };
};

const mixin = {
    data() {
        return {
            loading: false,
            firstFilter: true,
            totalRecords: 0,
            lazyParams: createLazyParams(),
            filters: {
                global: { value: null, matchMode: FilterMatchMode.CONTAINS },
            },
            dados: [],
        };
    },
    mounted() {
        this.loading = true;
        let lazy = createLazyParams();
        lazy.rows = this.$refs.dt.rows;
        lazy.filters = this.filters;
        this.lazyParams = lazy;
        this.loadLazyData();
    },
    methods: {
        doLazySearch(params) {
            return new Promise();
        },
        loadLazyData() {
            this.loading = true;
            this.$nextTick(() => {
                this.service.findAll(this.lazyParams).then((dados) => {
                    this.loading = false;
                    this.dados = dados.content;
                    this.totalRecords = dados.totalElements;
                });
            });
        },
        onPage(event) {
            const lazy = Object.assign({}, this.lazyParams, event);
            this.lazyParams = lazy;
            console.log("onPage", event);
            this.loadLazyData();
        },
        onSort(event) {
            const lazy = Object.assign({}, this.lazyParams, event);
            this.lazyParams = lazy;
            console.log("onSort", event);
            this.loadLazyData();
        },
        onFilter(event) {
            console.log("onFilter", event);
            this.lazyParams.filters = this.filters;
        },
        doFilter(event) {
            console.log("doFilter", event);
            this.lazyParams.filters = this.filters;
            this.loadLazyData();
        },
        refresh() {
            let lazy = createLazyParams();
            lazy.rows = this.$refs.dt.rows;
            lazy.filters = this.filters;
            lazy.filters.global.value = null;
            this.lazyParams = lazy;
            this.loadLazyData();
        },
    }
}

export default mixin;