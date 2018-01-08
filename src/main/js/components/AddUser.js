import React from 'react';
import axios from 'axios';
import { connect } from 'react-redux'

import { addNewUser } from '../actions/user'

class AddUser extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            name: '',
            surname: '',
            age: ''
        }
        this.addUser = this.addUser.bind(this);
    }


   /* componentWillUnmount(){
        console.log('unmount');
    }*/

    addUser(event) {
        event.preventDefault();
        const data = new FormData(event.target);
        const postData = {
            name: this.state.name,
            surname: this.state.surname,
            age: this.state.age
        }
        axios.post('/user', postData)
            .then(response => {console.log(response); this.props.addNewUser(postData)})
            .catch(error => console.log(error))
    }

    handleNameChange(e) {
        this.setState({name: e.target.value});
    }
    handleSurnameChange(e) {
        this.setState({surname: e.target.value});
    }
    handleAgeChange(e) {
        this.setState({age: e.target.value});
    }

    render() {
        return (
            <form onSubmit={this.addUser}>
                <label>
                    Name: <input type="text" name="name" value={this.state.name} onChange={this.handleNameChange.bind(this)} />
                </label>
                <label>
                    Surname: <input type="text" name="surname" value={this.state.surname} onChange={this.handleSurnameChange.bind(this)} />
                </label>
                <label>
                    Age: <input type="text" name="age" value={this.state.age} onChange={this.handleAgeChange.bind(this)} />
                </label>
                <input type="submit" value="Submit" />
            </form>
        )
    }

}

AddUser = connect(
    state =>  state.user,
    { addNewUser }
)(AddUser)

export default AddUser;