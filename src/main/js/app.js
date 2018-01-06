'use strict';

import React from 'react';
import ReactDOM from 'react-dom';
import axios from 'axios';

import EmployeeList from './components/EmployeeList';

class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            employees: [],
        };
    }

    componentDidMount() {
        axios
            .get(`/api/employees`)
            .then(res => this.setState({ employees: res.data._embedded.employees }))
            .catch(err => console.log(err))
    }

    render() {
        return (
            <EmployeeList employees={this.state.employees}/>
    )
    }
}

ReactDOM.render(
    <App />,
    document.getElementById('app')
);