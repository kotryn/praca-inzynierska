import {ADD_NEW_INPUT_DATA, CLEAR} from '../actions/inputFormData'

const defaultState = {
    query: [],
}

function inputDataFormReducer(state = defaultState, action) {
    switch (action.type) {
        case ADD_NEW_INPUT_DATA:
            let newQuery = state.query.concat([action.query])
            return {
                ...state,
                query: newQuery,
            }
        case CLEAR:
            return {
                ...state,
                query: [],
            }
        default:
            return state
    }
}

export default inputDataFormReducer