'use strict';

import React from 'react';
import ReactDOM from 'react-dom';
import axios from 'axios';

import UsersList from './components/UsersList';

class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            config: [],
        };
    }

    componentDidMount() {
        axios
            .get(`/users`)
            .then(res => this.setState({ config: res.data }))
            .catch(err => console.log(err))
    }

    render() {
        return (
            <UsersList config={this.state.config}/>
    )
    }
}

ReactDOM.render(
    <App />,
    document.getElementById('app')
);