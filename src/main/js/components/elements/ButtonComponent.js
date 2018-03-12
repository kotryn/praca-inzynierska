import React from 'react';
import { connect } from 'react-redux'
import axios from 'axios';

import { getPageDataInfo } from '../../actions/data'

class ButtonComponent extends React.Component{

    constructor(props) {
        super(props);
        this.next = this.next.bind(this);
    }

    next(event) {
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
        const {name} = this.props.config;

        return (
            <form className={'form-text'}>
                <button onClick={this.next}>{name}</button>
            </form>
        );
    }
}

ButtonComponent = connect(
    null,
    { getPageDataInfo }
)(ButtonComponent)

export default ButtonComponent;