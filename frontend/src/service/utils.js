export default {
    dateFromServerToString(dateFromServer){
        if(dateFromServer) {
            return dateFromServer.replace('T', ' ');
        }
    },
    dateToServerData(dateJs){
        if(dateJs){
            if(dateJs instanceof Date){
                dateJs = this.dateToString(dateJs);
            }
            if(this.isString(dateJs)){
                return dateJs.replace(' ', 'T');
            }
        }
        return dateJs;
    },
    dateToString(dateJs){
        if(dateJs instanceof Date){
            const ano = this.formatLeftZero(dateJs.getFullYear(), 4);
            const mes = this.formatLeftZero(dateJs.getMonth() + 1, 2);
            const dia = this.formatLeftZero(dateJs.getDate(), 2);
            
            const hora = this.formatLeftZero(dateJs.getHours(), 2);
            const minuto = this.formatLeftZero(dateJs.getMinutes(), 2);
            const segundo = this.formatLeftZero(dateJs.getSeconds(), 2);
            return `${ano}-${mes}-${dia} ${hora}:${minuto}:${segundo}`;
        }
    },
    isString(value){
        return typeof value === "string";
    },
    formatLeftZero(value, size){
        value = `${value}`
        while(value.length < size){
            value = "0" + value;
        }
        return value;
    }
};