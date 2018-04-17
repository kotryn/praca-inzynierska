import React from 'react';
import { connect } from 'react-redux'
import axios from 'axios';

import { getPageDataInfo } from '../../actions/data'

class Button extends React.Component{

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
        const {title} = this.props.config;

        return (
            <div className={'my-button'}>
                <button className={"btn btn-primary"} onClick={this.next}>{title}</button>
            </div>
        );
    }
}

Button = connect(
    null,
    { getPageDataInfo }
)(Button)

export default Button;