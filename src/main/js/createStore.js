import {applyMiddleware, combineReducers, compose, createStore} from 'redux'
import thunk from 'redux-thunk'

import inputDataFormReducer from './reducers/inputFormData'
import formDataReducer from './reducers/formData'
import dataReducer from './reducers/data'

const rootReducer = combineReducers({
    inputFormData: inputDataFormReducer,
    formData: formDataReducer,
    pageData: dataReducer
});

const composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;

export default createStore(
    rootReducer,
    composeEnhancers(
        applyMiddleware(
            thunk
        )
    )
)