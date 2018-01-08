'use strict';

import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter } from 'react-router-dom'
import axios from 'axios';
import { Provider } from 'react-redux'

import store from './createStore'
import Routes from './Routes';

class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            config: [],
            loading: true
        };
    }

    componentDidMount() {
        axios
            .get(`/users`)
            .then(res => this.setState({
                config: res.data,
                loading: false
            }))
            .catch(err => console.log(err))
    }

    render() {
        if(this.state.loading){
            return <div>loading...</div>
        }
        return (
            <Routes config={this.state.config}/>
    )
    }
}

ReactDOM.render(
    <Provider store={store}>
        <BrowserRouter>
            <App />
        </BrowserRouter>
    </Provider>,
    document.getElementById('app')
);