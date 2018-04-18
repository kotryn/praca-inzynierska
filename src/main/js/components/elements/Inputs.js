import React from 'react';
import { connect } from 'react-redux'

import { createInputData, clear, createOutputData } from "../../actions/formData";
import InputComponent from "./Input"

class Inputs extends React.Component{

    constructor(props) {
        super(props);
        let formData = this.props.config.values.map(()=> "");
        this.props.createInputData(formData);
    }

    componentWillUnmount(){
        this.props.clear();
    }

    shouldComponentUpdate(nextProps){
        return nextProps.jsonData === this.props.jsonData;
    }

    componentDidUpdate(){
        this.props.createOutputData(this.createOutputData());
    }

    createOutputData(){
        const {values} = this.props.config;
        let output = {};
        this.props.values.forEach((e, i)=>{
            output[values[i]] = e
        });

        return JSON.parse(JSON.stringify(output));
    }

    render() {
        const {names} = this.props.config;

        const component = names.map((element, index) => (
            <InputComponent  key={index} name={element} id={index} />
        ));

        return (
            <form className={'form-text'}>
                {component}
            </form>
        );
    }
}

Inputs = connect(
    state =>  state.formData,
    { createInputData, clear, createOutputData }
)(Inputs)

export default Inputs;