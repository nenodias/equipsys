import axios from "@/service/axios";

export default {
    findById(id){
        return axios.get(`/api/obra/${id}`).then(res => res.data);
    },
    deleteById(id){
        return axios.delete(`/api/obra/${id}`);
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
        return axios.get(`/api/obra?page=${page}&size=${rows}${sortField}${nome}`)
            .then(res => res.data);
    },
    save(dados){
        console.log(dados);
        return axios.post(`/api/obra`, dados).then(res => res.data);
    },
    update(dados){
        return axios.put(`/api/obra/${dados.id}`, dados).then(res => res.data);
    }
};