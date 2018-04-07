import React from 'react';
import { connect } from 'react-redux'
import axios from 'axios';

import { getPageDataInfo } from '../../actions/data'
import { createInputData, clear } from "../../actions/inputData";
import InputComponent from "./InputComponent"
import ButtonComponent from './ButtonBackComponent'

class FormComponent extends React.Component{

    constructor(props) {
        super(props);
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

    createOutputData(){
        const {input} = this.props.config;
        let output = {};
        this.props.values.forEach((e, i)=>{
            output[input.values[i]] = e
        });

        return JSON.parse(JSON.stringify(output));
    }

    add(event) {
        event.preventDefault();
        const {button} = this.props.config;

        axios.post(button.url, this.createOutputData())
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
                <button className={'btn btn-success'} onClick={this.add}>{button.title}</button>
            </form>
        );
    }
}

FormComponent = connect(
    state =>  state.inputData,
    { createInputData, clear, getPageDataInfo }
)(FormComponent)

export default FormComponent;