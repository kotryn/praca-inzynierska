import {ADD_NEW_INPUT_VALUE, EDIT_INPUT_VALUE, CLEAR, CREATE_INPUT_DATA} from '../actions/inputData'

const defaultState = {
    values: [],
};

function inputDataReducer(state = defaultState, action) {
    switch (action.type) {
        case CREATE_INPUT_DATA:
            return {
                ...state,
                values: action.values,
            }
        case EDIT_INPUT_VALUE:
            let newValues = state.values.map((e,i)=>{
                if(i === action.id){
                    return action.value
                }
                return e
            })
            return {
                ...state,
                values: newValues
            }
        case CLEAR:
            return {
                ...state,
                values: [],
            }
        default:
            return state;
    }
}

export default inputDataReducer