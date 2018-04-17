import React from 'react';
import { connect } from 'react-redux'
import axios from 'axios';

import { getPageDataInfo } from '../../actions/data'

class ButtonBack extends React.Component{

    constructor(props) {
        super(props);
        this.back = this.back.bind(this);
    }

    back(event) {
        event.preventDefault();
        const {url} = this.props.config;

        axios.post(url, '')
            .then(response => {
                if(response.status === 200){
                    this.props.getPageDataInfo();
                }
            })
            .catch(error => console.log(error))
    }

    render() {
        const {title} = this.props.config;

        return (
            <button className={'btn btn-light'} onClick={this.back}>{title}</button>
        );
    }
}

ButtonBack = connect(
    null,
    { getPageDataInfo }
)(ButtonBack)

export default ButtonBack;