import {FETCHING, GET_DATA, GET_DATA_FAILURE, GET_DATA_SUCCESS, SET_ERROR} from './../actions/data'

const defaultState = {
    config: [],
    fetching: true
};

function dataReducer(state = defaultState, action) {
    switch (action.type) {
        case GET_DATA:
            return {
                ...state,
                fetching: true,
                error: false,
                config: [],
                status: action.status
            }
        case GET_DATA_SUCCESS:
            return {
                ...state,
                fetching: false,
                error: false,
                config: action.data,
                status: action.status
            }
        case GET_DATA_FAILURE:
            return {
                ...state,
                fetching: false,
                error: action.error,
                config: [],
                status: action.status
            }
        case SET_ERROR:
            return {
                ...state,
                fetching: false,
                error: action.error,
            }
        case FETCHING:
            return {
                ...state,
                fetching: true,
            }
        default:
            return state;
    }
}

export default dataReducer