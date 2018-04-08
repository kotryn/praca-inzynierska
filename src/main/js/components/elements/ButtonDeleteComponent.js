import React from 'react';
import { connect } from 'react-redux'
import axios from 'axios';

import { getPageDataInfo } from '../../actions/data'

class ButtonDeleteComponent extends React.Component{

    constructor(props) {
        super(props);
        this.delete = this.delete.bind(this);
    }

    delete(event) {
        event.preventDefault();
        const {url} = this.props.config;

        axios.delete(url)
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
                <button className={"btn btn-danger"} onClick={this.delete}>{title}</button>
            </div>
        );
    }
}

ButtonDeleteComponent = connect(
    null,
    { getPageDataInfo }
)(ButtonDeleteComponent)

export default ButtonDeleteComponent;