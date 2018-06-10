export const ADD_NEW_INPUT_VALUE = 'ADD_NEW_INPUT_VALUE'
export function addNewInputValue(value) {
    return {
        type: ADD_NEW_INPUT_VALUE,
        value: value
    }
}

export const EDIT_INPUT_VALUE = 'EDIT_INPUT_VALUE'
export function editInputValue(value, id) {
    return {
        type: EDIT_INPUT_VALUE,
        value: value,
        id: id
    }
}

export const CREATE_INPUT_DATA = 'CREATE_INPUT_DATA'
export function createInputData(values) {
    return {
        type: CREATE_INPUT_DATA,
        values: values
    }
}

export const CLEAR = 'CLEAR'
export function clear() {
    return {
        type: CLEAR
    }
}

export const CREATE_OUTPUT_DATA = 'CREATE_OUTPUT_DATA'
export function createOutputData(jsonData) {
    return {
        type: CREATE_OUTPUT_DATA,
        jsonData: jsonData
    }
}

export const DELETE_OUTPUT_ELEMENT = 'DELETE_OUTPUT_ELEMENT'
export function deleteOutputElement(element) {
    return {
        type: DELETE_OUTPUT_ELEMENT,
        element: element
    }
}


export const CHANGE_ID = 'CHANGE_ID'
export function changeNextId(id) {
    return {
        type: CHANGE_ID,
        currentId: id
    }
}

export const EDIT_CHECKBOX_DATA = 'EDIT_CHECKBOX_DATA'
export function changeCheckboxData (checkbox) {
    return{
        type: EDIT_CHECKBOX_DATA,
        checkbox: checkbox
    }
}

export const EDIT_RADIO_DATA = 'EDIT_RADIO_DATA'
export function changeRadioData (radio, value) {
    return{
        type: EDIT_RADIO_DATA,
        radio: radio,
        value: value
    }
}

