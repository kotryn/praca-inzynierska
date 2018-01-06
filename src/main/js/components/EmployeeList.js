import React from 'react';

import Employee from './Employee'

class EmployeeList extends React.Component{
    constructor(props) {
        super(props);
    }

    render() {
        console.log(this.props.employees);
        const employees = this.props.employees.map(employee =>
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

export default EmployeeList;