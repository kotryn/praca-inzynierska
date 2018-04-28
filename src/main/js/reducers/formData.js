import {ADD_NEW_INPUT_VALUE, EDIT_INPUT_VALUE, CLEAR, CREATE_INPUT_DATA, CREATE_OUTPUT_DATA, DELETE_OUTPUT_ELEMENT, CHANGE_ID, EDIT_CHECKBOX_DATA} from '../actions/formData'

const defaultState = {
    currentId: 0,
    values: [],
    checkbox: [],
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

            let newCheckboxJsonData = new Set([...state.checkbox]);
            if(newCheckboxJsonData.size > 0){
                newCheckboxJsonData.delete(action.element);
            }
            let checkbox = [...newCheckboxJsonData];
            return {
                ...state,
                jsonData: JSON.parse(JSON.stringify({checkbox})),
                checkbox: [...newCheckboxJsonData]
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
        case EDIT_CHECKBOX_DATA:
            let newData =  new Set([...state.checkbox,...action.checkbox]);
            checkbox = [...newData];
            return {
                ...state,
                checkbox: [...newData],
                jsonData: JSON.parse(JSON.stringify({checkbox}))
            }
        case CLEAR:
            return {
                ...state,
                values: [],
                jsonData: {},
                currentId: 0,
                checkbox: []
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