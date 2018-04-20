import React from 'react';
import {connect} from "react-redux";

import Checkbox from './Checkbox'
import Input from "./Input"
import { createInputData, clear, createOutputData, changeNextId } from "../../actions/formData";

class Form extends React.Component{

    constructor(props) {
        super(props);
        this.state = {
            id: this.props.currentId
        }
        this._setFormData(this.props.config, this.props);
    }

    _setFormData({type, values}, {createInputData, changeNextId}){
        let formData = [];
        switch(type){
            case "checkbox":
                formData = values.map(()=> false);
                break;
            case "input":
                formData = values.map(()=> "");
                break;
            default:
                console.log("Form: unknown type - formData!!!");
                return;
        }
        createInputData(formData);
        changeNextId(values.length);
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
        let output = {values};
        this.props.values.forEach((e, i)=>{
            output[values[i]] = e
        });

        return JSON.parse(JSON.stringify(output));
    }

    render() {
        const {names, type, values} = this.props.config;
        const id = this.state.id;

        let ComponentName = "";
        let style = "";
        switch(type){
            case "checkbox":
                ComponentName = Checkbox;
                style = "form-checkbox";
                break;
            case "input":
                ComponentName = Input;
                style = "form-text";
                break;
            default:
                console.log("Form: unknown type - componentName!!!");
                return;
        }

        const component = names.map((element, index) => (
            <ComponentName key={index} name={element} id={id+index} />
        ));

        return (
            <form className={style}>
                {component}
            </form>
        );
    }
}

Form = connect(
    state =>  state.formData,
    { createInputData, clear, createOutputData, changeNextId }
)(Form)

export default Form;