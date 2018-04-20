import React from 'react';

import Checkbox from './Checkbox'
import {connect} from "react-redux";
import { createInputData, clear, createOutputData } from "../../actions/formData";

/*ZMIENIC!!!*/

class Form extends React.Component{

    constructor(props) {
        super(props);
        let formData = this.props.config.values.map(()=> false);
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
            <Checkbox key={index} name={element} id={index}/>
        ));

        return (
            <form className={"form-checkbox"}>
                {component}
            </form>
        );
    }
}

Form = connect(
    state =>  state.formData,
    { createInputData, clear, createOutputData }
)(Form)

export default Form;