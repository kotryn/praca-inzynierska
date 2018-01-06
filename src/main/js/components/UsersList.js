import React from 'react';

import Users from './Users'

class UsersList extends React.Component{
    constructor(props) {
        super(props);
    }

    render() {
        console.log(this.props.config, 'eej');
        const users = this.props.config.map(users =>
            <Users key={users.id} users={users}/>
        );
        return (
            <table>
                <tbody>
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Age</th>
                </tr>
                    {users}
                </tbody>
            </table>
        )
    }
}

export default UsersList;