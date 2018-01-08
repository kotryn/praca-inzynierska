import {getProductsInfo} from './../Product/getProductsInfo'

export function changeSort() {
    return function(dispatch, getState) {
        const query = getState().products.query;
        dispatch(getProductsAsync({
            ...query,
            sort: query.sort === 'asc' ? 'desc' : 'asc',
            offset: 0
        }))
    }
}

export function getInitialProductsAsync(viewId) {
    return function(dispatch, getState) {
        const query = getState().products.query;
        dispatch(getProductsAsync({
            ...query,
            offset: 0,
            viewId
        }))
    }
}

export function getProductsAsync(query) {
    return function(dispatch, getState) {
        query = query || getState().products.query;
        dispatch(getProducts(query))

        getProductsInfo(query)
            .then(data => dispatch(getProductsSuccess(data)))
            .catch(error => dispatch(getProductsFailure(error)))
    }
}

export const GET_PRODUCTS = 'GET_PRODUCTS'
export function getProducts(query) {
    return {
        type: GET_PRODUCTS,
        query
    }
}

export const GET_PRODUCTS_SUCCESS = 'GET_PRODUCTS_SUCCESS'
export function getProductsSuccess(data) {
    return {
        type: GET_PRODUCTS_SUCCESS,
        data: data || []
    }
}

export const GET_PRODUCTS_FAILURE = 'GET_PRODUCTS_FAILURE'
export function getProductsFailure(error) {
    return {
        type: GET_PRODUCTS_FAILURE,
        error
    }
}