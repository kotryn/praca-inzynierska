import React from 'react';
import ReactDOM from 'react-dom';
import { HashRouter, browserHistory } from 'react-router-dom'
import { Provider } from 'react-redux'

import store from './createStore'
import App from './App';

ReactDOM.render(
    <Provider store={store}>
        <HashRouter history={browserHistory}>
            <App />
        </HashRouter>
    </Provider>,
    document.getElementById('app')
);