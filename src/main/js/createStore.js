import { createStore, combineReducers, applyMiddleware, compose } from 'redux'
import thunk from 'redux-thunk'

import inputDataReducer from './reducers/inputData'

const rootReducer = combineReducers({
    inputData: inputDataReducer,
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