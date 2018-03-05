import React from 'react';
import { connect } from 'react-redux'
import axios from 'axios';

import { update, getPageDataInfo } from '../../actions/data'
import { createInputData, clear } from "../../actions/inputData";
import InputComponent from "./InputComponent"
import ButtonComponent from './ButtonComponent'

class FormInputComponent extends React.Component{

    constructor(props) {
        super(props);
        this.state = ({
            redirect: false
        })
        this.add = this.add.bind(this);
    }

    componentWillMount(){
        console.log(this.props.config);
        let inputData = this.props.config.input.values.map(()=> "");
        this.props.createInputData(inputData);
    }

    componentWillUnmount(){
        this.props.clear();
    }

    add(event) {
        event.preventDefault();
        const {button, input} = this.props.config;

        let output = {};
        this.props.values.forEach((e, i)=>{
            output[input.values[i]] = e
        });

        let postData = JSON.parse(JSON.stringify(output));
        console.log( postData );
        axios.post(button.requestUrl, postData)
            .then(response => {
                if(response.status === 201){
                    this.props.getPageDataInfo();
                }
            })
            .catch(error => console.log(error))
    }

    render() {
        const {input, button} = this.props.config;

        const component = input.names.map((element, index) => (
            <InputComponent  key={index} name={element} id={index} />
        ));

        return (
            <form className={'form-text'}>
                {component}
                <button onClick={this.add}>{button.title}</button>
            </form>
        );
    }
}

FormInputComponent = connect(
    state =>  state.inputData,
    { createInputData, clear, update, getPageDataInfo }
)(FormInputComponent)

export default FormInputComponent;