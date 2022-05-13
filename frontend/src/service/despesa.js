import axios from "@/service/axios";

export default {
    findById(id){
        return axios.get(`/api/despesa/${id}`).then(res => res.data);
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
            .then(res => res.data);
    },
    save(dados){
        console.log(dados);
        return axios.post(`/api/despesa`, dados).then(res => res.data);
    },
    update(dados){
        return axios.put(`/api/despesa/${dados.id}`, dados).then(res => res.data);
    }
};