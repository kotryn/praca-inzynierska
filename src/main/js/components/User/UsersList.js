import React from 'react';
import { connect } from 'react-redux'
import { Link } from 'react-router-dom'
import axios from 'axios';

import Users from './Users'
import AddUser from './AddUser'

class UsersList extends React.Component{

    constructor(props) {
        super(props);
        this.state = {
            config: [],
            data: [],
            loading: true
        };
    }

    componentDidMount() {
        axios
            .get(`/usersconfig.json`)
            .then(res => {
                this.setState({config: res.data,/*loading: false*/});
                axios.get(res.data.data.url)
                    .then(res => {
                        this.setState({data: res.data,loading: false});
                    })
                    .catch(error => console.log(error))
            })
            .catch(error => console.log(error))
    }

    render() {
        if(this.state.loading){
            return <div>loading...</div>
        }

        const users = this.state.data.map(users =>
            <Users key={users.id} users={users}/>
        );

        const newUser = this.props.query.map((element, index) =>
            <Users key={index} users={element}/>
        );

        let table = {};
        if(this.state.config.table){
            const title = this.state.config.table && this.state.config.table.title.map((item, index)=>
                <th>{item}</th>
            )
            table = (
                <table>
                <tbody>
                    <tr>
                        {title}
                    </tr>
                    {users}
                    {newUser}
                </tbody>
            </table>);
        }

        return (
            <div>
                {table}
                <AddUser />
                <Link to='/book'>Book list</Link>
            </div>
        )
    }
}

UsersList = connect(
    state =>  state.user,
    null
)(UsersList)


export default UsersList;