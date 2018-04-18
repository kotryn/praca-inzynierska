import {ADD_NEW_INPUT_VALUE, EDIT_INPUT_VALUE, CLEAR, CREATE_INPUT_DATA, CREATE_OUTPUT_DATA} from '../actions/formData'

const defaultState = {
    values: [],
    jsonData: {}
};

function formDataReducer(state = defaultState, action) {
    switch (action.type) {
        case CREATE_INPUT_DATA:
            return {
                ...state,
                values: action.values,
            }
        case CREATE_OUTPUT_DATA:
            return {
                ...state,
                jsonData: action.jsonData,
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
                jsonData: {}
            }
        default:
            return state;
    }
}

export default formDataReducer