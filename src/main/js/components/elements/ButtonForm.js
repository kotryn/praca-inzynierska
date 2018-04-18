import React from 'react';
import { connect } from 'react-redux'
import axios from 'axios';

import { getPageDataInfo } from '../../actions/data'

class ButtonForm extends React.Component{

    constructor(props) {
        super(props);
        this.add = this.add.bind(this);
    }

    componentWillMount(){
        console.log(this.props.config, "btn-form");
    }

    add(event) {
        event.preventDefault();
        const {url} = this.props.config;

        axios.post(url, this.props.jsonData)
            .then(response => {
                if(response.status === 201){
                    this.props.getPageDataInfo();
                }
            })
            .catch(error => console.log(error))
    }

    render() {
        const {title} = this.props.config;

        return (
            <button className={'btn btn-success'} onClick={this.add}>{title}</button>
        );
    }
}

ButtonForm = connect(
    state =>  state.formData,
    { getPageDataInfo }
)(ButtonForm)

export default ButtonForm;