import axios from "axios/index"

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

export function sentInputData(url, jsonData, getPageDataInfo) {
    return axios
    .post(url, jsonData)
        .then(response => {
            if(response.status === 201){
                getPageDataInfo()
            }
        })
        .catch(error => console.log(error))
}