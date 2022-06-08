import axios from "@/service/axios";
import utils from "@/service/utils";

export default {
    findById(id){
        return axios.get(`/api/despesa/${id}`).then(res => this.beforeGet(res.data));
    },
    deleteById(id){
        return axios.delete(`/api/despesa/${id}`);
    },
    findAll({page, rows, sortField, sortOrder, filters}){
        if(sortField !== null){
            if(sortOrder !== null){
                sortOrder = sortOrder == 1 ? "ASC" : "DESC";
            }
            sortField = `&sort=${sortField}${sortOrder ? `,${sortOrder}` : ''}`;
        }else {
            sortField = "";
        }
        let descricao = "";
        if(filters.global.value){
            descricao = `&descricao=${filters.global.value}`;
        }
        return axios.get(`/api/despesa?page=${page}&size=${rows}${sortField}${descricao}`)
            .then(res => this.beforeGets(res.data));
    },
    save(dados){
        return axios.post(`/api/despesa`, this.beforeSave(dados)).then(res => this.beforeGet(res.data));
    },
    update(dados){
        return axios.put(`/api/despesa/${dados.id}`, this.beforeSave(dados)).then(res => this.beforeGet(res.data));
    },
    beforeGets(data){
        return data;
    },
    beforeGet(data){
        let {dataRealizacaoObraInicio, dataRealizacaoObraTermino} = data;
        dataRealizacaoObraInicio = utils.dateFromServerToString(dataRealizacaoObraInicio);
        dataRealizacaoObraTermino = utils.dateFromServerToString(dataRealizacaoObraTermino);
        data.dataRealizacaoObraInicio = dataRealizacaoObraInicio;
        data.dataRealizacaoObraTermino = dataRealizacaoObraTermino;
        return data;
    },
    beforeSave(data){
        let {dataRealizacaoObraInicio, dataRealizacaoObraTermino} = data;
        dataRealizacaoObraInicio = utils.dateToServerData(dataRealizacaoObraInicio);
        dataRealizacaoObraTermino = utils.dateToServerData(dataRealizacaoObraTermino);
        data.dataRealizacaoObraInicio = dataRealizacaoObraInicio;
        data.dataRealizacaoObraTermino = dataRealizacaoObraTermino;
        return data;
    }
};