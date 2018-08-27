import axios from "axios/index";

export function getPageData(){
    return axios
        .get('http://localhost:8080/data', {
            headers: {
                'Content-type': 'application/json'
            }
        })
        .then(res => res)
        .catch(error => console.log(error))
}