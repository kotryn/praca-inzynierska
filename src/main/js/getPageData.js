import axios from "axios/index";

export function getPageData(){
    return axios
        .get('http://192.168.31.106:8081//data', {
            headers: {
                'Content-type': 'application/json'
            }
        })
        .then(res => res)
        .catch(error => console.log(error))
}