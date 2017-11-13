'use strict';

import React from 'react';
import ReactDOM from 'react-dom';
import client from './client';
import axios from 'axios';
//import App from './components/App';

class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {employees: []};
    }

    /*componentDidMount() {
        client({method: 'GET', path: '/api/employees'}).done(response => {
            this.setState({employees: response.entity._embedded.employees});
        });
    }*/

    componentDidMount() {
        axios
            .get(`/api/employees`)
            .then(res => this.setState({ employees: res.data._embedded.employees }))
            .catch(err => console.log(err))
    }

    render() {
        console.log(this.state.employees, 'a');
        return (
            <EmployeeList employees={this.state.employees}/>
    )
    }
}

class EmployeeList extends React.Component{
    constructor(props) {
        super(props);
    }

    render() {
        console.log(this.props.employees);
        var employees = this.props.employees.map(employee =>
            <Employee key={employee._links.self.href} employee={employee}/>
        );
        return (
            <table>
                <tbody>
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Description</th>
                </tr>
                {employees}
                </tbody>
            </table>
        )
    }
}

class Employee extends React.Component{
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <tr>
                <td>{this.props.employee.firstName}</td>
                <td>{this.props.employee.lastName}</td>
                <td>{this.props.employee.description}</td>
            </tr>
        )
    }
}

class App2 extends React.Component{
    render() {
        return (
            <div>TEST 2</div>
        )
    }
}

ReactDOM.render(
    <App />,
    document.getElementById('react')
);