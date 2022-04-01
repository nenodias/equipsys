import AppEventBus from "@/AppEventBus";

const mixin = {
    data() {
        return {
            form: {
                id: null,
                nome: ""
            },
            btnSave: false,
        }
    },
    created() {
        if (this.$route.params && this.$route.params.id) {
            const id = this.$route.params.id;
            this.service.findById(id).then(data => {
                this.form = data;
            }).catch((err) => {
                if (err && err.response && err.response.status == 404) {
                    AppEventBus.emit("message", {
                        severity: "error",
                        content: `Registro com identificador ${id} não encontrado!`,
                        closeable: true,
                    });
                } else {
                    AppEventBus.emit("message", {
                        severity: "error",
                        content: `Erro ao carregar o registro ${id}!`,
                        closeable: true,
                    });
                }
                console.error(err);
                console.error(err.response);
            });
        }

    },
    methods: {
        btnSaveDebounce(){
            this.btnSave = true;
            setTimeout(() => {
                this.btnSave = false;
            }, 500);
        },
        save() {
            this.btnSaveDebounce();
            const catchException = (operation) => {
                return (err) => {
                    const id = this.form.id;
                    if (err && err.response && err.response.status == 404) {
                        AppEventBus.emit("message", {
                            severity: "error",
                            content: `Registro com identificador ${id} não encontrado!`,
                            closeable: true,
                        });
                    } else {
                        AppEventBus.emit("message", {
                            severity: "error",
                            content: `Erro ao ${operation} o registro ${id}!`,
                            closeable: true,
                        });
                    }
                    console.error(err);
                    console.error(err.response);
                };
            };
            const successCallback = (message) => {
                return (data) => {
                    this.form = data;
                    AppEventBus.emit("message", {
                        severity: "success",
                        content: message(data),
                        closeable: true,
                    });
                    this.$router.push({ name: this.pageRoutes.editPage, params: { id: data.id } });
                };
            }
            this.validate().then(() => {
                if (this.form.id === null) {
                    this.service.save(this.form)
                    .then(successCallback((data) =>`Registro com identificador ${data.id} salvo com sucesso!`))
                    .catch(catchException('salvar'));
                } else {
                    this.service.update(this.form)
                    .then(successCallback((data) =>`Registro com identificador ${data.id} atualizado com sucesso!`))
                    .catch(catchException('atualizar'));
                }
            });
        },
        cancel() {
            this.$router.push({ name: this.pageRoutes.indexPage });
        }
    }
};

export default mixin;