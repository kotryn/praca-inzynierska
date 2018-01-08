import React from 'react';

class Users extends React.Component{
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <tr>
                <td>{this.props.users.name}</td>
                <td>{this.props.users.surname}</td>
                <td>{this.props.users.age}</td>
            </tr>
        )
    }
}

export default Users;