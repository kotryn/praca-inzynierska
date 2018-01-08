import React from 'react';
import { connect } from 'react-redux'

import Users from './Users'
import AddUser from './AddUser'

class UsersList extends React.Component{
    constructor(props) {
        super(props);
    }

    render() {
        const users = this.props.config.map(users =>
            <Users key={users.id} users={users}/>
        );

        const newUser = this.props.query.map((element, index) =>
            <Users key={index} users={element}/>
        );
        return (
            <div>
                <table>
                    <tbody>
                    <tr>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Age</th>
                    </tr>
                        {users}
                        {newUser}
                    </tbody>
                </table>
                <AddUser />
            </div>
        )
    }
}

UsersList = connect(
    state =>  state.user,
    null
)(UsersList)


export default UsersList;