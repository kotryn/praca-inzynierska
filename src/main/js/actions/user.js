export const ADD_NEW_USER = 'ADD_NEW_USER'
export function addNewUser(query) {
    return {
        type: ADD_NEW_USER,
        query: query
    }
}

export const CLEAR = 'CLEAR'
export function clear() {
    return {
        type: CLEAR
    }
}