export const ADD_NEW_INPUT_DATA = 'ADD_NEW_INPUT_DATA'
export function addNewInputData(query) {
    return {
        type: ADD_NEW_INPUT_DATA,
        query: query
    }
}

export const CLEAR = 'CLEAR'
export function clear() {
    return {
        type: CLEAR
    }
}