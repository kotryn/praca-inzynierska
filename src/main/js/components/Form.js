import React from 'react';
import axios from 'axios';
import { connect } from 'react-redux'

import { addNewUser, clear } from './../actions/user'

class Form extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            name: '',
            surname: '',
            age: ''
        }
        this.addUser = this.addUser.bind(this);
    }


     componentWillUnmount(){
         this.props.clear();
     }

    addUser(event) {
        event.preventDefault();
        const data = new FormData(/*event.target*/);
        if(this.state.name && this.state.surname && this.state.age){
            const postData = {
                name: this.state.name,
                surname: this.state.surname,
                age: this.state.age
            };
            axios.post('/user', postData)
                .then(response => {
                    console.log(response);
                    this.props.addNewUser(postData);
                    this.clearInputValues();
                })
                .catch(error => console.log(error))
        }else{
            window.alert('Complete all fields!')
        }

    }

    handleNameChange(e) {
        this.setState({name: e.target.value});
    }
    handleSurnameChange(e) {
        this.setState({surname: e.target.value});
    }
    handleAgeChange(e) {
        const age = e.target.validity.valid ? e.target.value : this.state.age;
        this.setState({age: age});
    }
    clearInputValues(){
        this.setState({name: ''});
        this.setState({surname: ''});
        this.setState({age: ''});
    }

    render() {
        return (
            <form onSubmit={this.addUser} className={'form-text'}>
                <label>
                    <div>Name:</div>
                    <input type="text" name="name" value={this.state.name} onChange={this.handleNameChange.bind(this)} />
                </label>
                <label>
                    <div>Surname:</div>
                    <input type="text" name="surname" value={this.state.surname} onChange={this.handleSurnameChange.bind(this)} />
                </label>
                <label>
                    <div>Age:</div>
                    <input type="text" pattern="[0-9]*" name="age" value={this.state.age} onChange={this.handleAgeChange.bind(this)} />
                </label>
                <div className={'parent'}>
                    <div className={'right'}><input type="submit" value="Submit" /></div>
                </div>
            </form>
        )
    }

}

Form = connect(
    state =>  state.user,
    { addNewUser, clear }
)(Form)

export default Form;