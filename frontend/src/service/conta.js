import axios from "@/service/axios";

export default {
    findById(id){
        return axios.get(`/api/conta/${id}`).then(res => res.data);
    },
    deleteById(id){
        return axios.delete(`/api/conta/${id}`);
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
        let nome = "";
        if(filters.global.value){
            nome = `&nome=${filters.global.value}`;
        }
        return axios.get(`/api/conta?page=${page}&size=${rows}${sortField}${nome}`)
            .then(res => res.data);
    },
    save(dados){
        console.log(dados);
        return axios.post(`/api/conta`, dados).then(res => res.data);
    },
    update(dados){
        return axios.put(`/api/conta/${dados.id}`, dados).then(res => res.data);
    }
};