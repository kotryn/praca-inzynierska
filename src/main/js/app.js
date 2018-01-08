'use strict';

import React from 'react';
import ReactDOM from 'react-dom';
import axios from 'axios';
import { Provider } from 'react-redux'

import store from './createStore'
import UsersList from './components/UsersList';

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
            <UsersList config={this.state.config}/>
    )
    }
}

ReactDOM.render(
    <Provider store={store}>
        <App />
    </Provider>,
    document.getElementById('app')
);