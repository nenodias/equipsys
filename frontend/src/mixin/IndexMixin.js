import ConfirmationEventBus from "primevue/confirmationeventbus";
import AppEventBus from "@/AppEventBus";

const mixin = {
    methods: {
        doNew() {
            this.$router.push({ name: this.pageRoutes.newPage });
        },
        doEdit(data) {
            this.$router.push({ name: this.pageRoutes.editPage, params: { id: data.id } });
        },
        confirmDelete(data) {
            ConfirmationEventBus.emit("confirm", this.dataConfirmDialog);
            this.$confirm.require({
                message: `Deseja remover o registro ${data.id}?`,
                header: "Atenção",
                icon: "pi pi-exclamation-triangle",
                acceptLabel: "Sim",
                rejectLabel: "Não",
                accept: () => {
                    this.service.deleteById(data.id)
                        .then((res) => {
                            if (res.status == 200) {
                                AppEventBus.emit("message", {
                                    severity: "success",
                                    content: `Registro ${data.id} removido com sucesso!`,
                                    closeable: true,
                                });
                                this.loadLazyData();
                            } else {
                                AppEventBus.emit("message", {
                                    severity: "error",
                                    content: `Erro ao remover o registro ${data.id}!`,
                                    closeable: true,
                                });
                            }
                        })
                        .catch((err) => {
                            AppEventBus.emit("message", {
                                severity: "error",
                                content: `Erro ao remover o registro ${data.id}!`,
                                closeable: true,
                            });
                            console.error(err);
                            console.error(err.response);
                        });
                },
                reject: () => { },
            });
        }
    }
};

export default mixin;