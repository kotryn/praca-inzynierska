import React from 'react';
import { connect } from 'react-redux'
import axios from 'axios';

import { getPageDataInfo } from '../../actions/data'

class ButtonDelete extends React.Component{

    constructor(props) {
        super(props);
        this.delete = this.delete.bind(this);
    }

    delete(event) {
        event.preventDefault();
        const {url} = this.props.config;
        axios.delete(url, {data:this.props.jsonData})
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
                <button className={"btn dark-red"} onClick={this.delete}>{name}</button>
        );
    }
}

ButtonDelete = connect(
    state =>  state.formData,
    { getPageDataInfo }
)(ButtonDelete)

export default ButtonDelete;