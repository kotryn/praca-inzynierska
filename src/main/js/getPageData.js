import axios from "axios/index";

export function getPageData(){
    return axios
        .get('/redirect', {
            headers: {
                'Content-type': 'application/json'
            }
        })
        .then(res => res)
        .catch(error => console.log(error))
}