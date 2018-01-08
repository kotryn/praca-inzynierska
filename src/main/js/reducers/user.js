import {ADD_NEW_USER} from './../actions/user'

const defaultState = {
    query: [],
};

function userReducer(state = defaultState, action) {
    switch (action.type) {
        case ADD_NEW_USER:
            let newQuery = state.query.concat([action.query]);
            return {
                ...state,
                query: newQuery,
            }
        default:
            return state;
    }
}

export default userReducer