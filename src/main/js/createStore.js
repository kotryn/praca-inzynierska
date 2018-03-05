import { createStore, combineReducers, applyMiddleware, compose } from 'redux'
import thunk from 'redux-thunk'

import inputDataFormReducer from './reducers/inputFormData'
import inputDataReducer from './reducers/inputData'
import dataReducer from './reducers/data'

const rootReducer = combineReducers({
    inputFormData: inputDataFormReducer,
    inputData: inputDataReducer,
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