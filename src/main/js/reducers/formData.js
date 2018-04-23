import {ADD_NEW_INPUT_VALUE, EDIT_INPUT_VALUE, CLEAR, CREATE_INPUT_DATA, CREATE_OUTPUT_DATA, DELETE_OUTPUT_ELEMENT, CHANGE_ID} from '../actions/formData'

const defaultState = {
    currentId: 0,
    values: [],
    jsonData: {}
};

function formDataReducer(state = defaultState, action) {
    switch (action.type) {
        case CREATE_INPUT_DATA:
            let newValuesCreate = state.values === [] ? action.values : state.values.concat(action.values);
            return {
                ...state,
                values: newValuesCreate,
            }
        case CREATE_OUTPUT_DATA:
            let newJsonData = state.jsonData === {} ? action.jsonData : Object.assign(state.jsonData, action.jsonData);
            return {
                ...state,
                jsonData: newJsonData,
        }
        case DELETE_OUTPUT_ELEMENT:

            let newJsonData2 = state.jsonData;
            let temp = state.jsonData;
            if(temp[action.element]){
                delete temp[action.element];
                newJsonData2 = temp;
            }
            return {
                ...state,
                jsonData: newJsonData2,
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
                jsonData: {},
                currentId: 0,
                element: null
            }
        case CHANGE_ID:
            return {
                ...state,
                currentId: state.currentId + action.currentId
            }
        default:
            return state;
    }
}

export default formDataReducer