import {getPageData} from './../getPageData'

export function getPageDataInfo() {
    return function(dispatch) {
        dispatch(getData());
        getPageData()
            .then(data => dispatch(getPageDataSuccess(data)))
            .catch(error => dispatch(getPageDataFailure(error)))
    }
}

export const GET_DATA = 'GET_DATA'
export function getData() {
    return {
        type: GET_DATA,
        status: 100
    }
}

export const GET_DATA_SUCCESS = 'GET_DATA_SUCCESS'
export function getPageDataSuccess(response) {
    return {
        type: GET_DATA_SUCCESS,
        data: response.data || [],
        status: response.status
    }
}

export const GET_DATA_FAILURE = 'GET_DATA_FAILURE'
export function getPageDataFailure(error) {
    return {
        type: GET_DATA_FAILURE,
        error
    }
}

export const SET_ERROR = 'SET_ERROR'
export function setError(error) {
    return {
        type: SET_ERROR,
        error
    }
}

export const FETCHING = 'FETCHING'
export function fetching() {
    return {
        type: FETCHING
    }
}