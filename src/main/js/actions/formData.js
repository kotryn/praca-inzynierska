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

