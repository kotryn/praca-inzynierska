import {GET_PRODUCTS, GET_PRODUCTS_SUCCESS, GET_PRODUCTS_FAILURE} from './../actions/products'

const defaultState = {
    query:{
        sortBy: "price",
        sort: "asc",
        limit: 10,
        offset: 0,
        viewId: null
    },
    items: [],
    allItemsFetched: false

};

function productsReducer(state = defaultState, action) {
    switch (action.type) {
        case GET_PRODUCTS:
            return {
                ...state,
                query: action.query,
                fetching: true,
                error: false,
                items: action.query.offset == 0 ? [] : state.items
            }
        case GET_PRODUCTS_SUCCESS:
            let newItems = state.items.concat(action.data)
            return {
                ...state,
                fetching: false,
                error: false,
                items: newItems,
                query: {
                    ...state.query,
                    offset: newItems.length
                },
                allItemsFetched: action.data.length == 0
            }
        case GET_PRODUCTS_FAILURE:
            return {
                ...state,
                fetching: false,
                error: action.error,
                items: []
            }
        default:
            return state;
    }
}

export default productsReducer