
const http = axios.create({
    baseURL:'http://localhost:8080/api'
})

http.interceptors.response.use(resp=>{
    return resp;
},err=>{
    if(err.response.data.msg){
        console.log(err.response.data.msg)
        alert(err.response.data.msg)
    }
    return Promise.reject(err);
})

