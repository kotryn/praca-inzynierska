import React from 'react';
import { connect } from 'react-redux'
import axios from 'axios';

import { getPageDataInfo, setError } from '../../actions/data'

class ButtonForm extends React.Component{

    constructor(props) {
        super(props);
        this.add = this.add.bind(this);
    }

    add(event) {
        event.preventDefault();
        const {url} = this.props.config;

        axios.post(url, this.props.jsonData)
            .then(response => {
                if(response.status === 201){
                    this.props.getPageDataInfo();
                }else{
                    this.props.setError("server error")
                }
            })
            .catch(error => {console.log(error); this.props.setError("server error")})
    }

    render() {
        const {name} = this.props.config;

        return (
            <button className={'btn dark-green'} onClick={this.add}>{name}</button>
        );
    }
}

ButtonForm = connect(
    state =>  state.formData,
    { getPageDataInfo, setError }
)(ButtonForm)

export default ButtonForm;