import axios from "@/service/axios";

export default {
    findById(id){
        return axios.get(`/api/conta/${id}`).then(res => res.data);
    },
    deleteById(id){
        return axios.delete(`/api/conta/${id}`);
    },
    findAll(){
        return axios.get(`/api/conta`).then(res => res.data);
    },
    save(dados){
        return axios.post(`/api/conta`, dados).then(res => res.data);
    },
    update(dados){
        return axios.put(`/api/conta/${dados.id}`, dados).then(res => res.data);
    }
};